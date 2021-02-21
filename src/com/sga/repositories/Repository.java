package com.sga.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class Repository<DataObject> {
	RepositoryFactory rf = RepositoryFactory.getInstance();
	SessionFactory sf = null;

	Session session = null;

	SessionFactory getSessionFactory() {
		return rf.getSessionFactory();
	}

	public abstract DataObject read(Long idStructure);

	public abstract List<DataObject> getAll();

	public Session getSession() {
		return getSessionFactory().openSession();
	}

	public Long create(DataObject data) {
		Long id = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			id = (Long) session.save(data);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			return null;
		} finally {
			if (session != null)
				session.close();
			if (sf != null)
				sf.close();
		}
	}
	public void delete(DataObject data) {
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.getTransaction().begin();
			session.delete(data);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (getSession().getTransaction() != null)
				getSession().getTransaction().rollback();
		} finally {
			if (session != null)
				session.close();
			if (sf != null)
				sf.close();
		}
	}

	public void update(DataObject s) {
		Transaction transaction = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.update(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null)
				session.close();
			if (sf != null)
				sf.close();
		}
	}
}
