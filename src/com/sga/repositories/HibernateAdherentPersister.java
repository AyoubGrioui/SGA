package com.sga.repositories;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.sga.entities.Adherent;

public class HibernateAdherentPersister extends Repository<Adherent> {
	Transaction transaction = null;

	@Override
	public Adherent read(Long idStructure) {
		Adherent obj = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			obj = session.find(Adherent.class, idStructure);
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
	public List<Adherent> getAll() {
		List<Adherent> list = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			list = session.createQuery("from Adherent").list();
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

	public Adherent getByEmail(String email) {
		Adherent obj = null;
		Query<Adherent> query = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			query = getSession().createQuery("from Adherent where email=:email");
			query.setString("email", email);
			obj = query.list().get(0);
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
}
