package com.example.demo.repository;

// import java.time.LocalDateTime;
// import java.util.List;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction>findTransactionByUserId(Long userId);
    List<Transaction>findTransactionByClothingItemId(Long clothingItemId);
    List<Transaction>findTransactionBySellerId(Long sellerId);
    List<Transaction>findTransactionByAmount(Double amount);
    List<Transaction>findTransactionByStatus(String status); 
    List<Transaction>findTransactionByTdate(LocalDate tdate);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.sellerId = :sid")
    Double findSumOfUserAmount(@Param("sid") Long sellerId);
    
}
