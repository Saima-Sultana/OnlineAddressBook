package net.therap.service;

import net.therap.domain.Contact;
import net.therap.domain.User;

import java.util.List;

public interface ContactManager {
    public void saveContact(Contact contact);
    public void updateContact(Contact contact);
    public Contact getContact(long id);
    public List<Contact> getContacts(User user);
    public void deleteContact(long contactId);
}