package lv.nixx.poc.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class PersonAgeValidator implements ConstraintValidator<PersonAgeConstrain, Date> {

    private int minAge;
    private String returnMessage;

    @Override
    public void initialize(PersonAgeConstrain personAgeConstrain) {
        this.minAge = personAgeConstrain.minAge();
        this.returnMessage = "Minimal age must be: " + minAge + " current age is: ";
    }

    @Override
    public boolean isValid(Date dateOfBirth, ConstraintValidatorContext context) {
        int currentAge = new DateDifference(dateOfBirth, new Date()).years();
        boolean valid = currentAge >= minAge;

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(returnMessage + currentAge + " years")
                    .addConstraintViolation();
        }
        return valid;
    }


}
