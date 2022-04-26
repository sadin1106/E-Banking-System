package com.se2.ebankingproject.Controller;

import com.se2.ebankingproject.Model.Gift;
import com.se2.ebankingproject.ServiceImpl.GiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class GiftController {

    final String org = "http://127.0.0.1:5500"; //Visual Studio Live Server

    @Autowired
    GiftServiceImpl giftServiceImpl;

    @GetMapping(value = "/transaction/getAllGifts")
    @CrossOrigin(origins = org)
    public List<Gift> getAllGists(){
        return giftServiceImpl.getAllGift();
    }

    @GetMapping(value = "/transaction/getGiftById")
    @CrossOrigin(origins = org)
    public Gift getGiftById(@RequestParam int giftId) {
        return giftServiceImpl.getGiftById(giftId);
    }

    @GetMapping(value = "/transaction/getGiftByAccount")
    @CrossOrigin(origins = org)
    public List<Gift> getGiftsByAccount(@RequestParam int accountId){
        return giftServiceImpl.getGiftsByAccount(accountId);
    }

    @PostMapping(value= "/transaction/addGift")
    @CrossOrigin(origins = org)
    public String addGift(@RequestBody String gift) {
        return giftServiceImpl.addGift(gift);
    }

    @PutMapping(value= "/transaction/editGift")
    @CrossOrigin(origins = org)
    public String editGift(@RequestBody String gift) {
        return giftServiceImpl.editGift(gift);
    }

    @DeleteMapping(value= "/transaction/deleteGiftById")
    @CrossOrigin(origins = org)
    public String deleteGiftById(@RequestParam int giftId) {
        return giftServiceImpl.deleteGiftById(giftId);
    }

}
