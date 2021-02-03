package test.sga.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sga.entities.Fonction;
import com.sga.repositories.HibernateFonctionPersister;

class HibernateFonctionPersisterTest {
	HibernateFonctionPersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateFonctionPersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateFonctionPersister_whenCreate_thenPersist() {
		Fonction obj = new Fonction();
		obj.setRole("Test");
		cut.create(obj);
		Session session = cut.getSession();
		Fonction r = (Fonction) session.createQuery("from Fonction ORDER BY idFonction DESC").setMaxResults(1).list()
				.get(0);
		assertEquals(r.getRole(), obj.getRole());
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateFonctionPersister_whenUpdate_thenPersist() {
		Fonction obj = new Fonction();
		obj.setRole("Test");
		cut.create(obj);
		obj.setRole("Modified");
		cut.update(obj);
		Session session = cut.getSession();
		Fonction r = (Fonction) session.createQuery("from Fonction ORDER BY idFonction DESC").setMaxResults(1).list()
				.get(0);
		assertEquals(r.getRole(), "Modified");
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateFonctionPersister_whenDelete_thenSuccess() {
		Fonction obj = new Fonction();
		obj.setRole("Test");
		cut.create(obj);
		cut.delete(obj);
		assertNull(cut.read(obj.getIdFonction()));
	}

	@Test
	void givenHibernateFonctionPersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}
}