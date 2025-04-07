package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.ClothingItem;

public interface ClothingItemRepository extends  JpaRepository<ClothingItem, Long>{

    List<ClothingItem>findByDescription(String description);
    List<ClothingItem>findBySize(String size);
    List<ClothingItem>findByCategory(String category);
    List<ClothingItem>findByBrand(String brand);
    
    @Query("SELECT c FROM ClothingItem c WHERE c.isSecondHand = true AND c.price BETWEEN :minPrice AND :maxPrice")
    List<ClothingItem> findSecondHandClothesInPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
