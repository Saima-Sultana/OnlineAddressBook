package net.therap.service;

import net.therap.dao.ContactDao;
import net.therap.domain.Contact;
import net.therap.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ContactManager")
public class ContactManagerImpl implements  ContactManager{
    @Autowired
    private ContactDao contactDao;

    public void saveContact(Contact contact) {
        contactDao.saveContact(contact);
    }

    public void updateContact(Contact contact) {
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
}
