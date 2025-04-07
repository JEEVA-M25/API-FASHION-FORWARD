package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class EcoImpact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private int itemsRecycled;

    @Column(nullable = false)
    private int itemsRented;

    @Column(nullable = false)
    private int itemsSwapped;

    @Column(nullable = false)
    private double carbonFootprintReduction; 

    private LocalDate lastUpdated;

    public EcoImpact() {}

    public EcoImpact(Long id, User user, int itemsRecycled, int itemsRented, int itemsSwapped,
            double carbonFootprintReduction, LocalDate lastUpdated) {
        this.id = id;
        this.user = user;
        this.itemsRecycled = itemsRecycled;
        this.itemsRented = itemsRented;
        this.itemsSwapped = itemsSwapped;
        this.carbonFootprintReduction = carbonFootprintReduction;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemsRecycled() {
        return itemsRecycled;
    }

    public void setItemsRecycled(int itemsRecycled) {
        this.itemsRecycled = itemsRecycled;
    }

    public int getItemsRented() {
        return itemsRented;
    }

    public void setItemsRented(int itemsRented) {
        this.itemsRented = itemsRented;
    }

    public int getItemsSwapped() {
        return itemsSwapped;
    }

    public void setItemsSwapped(int itemsSwapped) {
        this.itemsSwapped = itemsSwapped;
    }

    public double getCarbonFootprintReduction() {
        return carbonFootprintReduction;
    }

    public void setCarbonFootprintReduction(double carbonFootprintReduction) {
        this.carbonFootprintReduction = carbonFootprintReduction;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.setEcoImpact(this); // Ensure both objects reference each other
    }
}
