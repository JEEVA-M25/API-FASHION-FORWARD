package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.ClothingItem;
import com.example.demo.repository.ClothingItemRepository;

@Service
public class ClothingItemService {


    @Autowired
    ClothingItemRepository Crepo;

   public List<ClothingItem>addCloth(List<ClothingItem>clothes)
   {
       return Crepo.saveAll(clothes);
   }
   public List<ClothingItem>findAllClothes()
   {
       return Crepo.findAll();
   }
   public List<ClothingItem>findByBrand(String brand)
   {
    return Crepo.findByBrand(brand);
   }
   public List<ClothingItem>findByCategory(String category )
   {
    return Crepo.findByCategory(category);
   }
   public List<ClothingItem>findBySize(String size)
   {
    return Crepo.findBySize(size);
   }
   public List<ClothingItem>findByDescription(String description)
   {
    return Crepo.findByDescription(description);
   }
   public Optional <ClothingItem> findClothById(Long id) {
    return Crepo.findById(id);
  }
  public ResponseEntity<?> updateCloth(Long id,ClothingItem item)
    {
        Optional<ClothingItem>found= Crepo.findById(id);
        if(found.isPresent()) 
        {
            item.setId(id);
            ClothingItem ci = Crepo.save(item); 
            return ResponseEntity.ok(ci);
        } 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClothingItem with ID " + id + " not found.");
    }

    public Page<ClothingItem> getClothsSorted(int page,int size, String field, String way)
    {
        Sort sorted = way.equalsIgnoreCase("asc")?Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pages = PageRequest.of(page, size, sorted);
        return Crepo.findAll(pages);
    }

    public ResponseEntity<?> deleteCloth(Long id)
    {
        Optional<ClothingItem>found= Crepo.findById(id);
        if(found.isPresent()) 
        {
            Crepo.deleteById(id);
            return ResponseEntity.ok("Cloth with id-> "+id+" has bdeen deleted successfully");   
        } 
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cloth with ID " + id + " not found to delete that particular id");
    
    }
    public List<ClothingItem>getIsSecondHandClothsPrice( double minp, double maxp)
    {
       return Crepo.findSecondHandClothesInPriceRange(minp,maxp);
    }
  public String deleteAllClothes()
  {
     Crepo.deleteAll();
     return "All Clothing Items have been deleted SUCCESSFULLY";
  }
    
}
