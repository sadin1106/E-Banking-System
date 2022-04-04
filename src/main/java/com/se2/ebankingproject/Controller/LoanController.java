package com.se2.ebankingproject.Controller;

import com.se2.ebankingproject.Model.Loan;
import com.se2.ebankingproject.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    final String org = "http://127.0.0.1:5500"; //Visual Studio Live Server

    @Autowired
    LoanService loanService;

    @GetMapping(value = "/loan/getLoan")
    @CrossOrigin(origins = org)
    public Loan getLoan(@RequestParam int accountId) {
        return loanService.getLoan(accountId);
    }

    @PostMapping(value = "/loan/addLoan")
    @CrossOrigin(origins = org)
    public String addLoan(@RequestBody String loan) {
        return loanService.addLoan(loan);
    }

    @DeleteMapping(value = "/loan/deleteLoan")
    @CrossOrigin(origins = org)
    public String deleteLoan(@RequestParam int accountId) {
        return loanService.deleteLoan(accountId);
    }

    @PutMapping(value = "/loan/editLoan")
    public String editLoan(@RequestBody String loan) {
        return loanService.editLoan(loan);
    }
}
