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

import com.example.demo.model.Alteration;
import com.example.demo.repository.AlterationRepository;

@Service
public class AlterationService {

    @Autowired
    private AlterationRepository alterationRepository;

    public List<Alteration> getAllAlterations() 
    {
        return alterationRepository.findAll();
    }

    public Optional<Alteration> getAlterationById(Long id) 
    {
        return alterationRepository.findById(id);
    }

    public List<Alteration> getAlterationsByProviderId(Long providerId) 
    {
        return alterationRepository.findByAlterationProviderId(providerId);
    }

    public ResponseEntity<?> addAlteration(List<Alteration> alteration) 
    {
        
        return ResponseEntity.ok(alterationRepository.saveAll(alteration));
    }

    public ResponseEntity<?> updateAlteration(Long id, Alteration updatedAlteration) 
    {
        Optional<Alteration> existingAlteration = alterationRepository.findById(id);

        if (existingAlteration.isPresent()) {
            updatedAlteration.setId(id);
            return ResponseEntity.ok(alterationRepository.save(updatedAlteration));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Alteration with ID " + id + " not found.");
    }

    public ResponseEntity<?> deleteAlteration(Long id) 
    {
        if (alterationRepository.existsById(id)) {
            alterationRepository.deleteById(id);
            return ResponseEntity.ok("Alteration with ID " + id + " CANCELLED successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Alteration with ID " + id + " not found.");
    }
    public String deleteAllAlterations()
    {
        alterationRepository.deleteAll();
        return "All Alteration Requests are deleted successfully";
    }

    public Page<Alteration> getAllAlterations(int page, int size, String field, String way) {
        Sort sort = way.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return alterationRepository.findAll(pageable); // Using findAll(Pageable) for pagination and sorting
    }

    public List<Alteration>searchAlterationByWord(String description)
    {
        return alterationRepository.findAlterationsByDescription(description);
    }
    public List<Alteration>findAlterationsByCostRange(double c1,double c2)
    {
        return alterationRepository.findAlterationsByCostRange(c1, c2);
    }


}
