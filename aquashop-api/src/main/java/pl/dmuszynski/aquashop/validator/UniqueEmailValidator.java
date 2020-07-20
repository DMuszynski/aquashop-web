package pl.dmuszynski.aquashop.validator;


import org.springframework.beans.factory.annotation.Autowired;
import pl.dmuszynski.aquashop.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserService userService;

    @Autowired
    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return value != null && !userService.isEmailAlreadyInUse(value);
        return true;
//        userService.findByEmail(value);
    }

}
