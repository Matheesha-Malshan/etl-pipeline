package org.example.loder;

import org.example.entity.SalesTransactionEntity;
import org.example.model.SalesTransaction;
import org.example.repository.SalesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.BlockingQueue;

@Service
public class LoadingService {

    private final BlockingQueue<Object> transformedData;
    private final SalesRepository salesRepository;
    private final ModelMapper mapper;

    public LoadingService(@Qualifier("transformer-load")BlockingQueue<Object> transformedData,
                          SalesRepository salesRepository, ModelMapper mapper){

        this.transformedData=transformedData;
        this.salesRepository=salesRepository;
        this.mapper=mapper;
    }

    @Scheduled(fixedDelay = 2000)
    public void savedData() throws InterruptedException {
        Object data;
        while ((data=transformedData.poll())!=null){

            SalesTransaction salesTransaction=(SalesTransaction) data;
            SalesTransactionEntity s=mapper.map(salesTransaction,SalesTransactionEntity.class);
            salesRepository.save(s);

        }
    }
}
