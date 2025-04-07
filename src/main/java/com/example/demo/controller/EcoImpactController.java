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

import com.example.demo.model.EcoImpact;
import com.example.demo.service.EcoImpactService;

@RestController
@RequestMapping("/ecoimpact")
public class EcoImpactController {

    @Autowired
    private EcoImpactService ecoImpactService;

    @GetMapping("/get")
    public List<EcoImpact> getAllEcoImpacts() {
        return ecoImpactService.getAllEcoImpacts();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEcoImpactById(@PathVariable Long id) {
        Optional<EcoImpact> impact = ecoImpactService.getEcoImpactById(id);
        return impact.isPresent() ? ResponseEntity.ok(impact) : ResponseEntity.status(404).body("No Eco Impact found");
    }

    @GetMapping("/get/user")
    public List<EcoImpact> getEcoImpactByUserId(@RequestParam Long userId) {
        return ecoImpactService.getEcoImpactByUserId(userId);
    }

    @PostMapping("/post")
    public ResponseEntity<?> addEcoImpact(@RequestBody List<EcoImpact> ecoImpacts) {
        return ecoImpactService.addEcoImpact(ecoImpacts);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEcoImpact(@PathVariable Long id, @RequestBody EcoImpact ecoImpact) {
        return ecoImpactService.updateEcoImpact(id, ecoImpact);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEcoImpact(@PathVariable Long id) {
        return ecoImpactService.deleteEcoImpact(id);
    }

    @DeleteMapping("/delete/all")
    public String deleteAllEcoImpacts() {
        return ecoImpactService.deleteAllEcoImpacts();
    }

    @GetMapping("/page/sort")
    public Page<EcoImpact> getAllEcoImpacts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size,@RequestParam(defaultValue = "carbonFootprintReduction") String field,@RequestParam(defaultValue = "asc") String way) 
    {
        return ecoImpactService.getAllEcoImpacts(page, size, field, way);
    }

    
}
