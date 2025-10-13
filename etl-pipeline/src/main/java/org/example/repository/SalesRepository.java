package org.example.repository;

import org.example.entity.SalesTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesTransactionEntity,String> {
}
