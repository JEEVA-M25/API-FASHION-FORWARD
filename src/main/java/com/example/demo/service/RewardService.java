package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reward;
import com.example.demo.repository.RewardRepository;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Optional<Reward> getRewardById(Long id) {
        return rewardRepository.findById(id);
    }

    public List<Reward> getRewardsByUserId(Long userId) {
        return rewardRepository.findByUserId(userId);
    }

    public List<Reward> getRewardsByRewardType(String rewardType) {
        return rewardRepository.findByRewardType(rewardType);
    }

    public ResponseEntity<?> addRewards(List<Reward> rewards) {
        List<Reward> savedRewards = rewardRepository.saveAll(rewards);
        return ResponseEntity.ok(savedRewards);
    }

    public ResponseEntity<?> updateReward(Long id, Reward reward) {
        if (rewardRepository.existsById(id)) {
            reward.setId(id);
            return ResponseEntity.ok(rewardRepository.save(reward));
        }
        return ResponseEntity.status(404).body("Reward not found");
    }

    public ResponseEntity<?> deleteReward(Long id) {
        if (rewardRepository.existsById(id)) {
            rewardRepository.deleteById(id);
            return ResponseEntity.ok("Reward deleted successfully");
        }
        return ResponseEntity.status(404).body("Reward not found");
    }

    public String deleteAllRewards() {
        rewardRepository.deleteAll();
        return "All Reward records deleted";
    }

    public Page<Reward> getAllRewards(int page, int size, String field, String way) {
        Sort sort = way.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        return rewardRepository.findAll(PageRequest.of(page, size, sort));
    }

    public Optional<Integer> getTotalPointsByUserId(Long userId) {
        return rewardRepository.findTotalPointsByUserId(userId);
    }
}
