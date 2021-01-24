package com.sga.repositories;

import com.sga.entities.DonVersement;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDonVersementPersister extends Repository<DonVersement> {
    Transaction transaction = null;

    @Override
    public DonVersement read(Long idStructure) {
        DonVersement obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(DonVersement.class, idStructure);
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
    public List<DonVersement> getAll() {
        List<DonVersement> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from DonVersement").list();
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
