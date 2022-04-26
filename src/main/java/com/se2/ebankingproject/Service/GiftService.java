package com.se2.ebankingproject.Service;

import com.se2.ebankingproject.Model.Gift;

import java.util.List;

public interface GiftService {

    public Gift getGiftById(int giftId);

    public List<Gift> getAllGift();

    public String deleteGiftById(int giftId);

    public String addGift(String gift);

    public String editGift(String gift);

    public List<Gift> getGiftsByAccount(int accountId);
}
