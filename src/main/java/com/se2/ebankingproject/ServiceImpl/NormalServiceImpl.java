package com.se2.ebankingproject.ServiceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.se2.ebankingproject.Helper.CheckValidate;
import com.se2.ebankingproject.Helper.ErrorType;
import com.se2.ebankingproject.Service.NormalService;
import com.se2.ebankingproject.Model.Normal;
import com.se2.ebankingproject.Repository.AccountRepository;
import com.se2.ebankingproject.Repository.NormalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormalServiceImpl implements NormalService {

    @Autowired
    NormalRepository normalRepository;

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ErrorType errorType;

    @Autowired
    CheckValidate checkValidate;

    @Override
    public Normal getNormalById(int normalId) {
        if (normalRepository.existsById(normalId))
            return normalRepository.findById(normalId).get();
        return null;
    }

    @Override
    public List<Normal> getAllNormal() {

        return normalRepository.findAll();
    }

    @Override
    public String addNormal(String normal) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Normal nor = gson.fromJson(normal, Normal.class);

        if (!normalRepository.existsById(nor.getAccountId())) {
            if (checkValidate.getOk(nor)) {
                normalRepository.save(nor);
            }
            return errorType.isValidated(nor.getName());
        }
        return editNormal(normal);

    }

    @Override
    public String deleteNormalById(int normalId) {
        if (normalRepository.existsById(normalId)) {
            normalRepository.deleteById(normalId);
            return errorType.getSuccessful();
        }
        return "Normal " + errorType.isNotExisted(normalId);
    }

    @Override
    public String editNormal(String normal) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
        Normal nor = gson.fromJson(normal, Normal.class);
        if (normalRepository.existsById(nor.getAccountId())) {
            if (checkValidate.getOk(nor)) {
                Normal fixNor = getNormalById(nor.getAccountId());
                if (nor.getIdentityCard() != null)
                    fixNor.setIdentityCard(nor.getIdentityCard());
                accountServiceImpl.editAccount(normal);
                normalRepository.save(fixNor);
                return errorType.getSuccessful();
            }
        }
        return errorType.isNotExisted(nor.getName());
    }
}
