package dao;

import models.Medicament;
import models.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.transaction.Transactional;
import java.util.List;

public class OrderDao {

    @Transactional
    public Order getOrder(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Order where id =:id");
        query.setParameter("id", id);
        Order order = (Order) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return order;
    }

    @Transactional
    public List<Order> getOrders() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Order> list = session.createQuery("from Order").list();

        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Transactional
    public void addOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void updateOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(order);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void deleteOrder(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Order where id =:id");
        query.setParameter("id", id);
        Order medicament = (Order) query.uniqueResult();
        session.delete(medicament);
        session.getTransaction().commit();
        session.close();
    }
}
