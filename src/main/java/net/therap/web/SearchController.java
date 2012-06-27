package net.therap.web;

import net.therap.domain.Contact;
import net.therap.domain.User;
import net.therap.service.ContactManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SearchController {
    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private ContactManager contactManager;

    @RequestMapping(value = "searchcontact.html", method = RequestMethod.POST)
    public ModelAndView processSearchForm(@RequestParam(required= false, defaultValue="") String name, ModelMap model,
                                    HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        List<Contact> contactList = contactManager.searchContact(user, name.trim());

        model.addAttribute("contactList", contactList);
        model.addAttribute("searchText", name.trim());
        return new ModelAndView("mycontacts", model);
    }
}
