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
public class Alteration {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;


    @Column(nullable = false)
    private Long clothingItemId;

    @Column(nullable = false)
    private Long alterationProviderId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double cost;

    private LocalDate requestDate;

    public Alteration() {
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

    public Long getAlterationProviderId() {
        return alterationProviderId;
    }

    public void setAlterationProviderId(Long alterationProviderId) {
        this.alterationProviderId = alterationProviderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}