package com.se2.ebankingproject.Service;

import com.se2.ebankingproject.Model.Save;

public interface SaveService {

    public Save getSave(int accountId);

    public String addSave(String save);

    public String deleteSave(int accountId);

    public String editSave(String save);

    public boolean isEmpty(int accountId);
}
