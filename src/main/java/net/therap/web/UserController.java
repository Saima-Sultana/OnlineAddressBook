package net.therap.web;

import net.therap.command.UserCmd;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

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

/*    @RequestMapping(value = "editprofile.html", method = RequestMethod.GET)
    public String showEditForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        UserCmd userCmd = new UserCmd(user.getLoginName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
        model.addAttribute("editUser", userCmd);
        return "editprofile";
    }

    @RequestMapping(value = "editprofile.html", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute("editUser") User user, BindingResult result,
                                  ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        userValidator.validate(user, result);
        log.info("after validation");

        userManager.updateUser(user);
        HttpSession session = request.getSession(false);
        session.setAttribute("User", user);
        log.info("after edit save");
        return "redirect:loginform.html";
    }*/
}
