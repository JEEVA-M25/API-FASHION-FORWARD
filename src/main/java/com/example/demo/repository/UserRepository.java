package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long>{
     
    List<User>findByName(String name);
    Optional<User>findByUsername(String username);
    Optional<User>findByEmail(String email);

    @Transactional
    void deleteByUsername(String username);

    // @Query(value = "SELECT * FROM user u WHERE u.clothing_items LIKE CONCAT('%', :clothName, '%')", nativeQuery = true)
    // List<User> findUsersByClothingItems(@Param("clothName") String clothName);

}