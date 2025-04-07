package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ClothingItem;
import com.example.demo.service.ClothingItemService;





@RestController
@RequestMapping("/clothing-items")
public class ClothingItemController {
    @Autowired
    ClothingItemService Cser;

    @PostMapping("/post")
    public List<ClothingItem>addcloth(@RequestBody List<ClothingItem>clothes)
    {
       return Cser.addCloth(clothes);
    }
    
    @GetMapping("/get")
    public List<ClothingItem>findAllClothes()
    {
       return Cser.findAllClothes();
    }
    @GetMapping("/get/size")
    public List<ClothingItem>findClothsBySize(@RequestParam String size)
    {
       return Cser.findBySize(size);
    }
    @GetMapping("/get/brand")
    public List<ClothingItem>findByBrand(@RequestParam String brand)
    {
       return Cser.findByBrand(brand);
    }
    @GetMapping("/get/category")
    public List<ClothingItem>findClothByCategory(@RequestParam String category)
    {
       return Cser.findByCategory(category);
    }
    @GetMapping("/get/description")
    public List<ClothingItem>findClothsByDesc(@RequestParam String description)
    {
       return Cser.findByDescription(description);
    }
    
    @GetMapping("/get/{id}")
    public Optional <ClothingItem> findClothById(@PathVariable Long id) {
        return Cser.findClothById(id);
    }
    
    @GetMapping("/page/sort")
    public Page<ClothingItem> GetPagesSorted(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size, @RequestParam(defaultValue="description") String field, @RequestParam(defaultValue="asc") String way)
    {
        return Cser.getClothsSorted(page, size, field, way);
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<?> updateCloth(@PathVariable Long id, @RequestBody ClothingItem item)
    {
        return Cser.updateCloth(id, item);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCloth(@PathVariable Long id)
    {
        return Cser.deleteCloth(id);
    }

    @DeleteMapping("/delete/all")
    public String deleteAllClothes()
    {
        return Cser.deleteAllClothes();
    }
    @GetMapping("/get/second-hand")
    public List<ClothingItem>getIsSecondHandCloths(@RequestParam double minp, @RequestParam double maxp)
    {
        return Cser.getIsSecondHandClothsPrice(minp,maxp);
    }
}