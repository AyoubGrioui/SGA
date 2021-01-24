package com.sga.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class Repository<DataObject> {
    RepositoryFactory rf = RepositoryFactory.getInstance();

    Session session = null;

    SessionFactory getSessionFactory() {
        return rf.getSessionFactory();
    }

    public abstract DataObject read(Long idStructure);

    public abstract List<DataObject> getAll();

    public Session getSession() {
        return getSessionFactory().openSession();
    }

    public void create(DataObject data) {
        try {
            session = getSessionFactory().openSession();
            session.beginTransaction();
            session.save(data);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }


    public void delete(DataObject data) {
        try {
            session = getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(data);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void update(DataObject s) {
        Transaction transaction = null;
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
}
