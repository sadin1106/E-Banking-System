package com.se2.ebankingproject.IService;

import com.se2.ebankingproject.Model.Account;

import java.util.List;

public interface IAccountService {

    public Account findById(int accountId) ;

    public List<Account> findAll();

    public String deleteAccountById(int accountId);

    public String addAccount(String account);

    public String editAccount(String account);

    public Account getAccountByUserName(String userName);

    public boolean checkPhoneNumber(String phoneNumber);

    public Account getAccountByPhoneNumber(String phoneNumber);
}
