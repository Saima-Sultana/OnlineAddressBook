package net.therap.web;

import net.therap.domain.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("contactValidator")
public class ContactValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(ContactValidator.class);
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean supports(Class candidate) {
        return Contact.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telWork", "required.telWork");

        Contact contact = (Contact) obj;

        if (!isValidEmail(contact.getEmail().trim()))
            errors.rejectValue("email", "incorrect.email");
    }

    private boolean isValidEmail(String input) {
        return input.matches(EMAIL_PATTERN);
    }
}
