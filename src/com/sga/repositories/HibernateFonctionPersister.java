package com.sga.repositories;

import com.sga.entities.Fonction;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateFonctionPersister extends Repository<Fonction> {
    Transaction transaction = null;

    @Override
    public Fonction read(Long idStructure) {
        Fonction obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(Fonction.class, idStructure);
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
    public List<Fonction> getAll() {
        List<Fonction> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from Fonction").list();
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
