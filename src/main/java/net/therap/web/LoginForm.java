package net.therap.web;

import net.therap.command.LoginCmd;
import net.therap.domain.User;
import net.therap.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("loginform.html")
public class LoginForm {
    private static final Logger log = LoggerFactory.getLogger(LoginForm.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private LoginValidator loginValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String successMsg = ServletRequestUtils.getStringParameter(request, "successMsg", null);
        if (successMsg != null)
            model.addAttribute("msg", "You have successfully registered. Please log in now.");

        LoginCmd loginForm = new LoginCmd();
        model.addAttribute("loginForm", loginForm);
        return "loginform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute("loginForm") LoginCmd loginForm, BindingResult result,
                              ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        loginValidator.validate(loginForm, result);

        if (result.hasErrors()) {
            model.addAttribute("loginForm", loginForm);
            return "loginform";
        }

        log.info("name", loginForm.getUserName());
        User user = userManager.getUserByLoginName(loginForm.getUserName());

        HttpSession session = request.getSession();
        session.setAttribute("User", user);

        return "redirect:mycontacts.html";
    }
}


