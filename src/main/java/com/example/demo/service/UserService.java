package com.example.demo.service;

import java.util.ArrayList;
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
import com.example.demo.model.ClothingItem;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.AlterationRepository;
import com.example.demo.repository.ClothingItemRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    AlterationRepository alterationRepository;

    @Autowired
    ClothingItemRepository clothingItemRepo;

    private static final String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String passReg = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{6,}$";

    @Transactional
    public ResponseEntity<?> addUser(List<User> users) {
        for (User user : users) {
            Optional<User> Duname = userRepo.findByUsername(user.getUsername());
            Optional<User> Demail = userRepo.findByEmail(user.getEmail());
            
            if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email shouldn't be empty");
            }
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password shouldn't be empty");
            }
            if (!user.getEmail().matches(emailReg)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email format");
            }
            if (!user.getPassword().matches(passReg)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password must be at least 6 characters long and contain at least :-\n" +
                        "one capital letter\none small letter\none number\none special character");
            }
            if (Duname.isPresent() && Demail.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and email already exist. Choose another.");
            }
            if (Duname.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists. Choose another.");
            }
            if (Demail.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists. Choose another.");
            }
        }
    
        // Save Users first
        List<User> savedUsers = userRepo.saveAll(users);
    
        // Save transactions associated with each user
        for (User user : savedUsers) {
            if (user.getTransactions() != null && !user.getTransactions().isEmpty()) {
                for (Transaction transaction : user.getTransactions()) {
                    transaction.setUser(user); // Set the user reference in each transaction
                }
                transactionRepo.saveAll(user.getTransactions()); // Save all transactions
            }
        }

    List<Alteration> allAlterations = new ArrayList<>();
    for (User user : savedUsers) {
        if (user.getAlterations() != null && !user.getAlterations().isEmpty()) {
            for (Alteration alteration : user.getAlterations()) {
                alteration.setUser(user); // Link alteration to user
                allAlterations.add(alteration);
            }
        }
    }
    alterationRepository.saveAll(allAlterations); // Explicitly save all alterations


     List<ClothingItem> allClothingItems = new ArrayList<>();
    for (User user : savedUsers) {
        if (user.getClothingItems() != null && !user.getClothingItems().isEmpty()) {
            for (ClothingItem clothingItem : user.getClothingItems()) {
                clothingItem.setUser(user); // Ensure clothing item is linked to user
                allClothingItems.add(clothingItem);
            }
        }
    }
    clothingItemRepo.saveAll(allClothingItems);
    
        return ResponseEntity.ok(savedUsers);
    }
    

    public List<User> showUsers()
    {
        return userRepo.findAll();
    }
    public ResponseEntity<?> findById(Long id)
    {
        Optional<User>found= userRepo.findById(id);
        if(found.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user has been found with the id -> "+id);   
        }
        return ResponseEntity.ok(found);
    }
    public ResponseEntity<?>findByUsername ( String username)
   {
       Optional<User>result = userRepo.findByUsername(username);
       if(result.isEmpty())
       {
       //   return ResponseEntity.ok("No user have been found with the username -> "+username); 
       //  OK
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user has been found with the username -> "+username);   
       }
       return ResponseEntity.ok(result);
   }
    public ResponseEntity<?>findByEmail ( String email)
   {
       Optional<User>result = userRepo.findByEmail(email);
       if(result.isEmpty())
       {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user has been found with the email -> "+email);   
       }
       return ResponseEntity.ok(result);
   }
   
    public ResponseEntity<?>findByName ( String name)
   {
       List<User>result = userRepo.findByName(name);
       if(result.isEmpty())
       {
       //   return ResponseEntity.ok("No users have been found with the name -> "+name);
       //   OK 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users have been found with the name -> "+name);
       }
       return ResponseEntity.ok(result);
   }
//     public ResponseEntity<?>findUsersByClothingItems ( String clothingItems)
//    {
//        List<User>result = userRepo.findUsersByClothingItems(clothingItems);
//        if(result.isEmpty())
//        {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users have been found with the clothing item that has been given by you\nSo You gotta give another clothing item instead of giving\n -> - - - "+clothingItems+" is not matching");
//        }
//        return ResponseEntity.ok(result);
//    }

    public Page<User> getUser(int page,int size)
    {
      Pageable pages = PageRequest.of(page,size);
        return userRepo.findAll(pages);
    }

    public Page<User> getUserSorted(int page,int size, String field, String way)
    {
        Sort sorted = way.equalsIgnoreCase("asc")?Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pages = PageRequest.of(page, size, sorted);
        return userRepo.findAll(pages);
    }

    public ResponseEntity<?> updateUser(Long id,User user)
    {
        Optional<User>found= userRepo.findById(id);
        if(found.isPresent()) 
        {
            user.setId(id);
            User uUser = userRepo.save(user); 
            return ResponseEntity.ok(uUser);
        } 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
    }
    public ResponseEntity<?> deleteUser(Long id)
    {
        Optional<User>found= userRepo.findById(id);
        if(found.isPresent()) 
        {
            userRepo.deleteById(id);
            return ResponseEntity.ok("User with id-> "+id+" has bdeen deleted successfully");   
        } 
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found to delete that particular id");
    
    }
    public String deleteAllUsers()
    {
        userRepo.deleteAll();
        return "All Users have been deleted successfully";
    }
    public String deleteUserByUsername(String username)
    {
        Optional<User> found= userRepo.findByUsername(username);
        if(found.isPresent())
        {
            userRepo.deleteByUsername(username);
            return "Username "+username+" deleted Successfully";
        }
        return "Username "+username+" is not found";
    }
    
}