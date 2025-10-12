package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesTransaction {


    private String transaction_id;
    private Timestamp timestamp;
    private String store_id;
    private String product_id;
    private int quantity;
    private double price;
    private String customerId;

}
