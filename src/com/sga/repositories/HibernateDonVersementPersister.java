package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;

import com.sga.entities.DonVersement;

public class HibernateDonVersementPersister extends Repository<DonVersement> {
    Transaction transaction = null;

    @Override
    public DonVersement read( Long idStructure ) {
        DonVersement obj = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find( DonVersement.class, idStructure );
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
    public List<DonVersement> getAll() {
        List<DonVersement> list = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery( "from DonVersement" ).list();
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
