package com.sga.repositories;

import com.sga.entities.LigneFonction;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateLigneFonctionPersister extends Repository<LigneFonction> {
    Transaction transaction = null;

    @Override
    public LigneFonction read(Long idStructure) {
        LigneFonction obj = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find(LigneFonction.class, idStructure);
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
    public List<LigneFonction> getAll() {
        List<LigneFonction> list = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery("from LigneFonction").list();
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
