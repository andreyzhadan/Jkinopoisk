package com.zhadan.validation;

import com.zhadan.bean.Movie;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/29/13
 * Time: 6:33 PM
 */
@Component
public class MovieValidator implements Validator {

    // Can this Validator validate instances of the supplied Class?
    @Override
    public boolean supports(Class<?> clazz) {
        return Movie.class.isAssignableFrom(clazz);
    }

    // Validates the given object and in case of validation errors, registers those with the given Errors object
    @Override
    public void validate(Object target, Errors errors) {
        Movie movie = (Movie) target;
        ValidationUtils.rejectIfEmpty(errors, "name", "movie.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "russianName", "movie.russianName.empty");
        ValidationUtils.rejectIfEmpty(errors, "slogan", "movie.slogan.empty");
        ValidationUtils.rejectIfEmpty(errors, "country", "movie.country.empty");

        int year = movie.getYear();
        if (year < 1900 || year > 2014) {
            errors.rejectValue("year", "movie.year.notValidYear");
        }

        float rating = movie.getRating();
        if (!(rating > 0f && rating < 10f)) {
            errors.rejectValue("rating", "movie.rating.notValidRating");
        }

    }
}
