package dao;

import models.Order;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class OrderDao {

    public Order getOrder(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Order where id=: id");
        query.setParameter("id", id);
        Order order = (Order) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return order;
    }

    public List<Order> getOrders() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Order> list =  session.createQuery("from Order").list();

        session.beginTransaction().commit();
        session.close();
        return list;
    }

    public void  addOrder (Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }


    public void updateOrder (Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(order);
        session.getTransaction().commit();
        session.close();
    }


    public void deleteOrder(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Order where id=:id");
        query.setParameter("id", id);
        Order order = (Order) query.uniqueResult();
        session.delete(order);
        session.beginTransaction().commit();
        session.close();
    }
}
