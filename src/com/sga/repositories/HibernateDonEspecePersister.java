package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.DonEspece;

public class HibernateDonEspecePersister implements Repository<DonEspece> {
	@Override
	public DonEspece read(Long id) {
		DonEspece object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(DonEspece.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
