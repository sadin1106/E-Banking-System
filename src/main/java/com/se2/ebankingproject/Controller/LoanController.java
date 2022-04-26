package com.se2.ebankingproject.Controller;

import com.se2.ebankingproject.Model.Loan;
import com.se2.ebankingproject.ServiceImpl.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    final String org = "http://127.0.0.1:5500"; //Visual Studio Live Server

    @Autowired
    LoanServiceImpl loanServiceImpl;

    @GetMapping(value = "/loan/getLoan")
    @CrossOrigin(origins = org)
    public Loan getLoan(@RequestParam int accountId) {
        return loanServiceImpl.getLoan(accountId);
    }

    @PostMapping(value = "/loan/addLoan")
    @CrossOrigin(origins = org)
    public String addLoan(@RequestBody String loan) {
        return loanServiceImpl.addLoan(loan);
    }

    @DeleteMapping(value = "/loan/deleteLoan")
    @CrossOrigin(origins = org)
    public String deleteLoan(@RequestParam int accountId) {
        return loanServiceImpl.deleteLoan(accountId);
    }

    @PutMapping(value = "/loan/editLoan")
    public String editLoan(@RequestBody String loan) {
        return loanServiceImpl.editLoan(loan);
    }

}
