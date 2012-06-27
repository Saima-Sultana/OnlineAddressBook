package net.therap.web;

import net.therap.domain.Contact;
import net.therap.domain.User;
import net.therap.service.ContactManager;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MyContactsController {
    private static final Logger log = LoggerFactory.getLogger(MyContactsController.class);

    @Autowired
    private ContactValidator contactValidator;

    @Autowired
    private ContactManager contactManager;

    @RequestMapping(value = "mycontacts.html", method = RequestMethod.GET)
    public String showContacts(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        List<Contact> contactList = contactManager.getContacts(user);

        model.addAttribute("contactList", contactList);
        return "mycontacts";
    }

    @RequestMapping(value = "addcontact.html", method = RequestMethod.GET)
    public String showContactForm(ModelMap model) {
        Contact contact = new Contact();
        model.addAttribute("newContact", contact);
        return "addcontact";
    }

    @RequestMapping(value = "addcontact.html", method = RequestMethod.POST)
    public String processContactForm(@ModelAttribute("newContact") Contact contact, BindingResult result, ModelMap model,
                                                HttpServletRequest request, HttpServletResponse response) {

        log.info("in post");
        contactValidator.validate(contact, result);
        if (result.hasErrors()) {
            model.addAttribute("newContact", contact);
            return "addcontact";
        }
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        contact.setUser(user);
        contactManager.saveContact(contact);
        log.info("after save");
        return "redirect:mycontacts.html";
    }

    @RequestMapping(value = "editcontact.html", method = RequestMethod.GET)
    public String showEditForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        long contactId = ServletRequestUtils.getLongParameter(request, "contactId", -1);
        Contact contact = contactManager.getContact(contactId);
        model.addAttribute("editContact", contact);
        return "editcontact";
    }

    @RequestMapping(value = "editcontact.html", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute("editContact") Contact contact, BindingResult result,
                                  ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        contactValidator.validate(contact, result);
        log.info("after validation");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        contact.setUser(user);
        contactManager.updateContact(contact);
        log.info("after edit save");
        return "redirect:mycontacts.html";
    }

    @RequestMapping(value = "deletecontact.html", method = RequestMethod.GET)
    public String deletePhoto(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        long contactId = ServletRequestUtils.getLongParameter(request, "contactId", -1);
        contactManager.deleteContact(contactId);
        return "redirect:mycontacts.html";
    }
}
