package net.therap.web;

import net.therap.domain.User;
import net.therap.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "registration.html", method = RequestMethod.GET)
    public String showRegistrationForm(ModelMap model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "registration";
    }

    @RequestMapping(value = "registration.html", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(@ModelAttribute("newUser") User user, BindingResult result, ModelMap model) {

        log.info("in post");
        userValidator.checkLoginName(user, result);
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", user);
            return new ModelAndView("registration");
        }
        userManager.saveUser(user);
        log.info("after save");
        model.addAttribute("successMsg", "successful");
        return new ModelAndView("redirect:loginform.html", model);
    }
}
