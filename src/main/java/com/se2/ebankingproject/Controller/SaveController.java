package com.se2.ebankingproject.Controller;

import com.se2.ebankingproject.Model.Save;
import com.se2.ebankingproject.Service.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaveController {

    final String org = "http://127.0.0.1:5500"; //Visual Studio Live Server

    @Autowired
    SaveService saveService;

    @GetMapping(value = "/save/getSave")
    @CrossOrigin(origins = org)
    public Save getSave(@RequestParam int accountId) {
        return saveService.getSave(accountId);
    }

    @PostMapping(value = "/save/addSave")
    @CrossOrigin(origins = org)
    public String addSave(@RequestBody String save) {
        return saveService.addSave(save);
    }

    @DeleteMapping(value = "/save/deleteSave")
    @CrossOrigin(origins = org)
    public String deleteSave(@RequestParam int accountId) {
        return saveService.deleteSave(accountId);

    }

    @PutMapping(value = "/save/editSave")
    @CrossOrigin(origins = org)
    public String editSave(@RequestBody String save) {
        return saveService.editSave(save);
    }

}
