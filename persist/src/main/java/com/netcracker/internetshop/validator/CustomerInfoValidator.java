package com.netcracker.internetshop.validator;

import com.netcracker.internetshop.models.CustomerInfo;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.lang.annotation.Target;

@Component("customerInfoValidator")
public class CustomerInfoValidator implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CustomerInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerInfo custInfo = (CustomerInfo) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");

        if (!emailValidator.isValid(custInfo.getEmail())) {
            errors.rejectValue("email", "Pattern.customerForm.email");
        }
        String phone = ((CustomerInfo)custInfo).getPhone();
        if (phone.length() != 15) {
            errors.rejectValue("phone", "phone.tooshort", "Phone must be at least 15 characters.");
        }
    }

}