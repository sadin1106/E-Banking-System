package com.se2.ebankingproject.Controller;

import com.se2.ebankingproject.Service.InvestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class InvestTypeController {

    @Autowired
    InvestTypeService investTypeService;
}
