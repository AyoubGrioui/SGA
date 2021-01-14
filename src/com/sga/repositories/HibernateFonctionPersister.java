package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.Fonction;

public class HibernateFonctionPersister implements Repository<Fonction> {
	@Override
	public Fonction read(Long id) {
		Fonction object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(Fonction.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
