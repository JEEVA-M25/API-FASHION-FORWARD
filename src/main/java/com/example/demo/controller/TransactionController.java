package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService Tser;

   @PostMapping("/post")
     public List<Transaction> addTransaction(@RequestBody List<Transaction> tsac)
     {
       return Tser.addTransaction(tsac);
     }
   @GetMapping("/get")
   public List<Transaction> findAllTransaction()
   {
        return Tser.findAllTransaction();
   }
   @GetMapping("/get/{id}")
   public Optional<Transaction> findTransactionById(@PathVariable Long id)
   {
        return Tser.findTransactionById(id);
   }
   
   @GetMapping("/get/userid")
   public List<Transaction>  findTransactionByUserId(@RequestParam Long userId)
   {
    return Tser.findTransactionByUserId(userId);
   }
   @GetMapping("/get/cid")
   public List<Transaction>  findTransactionByClothingItemId(@RequestParam Long clothingItemId)
   {
    return Tser.findTransactionByClothingItemId(clothingItemId);
   }
   @GetMapping("/get/seller")
   public List<Transaction>  findTransactionBySellerId(@RequestParam Long sellerId)
   {
    return Tser.findTransactionBySellerId(sellerId);
   }
   @GetMapping("/get/amount")
   public List<Transaction>  findTransactionByAmount(@RequestParam Double amount)
   {
    return Tser.findTransactionByAmount(amount);
   }
   @GetMapping("/get/status")
   public List<Transaction>  findTransactionByStatus(@RequestParam String status)
   {
    return Tser.findTransactionByStatus(status);
   }
   @GetMapping("/get/date")
   public List<Transaction> findTransactionByTdate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tdate) {
       return Tser.findTransactionByTdate(tdate);
   }
   
   @PutMapping("/put/{id}")
    public Transaction updateTransactionById(@PathVariable Long id,@RequestBody Transaction tsac)
    {
        return Tser.updateTransaction(id,tsac);
    }
   @DeleteMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id)
     {
    return Tser.deleteTransaction(id);
    }

    @GetMapping("/page/sort")
    public Page<Transaction> GetPagesSorted(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size, @RequestParam(defaultValue="amount") String field, @RequestParam(defaultValue="asc") String way)
    {
        return Tser.getTransactionSorted(page, size, field, way);
    }

    @GetMapping("/get/salesTotal")
    public ResponseEntity<?> findSumOfUserAmount(@RequestParam Long sellerId)
    {
        if(Tser.findSumOfUserAmount(sellerId)==0.0)
        {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hey.. The seller hasn't sold any item yet!!\nHis networth is 0.0 $");
        }
        return ResponseEntity.ok("The transaction amount that the seller with id "+sellerId+" = "+Tser.findSumOfUserAmount(sellerId)+" $");
    }
    
    @DeleteMapping("delete/all")
    public String DeleteAllTransactions()
    {
      return Tser.DeleteAllTransactions();
    }
    
}
