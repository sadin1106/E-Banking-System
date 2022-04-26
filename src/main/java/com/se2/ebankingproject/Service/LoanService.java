package com.se2.ebankingproject.Service;

import com.se2.ebankingproject.Model.Loan;

public interface LoanService {

    public Loan getLoan(int accountId);

    public String addLoan(String loan);

    public String deleteLoan(int accountId);

    public String editLoan(String loan);

    public boolean isEmpty(int accountId);
}
