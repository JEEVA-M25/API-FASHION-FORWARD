package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {

    List<Reward> findByUserId(Long userId);
    List<Reward> findByRewardType(String rewardType);

    @Query("SELECT SUM(r.points) FROM Reward r WHERE r.userId = :userId")
    Optional<Integer> findTotalPointsByUserId(Long userId);
}
