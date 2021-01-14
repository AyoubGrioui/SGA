package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.DonneurMoral;

public class HibernateDonneurMoralPersister implements Repository<DonneurMoral> {
	@Override
	public DonneurMoral read(Long id) {
		DonneurMoral object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(DonneurMoral.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
