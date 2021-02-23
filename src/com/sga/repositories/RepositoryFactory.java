package com.sga.repositories;

import com.sga.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class RepositoryFactory {
    SessionFactory sessionFactory;

    SessionFactory getSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure( "hibernate.cfg.xml" );
        configObj.addAnnotatedClass( Structure.class );
        configObj.addAnnotatedClass( Adherent.class );
        configObj.addAnnotatedClass( Depense.class );
        configObj.addAnnotatedClass( Don.class );
        configObj.addAnnotatedClass( DonCheque.class );
        configObj.addAnnotatedClass( DonVersement.class );
        configObj.addAnnotatedClass( DonEspece.class );
        configObj.addAnnotatedClass( Donneur.class );
        configObj.addAnnotatedClass( DonneurMoral.class );
        configObj.addAnnotatedClass( DonneurPhysique.class );
        configObj.addAnnotatedClass( Fonction.class );
        configObj.addAnnotatedClass( LigneFonction.class );
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
                .applySettings( configObj.getProperties() ).build();
        sessionFactory = configObj.buildSessionFactory( serviceRegistryObj );
        return sessionFactory;
    }

    public static RepositoryFactory getInstance() {
        return new RepositoryFactory();
    }

    public Repository<Structure> getStructureRepository() {
        return new HibernateStructurePersister();
    }

    public Repository<Adherent> getAdherentRepository() {
        return new HibernateAdherentPersister();
    }

    public Repository<Depense> getDepenseRepository() {
        return new HibernateDepensePersister();
    }

    public Repository<DonEspece> getDonEspeceRepository() {
        return new HibernateDonEspecePersister();
    }

    public Repository<DonVersement> getDonVersementRepository() {
        return new HibernateDonVersementPersister();
    }

    public Repository<DonCheque> getDonChequeRepository() {
        return new HibernateDonChequePersister();
    }

    public Repository<DonneurPhysique> getDonneurPhysiqueRepository() {
        return new HibernateDonneurPhysiquePersister();
    }

    public Repository<DonneurMoral> getDonneurMoralRepository() {
        return new HibernateDonneurMoralPersister();
    }

    public Repository<Fonction> getFonctionRepository() {
        return new HibernateFonctionPersister();
    }

    public Repository<LigneFonction> getLigneFonctionRepository() {
        return new HibernateLigneFonctionPersister();
    }

}
