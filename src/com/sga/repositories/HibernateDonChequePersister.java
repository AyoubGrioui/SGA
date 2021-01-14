package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.DonCheque;

public class HibernateDonChequePersister implements Repository<DonCheque> {
	@Override
	public DonCheque read(Long id) {
		DonCheque object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(DonCheque.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
