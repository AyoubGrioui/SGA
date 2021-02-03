package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;

import com.sga.entities.Don;

public class HibernateDonPersister extends Repository<Don> {
	Transaction transaction = null;

	@Override
	public Don read(Long id) {
		Don obj = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			obj = session.find(Don.class, id);
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
	public List<Don> getAll() {
		List<Don> list = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			list = session.createQuery("from Don").list();
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
