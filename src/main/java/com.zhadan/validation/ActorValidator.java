package com.zhadan.validation;

import com.zhadan.bean.Actor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/30/13
 * Time: 12:15 PM
 */
@Component
public class ActorValidator implements Validator {

    // Can this Validator validate instances of the supplied Class?
    @Override
    public boolean supports(Class<?> clazz) {
        return Actor.class.isAssignableFrom(clazz);
    }

    // Validates the given object and in case of validation errors, registers those with the given Errors object
    @Override
    public void validate(Object target, Errors errors) {
        Actor actor = (Actor) target;
        ValidationUtils.rejectIfEmpty(errors, "firstName", "actor.firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "actor.lastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "birthday", "actor.birthday.empty");
        ValidationUtils.rejectIfEmpty(errors, "country", "actor.country.empty");

        int year = actor.getBirthday();
        if (year < 1900 || year > 2014) {
            errors.rejectValue("birthday", "actor.birthday.notValidYear");
        }
    }
}
