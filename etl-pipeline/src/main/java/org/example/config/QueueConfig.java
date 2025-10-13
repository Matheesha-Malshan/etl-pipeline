package org.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class QueueConfig {

    @Bean(name = "extract-transformer")
    public BlockingQueue<String> queue(){
        return new LinkedBlockingQueue<>();
    }
    @Bean(name = "transformer-load")
    public BlockingQueue<Object> loadQueue(){
        return new LinkedBlockingQueue<>();
    }

}
