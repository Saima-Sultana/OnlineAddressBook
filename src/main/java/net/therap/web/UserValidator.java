package net.therap.web;

import net.therap.domain.User;
import net.therap.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("userValidator")
public class UserValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(UserValidator.class);
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    private UserManager userManager;

    public boolean supports(Class candidate) {
        return User.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginName", "required.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required.firstname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required.lastname");

        User user = (User) obj;

        User user2 = userManager.getUserByLoginName(user.getLoginName());
        if (user2 != null) {
            errors.rejectValue("loginName", "alreadyexists.username");
        }

        if (!isValidEmail(user.getEmail().trim()))
            errors.rejectValue("email", "incorrect.email");
    }

    private boolean isValidEmail(String input) {
        return input.matches(EMAIL_PATTERN);
    }
}


