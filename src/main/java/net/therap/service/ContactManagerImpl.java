package net.therap.service;

import net.therap.dao.ContactDao;
import net.therap.domain.Contact;
import net.therap.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@Service("ContactManager")
public class ContactManagerImpl implements  ContactManager {
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

    public void importVcard(User user, MultipartFile multipartFile) {
        Contact contact = new Contact();
        StringTokenizer st;
        if (!multipartFile.isEmpty()) {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
                try {
                    String line = null;
                    while ((line = input.readLine()) != null) {
                        if(line.startsWith("N:")) {
                            String str = line.substring(2);
                            st = new StringTokenizer(str, ";");
                            while (st.hasMoreTokens()) {
                                contact.setLastName(st.nextToken());
                                contact.setFirstName(st.nextToken());
                            }

                        }
                        else if(line.startsWith("FN:")) {
                            String str = line.substring(3);
                            contact.setFormattedName(str);
                        }
                        else if(line.startsWith("ORG:")) {
                            String str = line.substring(4);
                            contact.setOrg(str);
                        }
                        else if(line.startsWith("TITLE:")) {
                            String str = line.substring(6);
                            contact.setEmail(str);
                        }
                        else if(line.startsWith("PHOTO:")) {
                            String str = line.substring(6);
                            contact.setPhotoUrl(str);
                        }
                        else if(line.startsWith("TEL;TYPE=\"work")) {
                            int i = line.lastIndexOf(":");
                            String str = line.substring(i+1);
                            contact.setTelWork(str);
                        }
                        else if(line.startsWith("TEL;TYPE=\"home")) {
                            int i = line.lastIndexOf(":");
                            String str = line.substring(i+1);
                            contact.setTelHome(str);
                        }
                        else if(line.startsWith("ADR")) {
                            int i = line.lastIndexOf("=");
                            String str = line.substring(i+1);
                            contact.setAddress(str);
                        }
                        else if(line.startsWith("EMAIL:")) {
                            String str = line.substring(6);
                            contact.setEmail(str);
                        }
                        else if(line.startsWith("REV:")) {
                            String str = line.substring(4);
                            contact.setLastRevision(str);
                        }
                    }
                } finally {
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
            contact.setUser(user);
            contactDao.saveContact(contact);
        }
    }
}
