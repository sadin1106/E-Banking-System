package com.se2.ebankingproject.Controller;

import com.se2.ebankingproject.IService.IAccountService;
import com.se2.ebankingproject.Model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    final String org = "http://127.0.0.1:5500"; //Visual Studio Live Server

    @Autowired
    IAccountService accountService;

    @PostMapping(value = "/account/addAccount")
    @CrossOrigin(origins = org)
    public String addAccount(@RequestBody String account) {

        return accountService.addAccount(account);
    }

    @GetMapping(value = "/account/getAllAccounts")
    @CrossOrigin(origins = org)
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping(value = "/account/getAccountById")
    @CrossOrigin(origins = org)
    public Account getAccountById(@RequestParam int accountId) {
        return accountService.findById(accountId);
    }

    @PutMapping(value = "/account/editAccount")
    @CrossOrigin(origins = org)
    public String editAccount(@RequestBody String account) {
        return accountService.editAccount(account);
    }

    @DeleteMapping(value = "/account/deleteAccountById")
    @CrossOrigin(origins = org)
    public String deleteAccountById(@RequestParam int accountId) {
        return accountService.deleteAccountById(accountId);
    }

    @GetMapping(value = "/account/getAccountByUserName")
    @CrossOrigin(origins = org)
    public Account getAccountByUserName(@RequestParam String userName) {
        return accountService.getAccountByUserName(userName);
    }

    @GetMapping(value = "/account/checkPhoneNumber")
    @CrossOrigin(origins = org)
    public boolean checkAccountByPhoneNumber(@RequestParam String phoneNumber) {
        return accountService.checkPhoneNumber(phoneNumber);
    }

    @GetMapping(value = "/account/getAccountByPhoneNumber")
    @CrossOrigin(origins = org)
    public Account getAccountByPhoneNumber(String phoneNumber) {
        return accountService.getAccountByPhoneNumber(phoneNumber);
    }
}
