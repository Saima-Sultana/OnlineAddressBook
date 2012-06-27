package net.therap.service;

import net.therap.dao.ContactDao;
import net.therap.domain.Contact;
import net.therap.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@Service("ContactManager")
public class ContactManagerImpl implements  ContactManager{
    @Autowired
    private ContactDao contactDao;

    public void saveContact(Contact contact) {
        contact.setFormattedName(contact.getFirstName() + " " + contact.getLastName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        contact.setLastRevision(dateFormat.format(new Date()));
        contactDao.saveContact(contact);
    }

    public void updateContact(Contact contact) {
        contact.setFormattedName(contact.getFirstName() + " " + contact.getLastName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        contact.setLastRevision(dateFormat.format(new Date()));
        contactDao.updateContact(contact);
    }

    public Contact getContact(long id) {
        return (Contact) contactDao.getContact(id);
    }

    public List<Contact> getContacts(User user) {
        return contactDao.getContacts(user);
    }

    public void deleteContact(long contactId) {
        contactDao.deleteContact(contactId);
    }

    public List<Contact> searchContact(User user, String name) {
        if(name.isEmpty())
            return null;
        else
            return contactDao.searchContact(user, name);
    }

    public String exportVcard(Contact contact) {
        String str = "BEGIN:VCARD\n" +
            "VERSION:4.0\n" +
            "N:" + contact.getLastName() + ";" + contact.getFirstName() + ";;;" + "\n" +
            "FN:" + contact.getFormattedName() + "\n" +
            "ORG:" + contact.getOrg() + "\n" +
            "TITLE:" + contact.getTitle() + "\n" +
            "PHOTO:" + contact.getPhotoUrl() + "\n" +
            "TEL;TYPE=\"work,voice\";VALUE=uri:tel:" + contact.getTelWork() + "\n" +
            "TEL;TYPE=\"home,voice\";VALUE=uri:tel:" + contact.getTelHome() + "\n" +
            "ADR;TYPE=work;LABEL=" + contact.getAddress() + "\n" +
            "EMAIL:" + contact.getEmail() + "\n" +
            "REV:" + contact.getLastRevision() + "\n" +
            "END:VCARD";
        return str;
    }
}
