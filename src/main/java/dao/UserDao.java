package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

public class UserDao {
    @Transactional
    public List<User> getUsers(String login, String password) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Query userQuery = session.createQuery("from User where login=:login and password=:password")
                    .setParameter("login", login)
                    .setParameter("password", password);

            List<User> userList = (List<User>) userQuery.list();

            session.getTransaction().commit();
            session.close();

            return userList;
        } catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().commit();
            session.close();
            return Collections.emptyList();
        }

    }
}
