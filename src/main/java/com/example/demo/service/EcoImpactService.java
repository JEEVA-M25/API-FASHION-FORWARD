package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.EcoImpact;
import com.example.demo.repository.EcoImpactRepository;

import jakarta.transaction.Transactional;

@Service
public class EcoImpactService {

    @Autowired
    private EcoImpactRepository ecoImpactRepository;

    public List<EcoImpact> getAllEcoImpacts() {
        return ecoImpactRepository.findAll();
    }

    public Optional<EcoImpact> getEcoImpactById(Long id) {
        return ecoImpactRepository.findById(id);
    }

    public List<EcoImpact> getEcoImpactByUserId(Long userId) {
        return ecoImpactRepository.findByUserId(userId);
    }


    @Transactional
    public ResponseEntity<?> addEcoImpact(List<EcoImpact> ecoImpacts) {
        for (EcoImpact impact : ecoImpacts) {
            Optional<EcoImpact> existingImpact = ecoImpactRepository.findByUserId(impact.getUser().getId()).stream().findFirst();

            if (existingImpact.isPresent()) {
                EcoImpact existing = existingImpact.get();
                existing.setItemsRecycled(impact.getItemsRecycled());
                existing.setItemsRented(impact.getItemsRented());
                existing.setItemsSwapped(impact.getItemsSwapped());
                existing.setCarbonFootprintReduction(impact.getCarbonFootprintReduction());
                existing.setLastUpdated(impact.getLastUpdated());
                ecoImpactRepository.save(existing);
            } else {
                ecoImpactRepository.save(impact);
            }
        }
        return ResponseEntity.ok("Eco Impact records processed successfully.");
    }

    public ResponseEntity<?> updateEcoImpact(Long id, EcoImpact ecoImpact) {
        if (ecoImpactRepository.existsById(id)) {
            ecoImpact.setId(id);
            return ResponseEntity.ok(ecoImpactRepository.save(ecoImpact));
        }
        return ResponseEntity.status(404).body("Eco Impact not found");
    }

    public ResponseEntity<?> deleteEcoImpact(Long id) {
        Optional<EcoImpact> impactOpt = ecoImpactRepository.findById(id);
        if (impactOpt.isPresent()) {
            EcoImpact impact = impactOpt.get();
            impact.getUser().setEcoImpact(null);  // Remove reference from User
            ecoImpactRepository.delete(impact);   // Delete EcoImpact
            return ResponseEntity.ok("Eco Impact deleted successfully");
        }
        return ResponseEntity.status(404).body("Eco Impact not found");
    }
    

    public String deleteAllEcoImpacts() {
        ecoImpactRepository.deleteAll();
        return "All Eco Impact records deleted";
    }

    public Page<EcoImpact> getAllEcoImpacts(int page, int size, String field, String way) {
        Sort sort = way.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        return ecoImpactRepository.findAll(PageRequest.of(page, size, sort));
    }

    
}
