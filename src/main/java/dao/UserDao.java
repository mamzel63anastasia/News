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
    public User getUser(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where login=:login and password=:password ")
                .setParameter("login", login)
                .setParameter("password", password);

        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public User getUser(Long uuid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where id=:id  ")
                .setParameter("id", uuid);

        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Transactional
    public List<User> getUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Query userQuery = session.createQuery("from User");

            List<User> userList = (List<User>) userQuery.list();

            session.getTransaction().commit();
            session.close();

            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().commit();
            session.close();
            return Collections.emptyList();
        }
    }

    @Transactional
    public void addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void deleteUser(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where id=:id");
        query.setParameter("id",id);
        User user = (User) query.uniqueResult();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

}
