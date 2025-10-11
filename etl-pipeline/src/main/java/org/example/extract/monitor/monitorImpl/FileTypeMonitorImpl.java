package org.example.extract.monitor.monitorImpl;


import org.example.model.Filetype;
import org.example.extract.monitor.FileTypeMonitor;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class FileTypeMonitorImpl implements FileTypeMonitor {

    public Filetype detectFileType(Path filepath){

        String fileName=filepath.getFileName().toString().toLowerCase();

        if (fileName.endsWith(".csv")){
            return Filetype.CSV;
        } else if (fileName.endsWith(".json")) {
            return Filetype.JSON;
        }

        return null;
    }
}
