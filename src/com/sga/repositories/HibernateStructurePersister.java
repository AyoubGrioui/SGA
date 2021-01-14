package com.sga.repositories;

import javax.persistence.EntityManager;

import com.sga.entities.Structure;

public class HibernateStructurePersister implements Repository<Structure> {

	@Override
	public Structure read(Long id) {
		Structure object = null;
		try {
			EntityManager em = rf.getEntityManager();
			object = em.find(Structure.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rf.close();
		}
		return object;
	}

}
