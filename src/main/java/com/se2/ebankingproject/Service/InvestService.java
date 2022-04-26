package com.se2.ebankingproject.Service;

import com.se2.ebankingproject.Model.InvestType;

public interface InvestService {

    public InvestType getInvest(int accountId);

    public String addInvestType(String investType);

    public String deleteInvestType(int accountId);

    public String editInvestType(String investType);

    public boolean isEmpty(int accountId);
}
