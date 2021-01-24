package com.sga.repositories;

import com.sga.entities.Adherent;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateAdherentPersister extends Repository<Adherent> {
    Transaction transaction = null;

    @Override
    public Adherent read(Long idStructure) {
        Adherent obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(Adherent.class, idStructure);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
            return obj;
        }
    }

    @Override
    public List<Adherent> getAll() {
        List<Adherent> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from Adherent").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
            return list;
        }
    }
}
