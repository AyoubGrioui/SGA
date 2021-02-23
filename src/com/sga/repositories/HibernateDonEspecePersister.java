package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;

import com.sga.entities.DonEspece;

public class HibernateDonEspecePersister extends Repository<DonEspece> {
    Transaction transaction = null;

    @Override
    public DonEspece read( Long idStructure ) {
        DonEspece obj = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            obj = session.find( DonEspece.class, idStructure );
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
    public List<DonEspece> getAll() {
        List<DonEspece> list = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery( "from DonEspece" ).list();
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
