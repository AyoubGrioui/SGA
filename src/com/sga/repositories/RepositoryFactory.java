package com.sga.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sga.entities.Adherent;
import com.sga.entities.Depense;
import com.sga.entities.DonCheque;
import com.sga.entities.DonEspece;
import com.sga.entities.DonVersement;
import com.sga.entities.DonneurMoral;
import com.sga.entities.DonneurPhysique;
import com.sga.entities.Fonction;
import com.sga.entities.LigneFonction;
import com.sga.entities.Structure;

public class RepositoryFactory {
	private static final String persistenceUnit = "SGA";
	private static EntityManager em = null;
	private static EntityManagerFactory emf = null;

	public static RepositoryFactory getInstance() {
		return new RepositoryFactory();
	}

	public EntityManager getEntityManager() {
		emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
		return em;
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

	public void close() {
		if (em != null)
			em.close();
		if (emf != null)
			emf.close();
	}

}
