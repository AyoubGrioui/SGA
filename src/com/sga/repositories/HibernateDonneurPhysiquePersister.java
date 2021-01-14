package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.DonneurPhysique;

public class HibernateDonneurPhysiquePersister implements Repository<DonneurPhysique> {
	@Override
	public DonneurPhysique read(Long id) {
		DonneurPhysique object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(DonneurPhysique.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
