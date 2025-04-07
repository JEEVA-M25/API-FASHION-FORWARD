package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.model.Alteration;
import com.example.demo.service.AlterationService;

@RestController
@RequestMapping("/alterations")
public class AlterationController {
 
    @Autowired
    private AlterationService alterationService;

    @GetMapping("/get")
    public List<Alteration> getAllAlterations() 
    {
        return alterationService.getAllAlterations();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAlterationById(@PathVariable Long id) 
    {
      if(alterationService.getAlterationById(id).isPresent())
      {
        return ResponseEntity.ok(alterationService.getAlterationById(id));
      }
        return ResponseEntity.status(404).body("No Alteration request is found");
    }


    @GetMapping("/get/provider")
    public List<Alteration> getAlterationsByProviderId(@RequestParam Long providerId) 
    {
        return alterationService.getAlterationsByProviderId(providerId);
    }

    @PostMapping("/post")
    public ResponseEntity<?> addAlteration(@RequestBody List<Alteration> alteration)
     {
        return alterationService.addAlteration(alteration);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAlteration(@PathVariable Long id, @RequestBody Alteration alteration)
     {
        return alterationService.updateAlteration(id, alteration);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlteration(@PathVariable Long id) 
    {
        return alterationService.deleteAlteration(id);
    }

    @DeleteMapping("/delete/all")
    public String deleteAllAlterations()
    {
        return alterationService.deleteAllAlterations();
    }

    @GetMapping("/page/sort")
    public Page<Alteration> getAllAlterations(@RequestParam(defaultValue = "0") int page,  @RequestParam(defaultValue = "5") int size,  @RequestParam(defaultValue = "cost") String field, @RequestParam(defaultValue = "asc") String way) 
    {
        return alterationService.getAllAlterations(page, size, field, way);
    }

    @GetMapping("/search/keyword")
    public List<Alteration> searchAlterationsDescription(@RequestParam String description) {
        return alterationService.searchAlterationByWord(description);
    }
    @GetMapping("/range/{c1}/{c2}")
    public List<Alteration>findAlterationsByCostRange(@PathVariable double c1, @PathVariable double c2)
    {
        return alterationService.findAlterationsByCostRange(c1, c2);
    }
    
}
