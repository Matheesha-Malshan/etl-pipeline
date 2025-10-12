package org.example.transformer.validator;

import org.example.transformer.chain.chainImpl.ValidationHandler;

public abstract class DataValidator {

    public DataValidator nextDataValidator;

    public Object validatorHandler(Object o){
        boolean isValid=processValidate(o);

        if(!isValid){
            o=null;
            return o;
        }

        if(nextDataValidator!=null){

            return nextDataValidator.validatorHandler(o);
        }
        return o;
    }
    public abstract boolean processValidate(Object o);

}
