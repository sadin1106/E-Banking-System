package com.se2.ebankingproject.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.se2.ebankingproject.Helper.CheckValidate;
import com.se2.ebankingproject.Helper.ErrorType;
import com.se2.ebankingproject.IService.IGiftService;
import com.se2.ebankingproject.Model.Account;
import com.se2.ebankingproject.Model.Gift;
import com.se2.ebankingproject.Repository.AccountRepository;
import com.se2.ebankingproject.Repository.GiftRepository;
import com.se2.ebankingproject.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiftService implements IGiftService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ErrorType errorType;

    @Autowired
    CheckValidate checkValidate;

    @Override
    public Gift getGiftById(int giftId) {
        if(giftRepository.existsById(giftId))
            return giftRepository.findById(giftId).get();
        return null;
    }

    @Override
    public List<Gift> getAllGift() {

        return giftRepository.findAll();
    }

    @Override
    public String deleteGiftById(int giftId) {
        if (giftRepository.existsById(giftId)) {
            giftRepository.deleteById(giftId);
            return errorType.getSuccessful();
        }
        return "Gift " + errorType.isNotExisted(giftId + "");
    }

    @Override
    public String addGift(String gift) {

        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Gift gi = gson.fromJson(gift, Gift.class);

        if (accountRepository.existsById(gi.getAccount().getAccountId())) {

            if (!giftRepository.existsById(gi.getTransactionId())) {
                if (checkValidate.getOk(gi)) {
                    Account acc = accountRepository.findById(gi.getAccount().getAccountId()).get();
                    Account toAccount = accountRepository.findById(gi.getToAccount().getAccountId()).get();
                    if(toAccount == null) {
                        return errorType.isNotExisted("Account "+ toAccount.getAccountId());
                    }
                    if(acc == null) {
                        return errorType.isNotExisted("Account " + acc.getAccountId());
                    }
                    if(gi.getAmount() > 0) {
                        acc.setBalance(acc.getBalance()-gi.getAmount());
                        toAccount.setBalance(toAccount.getBalance()+gi.getAmount());
                    }

                    giftRepository.save(gi);
                    return errorType.getSuccessful();
                }
                return "Gift " + errorType.isValidated(gi.getTransactionId() + "");
            }
            return editGift(gift);

        }

        return "Account" + errorType.isNotExisted(gi.getAccount().getAccountId() + "");
    }

    @Override
    public String editGift(String gift) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Gift gi = gson.fromJson(gift, Gift.class);

        if (giftRepository.existsById(gi.getTransactionId())) {
            if (checkValidate.getOk(gi)) {
                Gift fixGift = getGiftById(gi.getTransactionId());
                giftRepository.save(fixGift);
                return errorType.getSuccessful();
            }
            return "Gift " + errorType.isValidated(gi.getTransactionId() + "");
        }
        return "Gift " + errorType.isNotExisted(gi.getTransactionId() + "");
    }

    @Override
    public List<Gift> getGiftsByAccount(int accountId) {
        Account acc = accountService.findById(accountId);
        List<Gift> gifts = new ArrayList<Gift>();
        if(acc != null)
        {
            acc.getTransactions().forEach(x -> {
                if(giftRepository.existsById(x.getTransactionId()))
                {
                    gifts.add(getGiftById(x.getTransactionId()));
                }
            });
        }
        return gifts;
    }


}