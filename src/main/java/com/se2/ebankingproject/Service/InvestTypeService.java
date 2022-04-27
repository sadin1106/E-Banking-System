package com.se2.ebankingproject.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.se2.ebankingproject.Helper.CheckValidate;
import com.se2.ebankingproject.Helper.ErrorType;
import com.se2.ebankingproject.IService.IInvestService;
import com.se2.ebankingproject.Model.Account;
import com.se2.ebankingproject.Model.InvestType;
import com.se2.ebankingproject.Repository.AccountRepository;
import com.se2.ebankingproject.Repository.InvestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InvestTypeService implements IInvestService {

    @Autowired
    ErrorType errorType;

    @Autowired
    CheckValidate checkValidate;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    InvestTypeRepository investTypeRepository;

    @Override
    public InvestType getInvest(int accountId) {
        if (accountRepository.existsById(accountId)) {
            Account acc = accountRepository.findById(accountId).get();
            InvestType iv = acc.getInvestType();
            return iv;
        }
        return null;
    }

    @Override
    public String addInvestType(String investType) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        InvestType it = gson.fromJson(investType, InvestType.class);

        if (isEmpty(it.getAccount().getAccountId())) {
            if (checkValidate.getOk(it)) {
                it.setTimeCreated(LocalDateTime.now());
                investTypeRepository.save(it);
                return errorType.getSuccessful();
            }
            return errorType.isValidated(it.getInvestTypeId());
        }

        return errorType.isExisted(it.getClass().getSimpleName());
    }

    @Override
    public String deleteInvestType(int accountId) {
        if (isEmpty(accountId)) {
            return errorType.isEmpty();
        }
        Account acc = accountRepository.findById(accountId).get();
        investTypeRepository.delete(acc.getInvestType());
        return errorType.getSuccessful();
    }

    @Override
    public String editInvestType(String investType) {
        return null;
    }

    @Override
    public boolean isEmpty(int accountId) {
        if (getInvest(accountId) == null)
            return true;
        return false;
    }
}