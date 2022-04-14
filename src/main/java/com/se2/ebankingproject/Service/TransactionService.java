package com.se2.ebankingproject.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.se2.ebankingproject.Helper.CheckValidate;
import com.se2.ebankingproject.Helper.ErrorType;
import com.se2.ebankingproject.IService.ITransactionService;
import com.se2.ebankingproject.Model.Account;
import com.se2.ebankingproject.Model.Transaction;
import com.se2.ebankingproject.Repository.AccountRepository;
import com.se2.ebankingproject.Repository.GiftRepository;
import com.se2.ebankingproject.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    ErrorType errorType;

    @Autowired
    CheckValidate checkValidate;

    @Override
    public Transaction getTransactionById(int transactionId) {
        if (transactionRepository.existsById(transactionId))
            return transactionRepository.findById(transactionId).get();
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }

    @Override
    public String addTransaction(String transaction) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Transaction tran = gson.fromJson(transaction, Transaction.class);

        if (accountRepository.existsById(tran.getAccount().getAccountId())) {
            if (!transactionRepository.existsById(tran.getTransactionId())) {
                if (checkValidate.getOk(tran)) {

                    transactionRepository.save(tran);
                    return errorType.getSuccessful();
                }
                return "Transaction " + errorType.isValidated(tran.getTransactionId());
            }
            return editTransaction(transaction);
        }
        return "Account" + errorType.isNotExisted(tran.getAccount().getAccountId());
    }

    @Override
    public String deleteTransactionById(int transactionId) {

        if (transactionRepository.existsById(transactionId)) {
            giftRepository.deleteById(transactionId);
            transactionRepository.deleteById(transactionId);
            return errorType.getSuccessful();
        }
        return "Transaction " + errorType.isNotExisted(transactionId + "");
    }

    @Override
    public String editTransaction(String transaction) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Transaction tran = gson.fromJson(transaction, Transaction.class);

        if (transactionRepository.existsById(tran.getTransactionId())) {
            if (checkValidate.getOk(tran)) {
                Transaction fixTran = getTransactionById(tran.getTransactionId());

                transactionRepository.save(fixTran);
                return errorType.getSuccessful();
            }
            return "Transaction " + errorType.isValidated(tran.getTransactionId());
        }

        return "Transaction " + errorType.isNotExisted(tran.getTransactionId());
    }

    @Override
    public List<Transaction> getTransactionsByAccount(int accountId) {
        Account acc = accountService.findById(accountId);
        List<Transaction> trans = new ArrayList<Transaction>();

        if(acc != null)
        {
            acc.getTransactions().forEach(x -> trans.add(x));
        }
        return trans;
    }
}
