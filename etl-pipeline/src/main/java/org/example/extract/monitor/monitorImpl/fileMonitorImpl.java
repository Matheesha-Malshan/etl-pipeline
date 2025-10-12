package org.example.extract.monitor.monitorImpl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.extract.extractor.FileExtractor;
import org.example.extract.extractor.extractorContext.ExtractorFactory;
import org.example.model.Filetype;
import org.example.extract.monitor.FileMonitor;
import org.example.extract.monitor.FileTypeMonitor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@ConditionalOnProperty(name="etl.file-monitor.enabled",havingValue = "true")
@Slf4j
@RequiredArgsConstructor
public class fileMonitorImpl implements FileMonitor {

    @Value("${etl.file-monitor.input-directory}")
    private String inputDirectory;

    @Value("${etl.file-monitor.processed-directory}")
    private  String processedDirectory;

    @Value("${etl.file-monitor.failed-directory}")
    private String failedDirectory;


    private WatchService watchService;
    private ExecutorService watcherExecutor;
    private volatile boolean running=false;

    final ExtractorFactory extractorFactory;
    final FileTypeMonitor fileTypeMonitor;
    final ObjectMapper objectMapper;
    final BlockingQueue<String> dataQueue;


    @PostConstruct
    public void start() throws IOException {

        try {

            Path dirPath = Paths.get(inputDirectory);
            Files.createDirectories(dirPath);

            // Create WatchService and register for "file created" events
            watchService = FileSystems.getDefault().newWatchService();
            dirPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            running = true;
            watcherExecutor = Executors.newSingleThreadExecutor();
            watcherExecutor.submit(this::watchDirectory);

            log.info("✅ Simple File Monitor started, watching folder: {}", inputDirectory);
        } catch (IOException e) {
            log.error("❌ Failed to start file watcher", e);
        }
    }
    private void watchDirectory() {
        while (running) {
            try {
                WatchKey key = watchService.take(); // Wait for an event
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        Path fileName = (Path) event.context();
                        log.info("📁 New file detected: {}", fileName);

                        Filetype fileType=fileTypeMonitor.detectFileType(fileName);

                       FileExtractor fileExtractor=extractorFactory.getStrategy(fileType);

                        Path fullPath = Paths.get(inputDirectory, fileName.toString());
                        File file = fullPath.toFile();
                        ArrayList<Map<String, String>> arrayList=fileExtractor.readFile(file);

                        for(Map<String, String> ar:arrayList){
                            String data=objectMapper.writeValueAsString(ar);
                            dataQueue.put(data);
                        }

                    }
                }
                key.reset();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("File watcher interrupted");
                break;
            } catch (Exception e) {
                log.error("Error watching directory", e);
            }
        }
    }
    @PreDestroy
    public void stopWatching() {
        running = false;
        log.info("🛑 Stopping Simple File Monitor...");
        try {
            if (watchService != null) watchService.close();
            if (watcherExecutor != null) {
                watcherExecutor.shutdown();
                watcherExecutor.awaitTermination(5, TimeUnit.SECONDS);
            }
            log.info("✅ File Monitor stopped.");
        } catch (Exception e) {
            log.error("Error while stopping file watcher", e);
        }
    }


}
