package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;

import com.sga.entities.DonneurMoral;

public class HibernateDonneurMoralPersister extends Repository<DonneurMoral> {
    Transaction transaction = null;

    @Override
    public DonneurMoral read( Long idStructure ) {
        DonneurMoral obj = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find( DonneurMoral.class, idStructure );
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
    public List<DonneurMoral> getAll() {
        List<DonneurMoral> list = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery( "from DonneurMoral" ).list();
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
