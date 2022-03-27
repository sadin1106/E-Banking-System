package com.se2.ebankingproject.IService;

import com.se2.ebankingproject.Model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITransactionService {

    public Transaction getTransactionById(int transactionId);

    public List<Transaction> getAllTransactions();

    public String addTransaction(String transaction);

    public String deleteTransactionById(int transactionId);

    public String editTransaction(String transaction);

    public List<Transaction> getTransactionsByAccount(int accountId);
}
