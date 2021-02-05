package com.sga.repositories;

import java.util.List;

import com.sga.entities.Adherent;
import org.hibernate.Transaction;

import com.sga.entities.Donneur;
import org.hibernate.query.Query;

public class HibernateDonneurPersister extends Repository<Donneur> {
	Transaction transaction = null;

	@Override
	public Donneur read(Long id) {
		Donneur obj = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			obj = session.find(Donneur.class, id);
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
	public List<Donneur> getAll() {
		List<Donneur> list = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			list = session.createQuery("from Donneur").list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
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

	public Donneur getByEmail(String email) {
		Donneur obj = null;
		Query<Donneur> query = null;
		try {
			sf = getSessionFactory();
			session = sf.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			query = getSession().createQuery("from Donneur where email=:email");
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
