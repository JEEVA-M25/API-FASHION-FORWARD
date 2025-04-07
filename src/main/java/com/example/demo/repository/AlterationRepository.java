package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Alteration;

public interface AlterationRepository extends  JpaRepository<Alteration, Long> {

    List<Alteration> findByAlterationProviderId(Long alterationProviderId);
    List<Alteration> findByClothingItemId(Long clothingItemId);


    @Query("SELECT a FROM Alteration a WHERE LOWER(a.description) LIKE LOWER(CONCAT('%',:des,'%'))")
    List<Alteration>findAlterationsByDescription(@Param("des")String description);

    @Query("select s from Alteration s where s.cost between :minc and :maxc")
    List<Alteration>findAlterationsByCostRange(@Param("minc")double c1, @Param("maxc") double c2);

}
