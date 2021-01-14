package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.Depense;

public class HibernateDepensePersister implements Repository<Depense> {
	@Override
	public Depense read(Long id) {
		Depense object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(Depense.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
