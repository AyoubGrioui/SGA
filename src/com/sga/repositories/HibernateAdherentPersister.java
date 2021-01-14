package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.Adherent;

public class HibernateAdherentPersister implements Repository<Adherent> {
	@Override
	public Adherent read(Long id) {
		Adherent object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(Adherent.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
