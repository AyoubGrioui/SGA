package com.sga.repositories;

import com.sga.entities.DonCheque;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDonChequePersister extends Repository<DonCheque> {
    Transaction transaction = null;

    @Override
    public DonCheque read(Long idStructure) {
        DonCheque obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(DonCheque.class, idStructure);
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
    public List<DonCheque> getAll() {
        List<DonCheque> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from DonCheque").list();
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
