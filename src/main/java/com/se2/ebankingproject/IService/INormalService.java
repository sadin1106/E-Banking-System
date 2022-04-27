package com.se2.ebankingproject.IService;

import com.se2.ebankingproject.Model.Normal;

import java.util.List;

public interface INormalService {

    public Normal getNormalById(int normalId);

    public List<Normal> getAllNormal();

    public String addNormal(String normal);

    public String deleteNormalById(int normalId);

    public String editNormal(String normal);
}
