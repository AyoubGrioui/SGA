package test.sga.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sga.entities.Structure;
import com.sga.repositories.HibernateStructurePersister;

class HibernateStructurePersisterTest {
	HibernateStructurePersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateStructurePersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateStructurePersister_whenCreate_thenPersist() {
		Structure s = new Structure();
		s.setNom("Tester");
		s.setDateCreation(LocalDate.now());
		s.setAdresse("Test");
		s.setAdherentList(new ArrayList<>());
		s.setDepenseList(new ArrayList<>());
		s.setDonneurList(new ArrayList<>());
		s.setEmail("test@test.com");
		s.setObjectif("Test");
		s.setSiteWeb("www.test.com");
		cut.create(s);
		Session session = cut.getSession();
		Structure r = (Structure) session.createQuery("from Structure ORDER BY idStructure DESC").setMaxResults(1)
				.list().get(0);
		assertEquals(r.getNom(), s.getNom());
		cut.delete(s);
		session.close();
	}

	@Test
	void givenHibernateStructurePersister_whenUpdate_thenPersist() {
		Structure s = new Structure();
		s.setNom("Test");
		s.setDateCreation(LocalDate.now());
		s.setAdresse("Test");
		s.setAdherentList(new ArrayList<>());
		s.setDepenseList(new ArrayList<>());
		s.setDonneurList(new ArrayList<>());
		s.setEmail("test@test.com");
		s.setObjectif("Test");
		s.setSiteWeb("www.test.com");
		cut.create(s);
		s.setNom("Modified");
		cut.update(s);
		Session session = cut.getSession();
		Structure r = (Structure) session.createQuery("from Structure ORDER BY idStructure DESC").setMaxResults(1)
				.list().get(0);
		assertEquals(r.getNom(), "Modified");
		cut.delete(s);
		session.close();
	}

	@Test
	void givenHibernateStructurePersister_whenDelete_thenSuccess() {
		Structure s = new Structure();
		s.setNom("Test");
		s.setDateCreation(LocalDate.now());
		s.setAdresse("Test");
		s.setAdherentList(new ArrayList<>());
		s.setDepenseList(new ArrayList<>());
		s.setDonneurList(new ArrayList<>());
		s.setEmail("test@test.com");
		s.setObjectif("Test");
		s.setSiteWeb("www.test.com");
		cut.create(s);
		cut.delete(s);
		assertNull(cut.read(s.getIdStructure()));
	}

	@Test
	void givenHibernateStructurePersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}
}