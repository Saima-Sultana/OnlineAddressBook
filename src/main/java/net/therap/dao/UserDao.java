package net.therap.dao;

import net.therap.domain.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

public class UserDao extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public void saveUser(User user) {
        log.info("in saveUser");
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.save(user);
        session.flush();
    }

    public void updateUser(User user) {
        log.info("in updateUser");
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.update(user);
        session.flush();
    }

    public User getUser(long id) {
        return (User) this.getHibernateTemplate().load(User.class, id);
    }

    public User getUserByLoginName(String userName) {
        String query = "FROM User user WHERE user.loginName = :login_name";

        List<User> userList = this.getHibernateTemplate().findByNamedParam(query, "login_name", userName);
        return (userList.size() == 0) ? null:userList.get(0);
    }

    public Long count() {
        log.debug("count");
        List result = this.getHibernateTemplate().find("SELECT COUNT(n) FROM User n");
        return result.get(0) != null ? (Long)result.get(0):0;
    }
}
