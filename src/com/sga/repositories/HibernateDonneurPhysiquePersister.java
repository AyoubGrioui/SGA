package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;

import com.sga.entities.DonneurPhysique;

public class HibernateDonneurPhysiquePersister extends Repository<DonneurPhysique> {
	Transaction transaction = null;

	@Override
	public DonneurPhysique read(Long idStructure) {
		DonneurPhysique obj = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			obj = session.find(DonneurPhysique.class, idStructure);
			transaction.commit();
			return obj;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return null;
		} finally {
			if (session != null)
				session.close();
			if (sf != null)
				sf.close();
		}
	}

	@Override
	public List<DonneurPhysique> getAll() {
		List<DonneurPhysique> list = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			list = session.createQuery("from DonneurPhysique").list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return null;
		} finally {
			if (session != null)
				session.close();
			if (sf != null)
				sf.close();
		}
	}
}
