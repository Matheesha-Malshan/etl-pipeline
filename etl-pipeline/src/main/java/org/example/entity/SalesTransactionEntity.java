package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class SalesTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transaction_id;
    private Timestamp timestamp;
    private String store_id;
    private String product_id;
    private int quantity;
    private double price;
    private String customerId;
    private double total_amount;
    private double tax_amount;
    private double grand_total;

}
