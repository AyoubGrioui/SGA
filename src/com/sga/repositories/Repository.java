package com.sga.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public interface Repository<DataObject> {
	static RepositoryFactory rf = RepositoryFactory.getInstance();

	public default void create(DataObject data) {
		EntityTransaction et = null;
		try {
			EntityManager em = rf.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(data);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
		} finally {
			rf.close();
		}
	}

	public DataObject read(Long id);

	public default void update(DataObject data) {
		EntityTransaction et = null;
		try {
			EntityManager em = rf.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(data);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
		} finally {
			rf.close();
		}
	}

	public default void delete(DataObject data) {
		EntityTransaction et = null;
		try {
			EntityManager em = rf.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.remove(data);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
		} finally {
			rf.close();
		}
	}
}
