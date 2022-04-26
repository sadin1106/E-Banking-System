package com.se2.ebankingproject.Service;

import com.se2.ebankingproject.Model.Transaction;

import java.util.List;

public interface TransactionService {

    public Transaction getTransactionById(int transactionId);

    public List<Transaction> getAllTransactions();

    public String addTransaction(String transaction);

    public String deleteTransactionById(int transactionId);

    public String editTransaction(String transaction);

    public List<Transaction> getTransactionsByAccount(int accountId);
}
