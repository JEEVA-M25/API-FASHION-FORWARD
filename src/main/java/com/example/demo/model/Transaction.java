package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

     @Column(nullable = false)
     private Long clothingItemId;

     @Column(nullable = false)
     private Long sellerId;

     @Column(nullable = false)
     private Double amount;

     @Column(nullable = false)
     private LocalDate tdate;

     @Column(nullable = false)
     private String paymentMethod;

     @Column(nullable = false)
     private String status;

     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
     @JsonBackReference
     private User user;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getClothingItemId() {
        return clothingItemId;
    }

    public void setClothingItemId(Long clothingItemId) {
        this.clothingItemId = clothingItemId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }



    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getTdate() {
        return tdate;
    }

    public void setTdate(LocalDate tdate) {
        this.tdate = tdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

       
}
