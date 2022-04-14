package com.se2.ebankingproject.Helper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

@Component
public class CheckValidate {

    private boolean Ok;

    public boolean getOk(Object obj) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violation = validator.validate(obj);

        if(violation.size() == 0) {
            this.Ok = true;
        }else {
            this.Ok = false;
            violation.forEach(x -> {
                System.out.println(x.getMessage() + "ok");
            });
        }
        return this.Ok;
    }
}