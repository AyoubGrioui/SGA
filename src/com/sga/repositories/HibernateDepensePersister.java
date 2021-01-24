package com.sga.repositories;

import com.sga.entities.Depense;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDepensePersister extends Repository<Depense> {
    Transaction transaction = null;

    @Override
    public Depense read(Long idStructure) {
        Depense obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(Depense.class, idStructure);
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
    public List<Depense> getAll() {
        List<Depense> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from Depense").list();
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
