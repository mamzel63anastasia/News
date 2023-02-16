package dao;

import models.Medicament;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class MedicamentDao {

    @Transactional
    public  Medicament getMedicament(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Medicament where id =:id");
        query.setParameter("id", id);
        Medicament medicament = (Medicament) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return medicament;
    }

    @Transactional
    public List<Medicament> getMedicaments() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Medicament> list = session.createQuery("from Medicament").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Transactional
    public void addMedicament(Medicament medicament){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(medicament);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void updateMedicament(Medicament medicament){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(medicament);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void deleteMedicament(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Medicament where id =:id");
        query.setParameter("id", id);
        Medicament medicament = (Medicament) query.uniqueResult();
        session.delete(medicament);
        session.getTransaction().commit();
        session.close();

    }

}
