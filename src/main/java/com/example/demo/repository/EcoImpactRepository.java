package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EcoImpact;

@Repository
public interface EcoImpactRepository extends JpaRepository<EcoImpact, Long> {

    List<EcoImpact> findByUserId(Long userId);

}
