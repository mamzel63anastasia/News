package dao;

import models.Producer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class ProducerDao {

    @Transactional
    public Producer getProducer(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Producer where id =:id ");
        query.setParameter("id", id);
        Producer producer = (Producer) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return producer;
    }

    @Transactional
    public List<Producer> getProducers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Producer> list = session.createQuery("from Producer").list();

        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Transactional
    public void addProducer(Producer producer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(producer);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void updateProducer(Producer producer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(producer);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteProducer(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Producer where id=:id");
        query.setParameter("id",id);
        Producer producer = (Producer) query.uniqueResult();
        session.delete(producer);
        session.getTransaction().commit();
        session.close();
    }

}
