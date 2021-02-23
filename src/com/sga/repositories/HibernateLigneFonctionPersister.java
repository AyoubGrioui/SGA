package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;

import com.sga.entities.LigneFonction;

public class HibernateLigneFonctionPersister extends Repository<LigneFonction> {
    Transaction transaction = null;

    @Override
    public LigneFonction read( Long idStructure ) {
        LigneFonction obj = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find( LigneFonction.class, idStructure );
            transaction.commit();
            return obj;
        } catch ( Exception e ) {
            if ( transaction != null ) {
                transaction.rollback();
            }
            return null;
        } finally {
            if ( session != null )
                session.close();
            if ( sf != null )
                sf.close();
        }
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<LigneFonction> getAll() {
        List<LigneFonction> list = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery( "from LigneFonction" ).list();
            transaction.commit();
            return list;
        } catch ( Exception e ) {
            if ( transaction != null ) {
                transaction.rollback();
            }
            return null;
        } finally {
            if ( session != null )
                session.close();
            if ( sf != null )
                sf.close();
        }
    }
}
