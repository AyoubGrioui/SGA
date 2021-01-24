package test.sga.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sga.entities.LigneFonction;
import com.sga.repositories.HibernateLigneFonctionPersister;

class HibernateLigneFonctionPersisterTest {
	HibernateLigneFonctionPersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateLigneFonctionPersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateLigneFonctionPersister_whenCreate_thenPersist() {
		LigneFonction obj = new LigneFonction();
		obj.setDateDebut(LocalDate.now());
		obj.setDateFin(LocalDate.now());
		cut.create(obj);
		Session session = cut.getSession();
		LigneFonction r = (LigneFonction) session.createQuery("from LigneFonction ORDER BY idLigneFonction DESC")
				.setMaxResults(1).list().get(0);
		assertNotNull(r);
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateLigneFonctionPersister_whenUpdate_thenPersist() {
		LigneFonction obj = new LigneFonction();
		obj.setDateDebut(LocalDate.now());
		obj.setDateFin(LocalDate.now());
		cut.create(obj);
		obj.setDateDebut(LocalDate.ofEpochDay(0));
		cut.update(obj);
		Session session = cut.getSession();
		LigneFonction r = (LigneFonction) session.createQuery("from LigneFonction ORDER BY idLigneFonction DESC")
				.setMaxResults(1).list().get(0);
		assertEquals(r.getDateDebut(), LocalDate.ofEpochDay(0));
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateLigneFonctionPersister_whenDelete_thenSuccess() {
		LigneFonction obj = new LigneFonction();
		obj.setDateDebut(LocalDate.now());
		obj.setDateFin(LocalDate.now());
		cut.create(obj);
		cut.delete(obj);
		assertNull(cut.read(obj.getIdLigneFonction()));
	}

	@Test
	void givenHibernateLigneFonctionPersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}
}