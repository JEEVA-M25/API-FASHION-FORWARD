package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository TRepo;

    public List<Transaction>addTransaction(List<Transaction>tsac)
    {
        List<Transaction>res= TRepo.saveAll(tsac);
        return res;
    }

    public List<Transaction> findAllTransaction()
   {
        return TRepo.findAll();
   }
   public Optional<Transaction> findTransactionById(Long id)
   {
        Optional<Transaction> tr= TRepo.findById(id);
        return tr;
   }
    public List<Transaction>findTransactionByUserId(Long userId)
    {
        return TRepo.findTransactionByUserId(userId);
    }
    public List<Transaction>findTransactionByClothingItemId(Long clothingItemId)
    {
        return TRepo.findTransactionByClothingItemId(clothingItemId);
    }
    public List<Transaction>findTransactionBySellerId(Long sellerId)
    {
        return TRepo.findTransactionBySellerId(sellerId);
    }
    public List<Transaction>findTransactionByAmount(Double amount)
    {
        return TRepo.findTransactionByAmount(amount);
    }
    public List<Transaction>findTransactionByStatus(String status)
    {
        return TRepo.findTransactionByStatus(status);
    } 
    public List<Transaction>findTransactionByTdate(LocalDate tdate)
    {
        return TRepo.findTransactionByTdate(tdate);
    }
    public Transaction updateTransaction(Long id, Transaction tsac)
    {
        Optional<Transaction> res= TRepo.findById(id);
        if(res.isPresent())
        {
            tsac.setId(id);
            return TRepo.save(tsac);
        }
        else
        throw new IllegalArgumentException("Give proper id");
    }

    public String deleteTransaction(Long id) {

        Optional<Transaction> tr = TRepo.findById(id); 
        if (tr.isPresent()) {
            TRepo.deleteById(id);
            return "The Transaction has been deleted successfully";
        }
        return "Give  proper ID to delete a transaction";
    }
    public Page<Transaction>getTransactionSorted(int page,int size, String field,String way)
    {
        Sort sort = way.equalsIgnoreCase("asc")?Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pages= PageRequest.of(page,size,sort);
        return TRepo.findAll(pages);
    }

    public Double findSumOfUserAmount(Long sellerId)
    {
        Double total = TRepo.findSumOfUserAmount(sellerId);
        return total!=null? total  : 0.0;
    }

    public String DeleteAllTransactions()
    {
        TRepo.deleteAll();
        return "All transactions have been deleted suucessfully";
    }

   
}
