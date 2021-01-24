package com.sga.repositories;

import com.sga.entities.DonneurPhysique;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDonneurPhysiquePersister extends Repository<DonneurPhysique> {
    Transaction transaction = null;

    @Override
    public DonneurPhysique read(Long idStructure) {
        DonneurPhysique obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(DonneurPhysique.class, idStructure);
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
    public List<DonneurPhysique> getAll() {
        List<DonneurPhysique> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from DonneurPhysique").list();
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
