package org.example.transformer;

import lombok.RequiredArgsConstructor;
import org.example.config.TransformationChainConfig;
import org.example.model.SalesTransaction;
import org.example.transformer.chain.TransformationHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class TransformationService {

    private final BlockingQueue<String> dataQueue;

    private final BlockingQueue<Object> transformedData;

    final TransformationHandler chain;

    public TransformationService(@Qualifier("extract-transformer")BlockingQueue<String> dataQueue,
                                 @Qualifier("transformer-load")BlockingQueue<Object> transformedData,
                                 TransformationHandler chain){
        this.dataQueue=dataQueue;
        this.transformedData=transformedData;
        this.chain=chain;

    }

    @Scheduled(fixedDelay = 1000)
    public void savedData() throws InterruptedException {
        String data;
        while ((data=dataQueue.poll())!=null){
            Object o=chain.handle(data);
            if (o instanceof SalesTransaction){
                transformedData.add(o);
            }


        }
    }
}
