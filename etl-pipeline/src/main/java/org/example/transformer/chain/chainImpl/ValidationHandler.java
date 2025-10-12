package org.example.transformer.chain.chainImpl;

import lombok.RequiredArgsConstructor;
import org.example.transformer.chain.TransformationHandler;
import org.example.transformer.validator.DataValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationHandler extends TransformationHandler {

    final DataValidator dataValidatorChain;

    @Override
    public Object process(Object input) {
        return dataValidatorChain.validatorHandler(input);
    }
}
