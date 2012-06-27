package net.therap.web;

import net.therap.domain.Contact;
import net.therap.service.ContactManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContactDetailsController {
    private static final Logger log = LoggerFactory.getLogger(ContactDetailsController.class);

    @Autowired
    private ContactManager contactManager;

    @RequestMapping(value = "contactdetails.html", method = RequestMethod.GET)
    public String showContact(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        long contactId = ServletRequestUtils.getLongParameter(request, "contactId", -1);
        Contact contact = contactManager.getContact(contactId);
        model.addAttribute("contact", contact);
        return "contactdetails";
    }
}
