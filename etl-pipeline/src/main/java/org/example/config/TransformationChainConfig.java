package org.example.config;

import org.example.transformer.chain.TransformationHandler;
import org.example.transformer.chain.chainImpl.EnrichmentHandler;
import org.example.transformer.chain.chainImpl.FilterHandler;
import org.example.transformer.chain.chainImpl.ParseHandler;
import org.example.transformer.chain.chainImpl.ValidationHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TransformationChainConfig {

    @Bean
    @Primary
    public TransformationHandler transformationHandler(ParseHandler parseHandler,
                                                       ValidationHandler validationHandler,
                                                       EnrichmentHandler enrichmentHandler){

        parseHandler.nextHandler=validationHandler;
        validationHandler.nextHandler=enrichmentHandler;
        return parseHandler;
    }

}
