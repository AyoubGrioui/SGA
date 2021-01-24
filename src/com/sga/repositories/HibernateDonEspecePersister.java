package com.sga.repositories;

import com.sga.entities.DonEspece;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDonEspecePersister extends Repository<DonEspece> {
    Transaction transaction = null;

    @Override
    public DonEspece read(Long idStructure) {
        DonEspece obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(DonEspece.class, idStructure);
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
    public List<DonEspece> getAll() {
        List<DonEspece> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from DonEspece").list();
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
