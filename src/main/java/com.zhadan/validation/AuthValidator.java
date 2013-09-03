package com.zhadan.validation;

import com.zhadan.bean.Movie;
import com.zhadan.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/2/13
 * Time: 4:23 PM
 */
@Component
public class AuthValidator implements Validator {

    // Can this Validator validate instances of the supplied Class?
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    // Validates the given object and in case of validation errors, registers those with the given Errors object
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmpty(errors, "userName", "user.userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
        ValidationUtils.rejectIfEmpty(errors, "password2", "user.password2.empty");

        String password = user.getPassword();
        String password2 = user.getPassword2();
        if (!password.equals(password2)) {
            errors.rejectValue("password", "user.password.notMatching");
        }
    }
}