package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;

import com.sga.entities.Structure;

public class HibernateStructurePersister extends Repository<Structure> {
    Transaction transaction = null;

    @Override
    public void update( Structure s ) {
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.update( s );
            transaction.commit();
        } catch ( Exception e ) {
            if ( transaction != null ) {
                transaction.rollback();
            }
        } finally {
            if ( session != null )
                session.close();
            if ( sf != null )
                sf.close();
        }
    }

    public Structure read( Long idStructure ) {
        Structure s = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            s = session.find( Structure.class, idStructure );
            transaction.commit();
            return s;
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
    public List<Structure> getAll() {
        List<Structure> list = null;
        try {
            sf = getSessionFactory();
            session = sf.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            list = session.createQuery( "from Structure" ).list();
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
