package com.se2.ebankingproject.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.se2.ebankingproject.Helper.CheckProperty;
import com.se2.ebankingproject.Helper.CheckValidate;
import com.se2.ebankingproject.Helper.ErrorType;
import com.se2.ebankingproject.IService.IAccountService;
import com.se2.ebankingproject.Model.Account;
import com.se2.ebankingproject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    NormalRepository normalRepository;

    @Autowired
    SysAdminRepository sysAdminRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    NormalService normalService;

    @Autowired
    ErrorType errorType;

    @Autowired
    CheckValidate checkValidate;

    @Autowired
    CheckProperty checkProperty;

    @Autowired
    InvestTypeService investTypeService;

    @Override
    public Account findById(int accountId) {
        if (accountRepository.existsById(accountId)) {
            return accountRepository.findById(accountId).get();
        }
        return null;
    }

    @Override
    public List<Account> findAll() {

        return accountRepository.findAll();
    }

    @Override
    public String deleteAccountById(int accountId) {
        if (accountRepository.existsById(accountId)) {
            Account acc = accountRepository.findById(accountId).get();
            acc.getTransactions().forEach(x -> transactionService.deleteTransactionById(x.getTransactionId()));
            investTypeService.deleteInvestType(acc.getAccountId());
            accountRepository.deleteById(accountId);

            return errorType.getSuccessful();
        }
        return "Account " + errorType.isNotExisted(accountId);
    }

    @Override
    public String addAccount(String account) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Account acc = gson.fromJson(account, Account.class);
        if (!accountRepository.existsById(acc.getAccountId())) {
            if (checkValidate.getOk(acc)) {
                acc.setTimeCreated(LocalDateTime.now());

                if (acc.getRole() == null || acc.getRole().equalsIgnoreCase("normal")) {
                    acc.setRole("normal");
                    normalService.addNormal(account);
                }

                return errorType.getSuccessful();
            }

            return errorType.isValidated(acc.getName());
        }
        return editAccount(account);
    }

    @Override
    public String editAccount(String account) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Account acc = gson.fromJson(account, Account.class);

        if (accountRepository.existsById(acc.getAccountId())) {
            if (checkValidate.getOk(acc)) {
                Account fixAccount = findById(acc.getAccountId());
                if (acc.getAvatar() != null) fixAccount.setAvatar(acc.getAvatar());
                if (acc.getBio() != null) fixAccount.setBio(acc.getBio());
                if (acc.getDateOfBirth() != null) fixAccount.setDateOfBirth(acc.getDateOfBirth());
                if (acc.getName() != null) fixAccount.setName(acc.getName());
                if (acc.getPhoneNumber() != fixAccount.getPhoneNumber())
                    fixAccount.setPhoneNumber(acc.getPhoneNumber());
                accountRepository.save(fixAccount);
                return errorType.getSuccessful();
            }
            return errorType.isValidated(acc.getName());
        }
        return errorType.isNotExisted(acc.getName());
    }

    @Override
    public Account getAccountByUserName(String userName) {

        for (Account acc : accountRepository.findAll()) {
            if (acc.getUserName().equalsIgnoreCase(userName)) {
                return acc;
            }
        }
        return null;
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        for (Account x : accountRepository.findAll()) {
            if (x.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Account getAccountByPhoneNumber(String phoneNumber) {
        for (Account x : accountRepository.findAll()) {
            if (x.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return x;
            }
        }
        return null;
    }
}
