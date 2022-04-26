package com.se2.ebankingproject.Controller;

import com.se2.ebankingproject.Model.Transaction;
import com.se2.ebankingproject.ServiceImpl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    final String org = "http://127.0.0.1:5500"; //Visual Studio Live Server

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    @PostMapping(value = "/transaction/addTransaction")
    @CrossOrigin(origins = org)
    public String addTransaction(@RequestBody String transaction)
    {
        return transactionServiceImpl.addTransaction(transaction);
    }

    @DeleteMapping(value = "/transaction/deleteTransactionById")
    @CrossOrigin(origins = org)
    public String deleteTransactionById(@RequestParam int transactionId)
    {
        return transactionServiceImpl.deleteTransactionById(transactionId);
    }

    @GetMapping(value = "/transaction/getTransactionById")
    @CrossOrigin(origins = org)
    public Transaction getTransactionById(@RequestParam int transactionId)
    {
        return transactionServiceImpl.getTransactionById(transactionId);
    }

    @GetMapping(value = "/transaction/getAllTransactions")
    @CrossOrigin(origins = org)
    public List<Transaction> getAllTransactions()
    {
        return transactionServiceImpl.getAllTransactions();
    }

}
