package net.therap.web;

import net.therap.domain.Contact;
import net.therap.domain.User;
import net.therap.service.ContactManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ImportExportController {
    private static final Logger log = LoggerFactory.getLogger(ImportExportController.class);

    @Autowired
    private ContactManager contactManager;

    @RequestMapping(value = "exportcontact.html", method = RequestMethod.GET)
    public String exportContact(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        long contactId = ServletRequestUtils.getLongParameter(request, "contactId", -1);
        Contact contact = contactManager.getContact(contactId);
        String vcard = contactManager.exportVcard(contact);

        if(!vcard.isEmpty()) {
            try {
                response.setContentType("text/text");
                response.setHeader("Content-Disposition", "attachment; filename="+contact.getFirstName()+".vcf");
                response.getOutputStream().write(vcard.getBytes());
                response.flushBuffer();
            } catch (Exception e) {
            }
        }
        return "redirect:mycontacts.html";
    }

    @RequestMapping(value = "importcontact.html", method = RequestMethod.POST)
    public String importContact(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                @RequestParam("file") MultipartFile file) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        contactManager.importVcard(user, file);
        return "redirect:mycontacts.html";
    }
}
