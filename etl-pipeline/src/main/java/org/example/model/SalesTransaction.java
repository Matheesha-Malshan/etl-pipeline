package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesTransaction {

    @Id
    private String transactionId;
    private LocalDateTime time;
    private String storedId;
    private int quantity;
    private double price;
    private String customerId;
    private LocalDateTime createdAt;

}
