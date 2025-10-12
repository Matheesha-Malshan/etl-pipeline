package org.example.config;

import org.example.transformer.validator.DataValidator;
import org.example.transformer.validator.validatorImpl.BusinessRuleValidator;
import org.example.transformer.validator.validatorImpl.FieldValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ValidatorConfig {

    @Bean
    @Primary
    public DataValidator dataValidator(FieldValidatorImpl fieldValidator,
                                       BusinessRuleValidator businessRuleValidator){

        fieldValidator.nextDataValidator=businessRuleValidator;

        return fieldValidator;
    }
}
