package com.sga.repositories;

import com.sga.entities.Structure;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateStructurePersister extends Repository<Structure> {
    Transaction transaction = null;

    public void update(Structure s) {
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.update(s);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }

    public Structure read(Long idStructure) {
        Structure s = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            s = session.find(Structure.class, idStructure);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
            return s;
        }
    }

    @Override
    public List<Structure> getAll() {
        List<Structure> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from Structure").list();
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

