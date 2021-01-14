package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.LigneFonction;

public class HibernateLigneFonctionPersister implements Repository<LigneFonction> {
	@Override
	public LigneFonction read(Long id) {
		LigneFonction object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(LigneFonction.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
