package org.example.transformer.validator.validatorImpl;

import lombok.RequiredArgsConstructor;
import org.example.model.SalesTransaction;
import org.example.transformer.validator.DataValidator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;




@Component
public class BusinessRuleValidator extends DataValidator {

    @Override
    public boolean processValidate(Object o) {

        SalesTransaction salesTransaction=(SalesTransaction) o;
        return validation(salesTransaction);
    }
    public boolean validation(SalesTransaction salesTransaction){

        boolean isValid=true;

        if (salesTransaction.getQuantity()*salesTransaction.getPrice()>1000.0){
            isValid=false;
        }

        int hour = salesTransaction.getTimestamp().toLocalDateTime().getHour();
        if (hour<8||hour>22){
            isValid=false;
        }

        return isValid;
    }



}
