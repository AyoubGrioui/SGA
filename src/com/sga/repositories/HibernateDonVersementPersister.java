package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.DonVersement;

public class HibernateDonVersementPersister implements Repository<DonVersement> {
	@Override
	public DonVersement read(Long id) {
		DonVersement object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(DonVersement.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
