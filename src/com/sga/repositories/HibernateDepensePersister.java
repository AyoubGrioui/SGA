package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;

import com.sga.entities.Depense;

public class HibernateDepensePersister extends Repository<Depense> {
	Transaction transaction = null;

	@Override
	public Depense read(Long idStructure) {
		Depense obj = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			obj = session.find(Depense.class, idStructure);
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
	public List<Depense> getAll() {
		List<Depense> list = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			list = session.createQuery("from Depense").list();
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
