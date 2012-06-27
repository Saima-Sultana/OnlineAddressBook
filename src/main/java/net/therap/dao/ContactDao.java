package net.therap.dao;

import net.therap.domain.Contact;
import net.therap.domain.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

public class ContactDao extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(ContactDao.class);

    public void saveContact(Contact contact) {
        log.info("in saveContact");
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.save(contact);
        session.flush();
    }

    public void updateContact(Contact contact) {
        log.info("in updateContact");
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.update(contact);
        session.flush();
    }

    public Contact getContact(long id) {
        return (Contact) this.getHibernateTemplate().load(Contact.class, id);
    }

    public List<Contact> getContacts(User user) {
        String query = "FROM Contact contact WHERE contact.user = ?";

        List<Contact> contactList = this.getHibernateTemplate().find(query, user);
        return (contactList.size() == 0 ? null:contactList);
    }

    public void deleteContact(long contactId) {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Contact contact = getContact(contactId);
        session.delete(contact);
        session.flush();
    }

    public List<Contact> searchContact(User user, String name) {
        String query = "FROM Contact contact WHERE contact.user = ? AND contact.formattedName like ?";

        List<Contact> contactList = this.getHibernateTemplate().find(query, user, "%"+name+"%");
        return (contactList.size() == 0 ? null:contactList);
    }
}
