package org.example.transformer;

import lombok.RequiredArgsConstructor;
import org.example.config.TransformationChainConfig;
import org.example.transformer.chain.TransformationHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
@RequiredArgsConstructor
public class TransformationService {

    final BlockingQueue<String> dataQueue;
    final TransformationHandler chain;

    @Scheduled(fixedDelay = 1000)
    public void savedData() throws InterruptedException {
        String data;
        while ((data=dataQueue.poll())!=null){
            System.out.println("math*************************");
            chain.handle(data);
        }
    }
}
