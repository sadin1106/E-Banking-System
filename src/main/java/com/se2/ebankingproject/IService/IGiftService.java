package com.se2.ebankingproject.IService;

import com.se2.ebankingproject.Model.Gift;

import java.util.List;

public interface IGiftService {

    public Gift getGiftById(int giftId);

    public List<Gift> getAllGift();

    public String deleteGiftById(int giftId);

    public String addGift(String gift);

    public String editGift(String gift);

    public List<Gift> getGiftsByAccount(int accountId);
}
