package org.example.transformer.Enricher.enricherImpl;

import org.example.model.SalesTransaction;
import org.example.transformer.Enricher.Enrichment;
import org.springframework.stereotype.Service;

@Service
public class EnrichmentImpl implements Enrichment {

    @Override
    public Object enrichData(Object data) {

        SalesTransaction salesTransaction=(SalesTransaction)data;
        return setSalesTransaction(salesTransaction);

    }
    public double setTotalAmount(SalesTransaction salesTransaction){

        return salesTransaction.getQuantity()*salesTransaction.getPrice();
    }
    public double setTaxAmount(double totalAmount){
        return totalAmount*0.1;
    }
    public double setGrandTotal(double totalAmount,double taxAmount){
        return totalAmount+taxAmount;
    }
    public Object setSalesTransaction(SalesTransaction object){
        object.setTotal_amount(setTotalAmount(object));
        object.setTax_amount(setTaxAmount(object.getTotal_amount()));
        object.setGrand_total(setGrandTotal(object.getTax_amount(),
                object.getTax_amount()));

        return object;
    }
}
