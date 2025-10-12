package org.example.transformer.validator.validatorImpl;

import jdk.jfr.Category;
import org.example.model.SalesTransaction;
import org.example.transformer.validator.DataValidator;
import org.springframework.stereotype.Component;

@Component
public class FieldValidatorImpl extends DataValidator {
    @Override
    public boolean processValidate(Object o) {
        SalesTransaction salesTransaction=(SalesTransaction) o;

        return validate(salesTransaction);


    }

    public boolean validate(SalesTransaction salesTransaction){

        boolean isValid=true;

        if("null".equalsIgnoreCase(salesTransaction.getTransaction_id())){
            isValid=false;
        }
        if(salesTransaction.getTimestamp()==null){
            isValid=false;
        }
        if(salesTransaction.getStore_id()==null){
            isValid=false;
        }
        if(salesTransaction.getProduct_id()==null){
            isValid=false;
        }

        if (salesTransaction.getPrice()<0){
            isValid=false;
        }
        return isValid;
    }
}
