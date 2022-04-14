package com.se2.ebankingproject.IService;

import com.se2.ebankingproject.Model.InvestType;

public interface IInvestService {

    public InvestType getInvest(int accountId);

    public String addInvestType(String investType);

    public String deleteInvestType(int accountId);

    public String editInvestType(String investType);

    public boolean isEmpty(int accountId);
}
