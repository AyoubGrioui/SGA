package com.sga.repositories;

import com.sga.entities.DonneurMoral;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDonneurMoralPersister extends Repository<DonneurMoral> {
    Transaction transaction = null;

    @Override
    public DonneurMoral read(Long idStructure) {
        DonneurMoral obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(DonneurMoral.class, idStructure);
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
    public List<DonneurMoral> getAll() {
        List<DonneurMoral> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from DonneurMoral").list();
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
