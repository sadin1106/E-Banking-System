package com.se2.ebankingproject.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.se2.ebankingproject.Helper.CheckValidate;
import com.se2.ebankingproject.Helper.ErrorType;
import com.se2.ebankingproject.IService.ILoanService;
import com.se2.ebankingproject.Model.Loan;
import com.se2.ebankingproject.Repository.AccountRepository;
import com.se2.ebankingproject.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoanService implements ILoanService {

    @Autowired
    ErrorType errorType;

    @Autowired
    CheckValidate checkValidate;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    InvestTypeService investService;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Loan getLoan(int accountId) {
        if (investService.isEmpty(accountId)) {
            return null;
        } else {
            if (isEmpty(accountId)) {
                return null;
            }
            Loan lo = loanRepository.findById(investService.getInvest(accountId).getInvestTypeId()).get();
            return lo;
        }

    }

    @Override
    public String addLoan(String loan) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Loan lo = gson.fromJson(loan, Loan.class);
        if(!accountRepository.existsById(lo.getAccount().getAccountId())) {
            return errorType.isNotExisted("Account ");
        }
        if (investService.isEmpty(lo.getAccount().getAccountId())) {

            if (checkValidate.getOk(lo)) {
                loanRepository.save(lo);
                return errorType.getSuccessful();
            }
            return errorType.isValidated(lo.getInvestTypeId());
        }
        return errorType.isExisted("invest");
    }

    @Override
    public String deleteLoan(int accountId) {
        if (investService.isEmpty(accountId)) {
            return errorType.isNotExisted("loan");

        }
        Loan loan = getLoan(accountId);
        if (loan.getAmount() == 0) {
            investService.deleteInvestType(accountId);
            return errorType.getSuccessful();
        }
        return errorType.getFail("loan ammount is not equal 0");

    }

    @Override
    public String editLoan(String loan) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Loan lo = gson.fromJson(loan, Loan.class);
        if (isEmpty(lo.getAccount().getAccountId())) {
            return errorType.isNotExisted("loan");
        } else {

            if (checkValidate.getOk(lo)) {
                Loan loanCurrent = getLoan(lo.getAccount().getAccountId());
                if (lo.getAmount() != loanCurrent.getAmount())
                    loanCurrent.setAmount(lo.getAmount());
                if (lo.getDescription() != null)
                    loanCurrent.setDescription(lo.getDescription());
                if (lo.getDuration() != loanCurrent.getDuration())
                    loanCurrent.setDuration(lo.getDuration());
                if (lo.getRate() != loanCurrent.getRate())
                    loanCurrent.setRate(lo.getRate());
                loanCurrent.setTimeCreated(LocalDateTime.now());
                loanRepository.save(loanCurrent);
                return errorType.getSuccessful();
            }
            return errorType.isValidated("loan");
        }
    }

    @Override
    public boolean isEmpty(int accountId) {
        if (loanRepository.existsById(investService.getInvest(accountId).getInvestTypeId())) {
            return false;
        }
        return true;
    }

}
