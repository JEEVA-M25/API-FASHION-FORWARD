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

import com.example.demo.model.Reward;
import com.example.demo.service.RewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/get")
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRewardById(@PathVariable Long id) {
        Optional<Reward> reward = rewardService.getRewardById(id);
        return reward.isPresent() ? ResponseEntity.ok(reward) : ResponseEntity.status(404).body("No Reward found");
    }

    @GetMapping("/get/user")
    public List<Reward> getRewardsByUserId(@RequestParam Long userId) {
        return rewardService.getRewardsByUserId(userId);
    }

    @GetMapping("/get/type")
    public List<Reward> getRewardsByRewardType(@RequestParam String rewardType) {
        return rewardService.getRewardsByRewardType(rewardType);
    }

    @GetMapping("/get/totalpoints")
    public ResponseEntity<?> getTotalPointsByUserId(@RequestParam Long userId) {
        Optional<Integer> totalPoints = rewardService.getTotalPointsByUserId(userId);
        return totalPoints.isPresent() ? ResponseEntity.ok("The total points earned by ther user with id "+userId+" = "+totalPoints.get()+" points") : ResponseEntity.ok("Seemes like the userId that you have entered has not been found\nSoThe user "+userId+" has 0 points");
    }

    @PostMapping("/post")
    public ResponseEntity<?> addRewards(@RequestBody List<Reward> rewards) {
        return rewardService.addRewards(rewards);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReward(@PathVariable Long id, @RequestBody Reward reward) {
        return rewardService.updateReward(id, reward);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReward(@PathVariable Long id) {
        return rewardService.deleteReward(id);
    }

    @DeleteMapping("/delete/all")
    public String deleteAllRewards() {
        return rewardService.deleteAllRewards();
    }

    @GetMapping("/page/sort")
    public Page<Reward> getAllRewards(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size,@RequestParam(defaultValue = "points") String field,@RequestParam(defaultValue = "asc") String way) {
        return rewardService.getAllRewards(page, size, field, way);
    }
}
