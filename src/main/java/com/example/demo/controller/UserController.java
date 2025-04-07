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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

// import jakarta.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userSer;
    
    @PostMapping("/post")
    public ResponseEntity<?>addUser(@RequestBody List<User>users)
    {
        return userSer.addUser(users);
    }
    
    @GetMapping("/get")
    public List<User> getAllUsers()
    {
        return userSer.showUsers();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        return userSer.findById(id);
    }

    @GetMapping("/get/username")
    public ResponseEntity<?>findByUsername (@RequestParam String username)
    {
        return userSer.findByUsername(username);
    }
    
    @GetMapping("/get/email")
    public ResponseEntity<?>findByEmail(@RequestParam String email)
    {
        return userSer.findByEmail(email);
    }
    
    @GetMapping("/get/name") //Custom jpa method
    // public List<User>GetByName(@RequestParam String name)
    // {
    //     return userSer.GetByname(name);
    // }
    public ResponseEntity<?>findByName (@RequestParam String name)
    {
        return userSer.findByName(name);
    } 

    // @GetMapping("/gets/users/cloth")
    // public ResponseEntity<?>findUsersByClothingItems (@RequestParam String cloth)
    // {
    //     return userSer.findUsersByClothingItems(cloth);
    // }
    @GetMapping("/pages")
    public Page<User> getUser(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size)
    {
        return userSer.getUser(page, size);
    }
    @GetMapping("/page/sort")
    public Page<User> GetPagesSorted(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size, @RequestParam(defaultValue="name") String field, @RequestParam(defaultValue="asc") String way)
    {
        return userSer.getUserSorted(page, size, field, way);
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<?>updateUser (@PathVariable Long id, @RequestBody User user)
    {
        return userSer.updateUser(id,user);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id)
    {
        return userSer.deleteUser(id);
    }
    @DeleteMapping("delete/all")
    public String DeleteAllUsers()
    {
        return userSer.deleteAllUsers();
    }
    @DeleteMapping("delete/username")
    public String deleteUserByUsername(@RequestParam String username)
    {
       return userSer.deleteUserByUsername(username);
    }
}