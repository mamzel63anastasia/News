package dao;

import models.Substance;
import models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class SubstanceDao {

    @Transactional
    public Substance getSubstance(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Substance where id=:id  ")
                .setParameter("id", id);

        Substance substance = (Substance) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return substance;
    }

    @Transactional
    public List<Substance> getSubstances() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Substance> list = session.createQuery("from Substance").list();

        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Transactional
    public void addSubstance(Substance substance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(substance);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void updateSubstance(Substance substance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(substance);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void deleteSubstance(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Substance where id=:id");
        query.setParameter("id", id);
        Substance substance = (Substance) query.uniqueResult();
        session.delete(substance);
        session.getTransaction().commit();
        session.close();
    }

}
