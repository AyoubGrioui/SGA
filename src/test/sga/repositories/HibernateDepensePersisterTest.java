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

import com.sga.entities.Depense;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDepensePersister;
import com.sga.repositories.HibernateStructurePersister;

class HibernateDepensePersisterTest {
	HibernateDepensePersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateDepensePersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateDepensePersister_whenCreate_thenPersist() {
		Depense obj = new Depense();
		obj.setTypeDepense("Charity");
		obj.setDateDepense(LocalDate.now());
		obj.setMontant(10000.0);
		obj.setIdAdherent(1L);
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
		(new HibernateStructurePersister()).create(s);
		obj.setStructure(s);
		cut.create(obj);
		Session session = cut.getSession();
		Depense r = (Depense) session.createQuery("from Depense ORDER BY idDepense DESC").setMaxResults(1).list()
				.get(0);
		assertNotNull(r);
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateDepensePersister_whenUpdate_thenPersist() {
		Depense obj = new Depense();
		obj.setTypeDepense("Charity");
		obj.setDateDepense(LocalDate.now());
		obj.setMontant(10000.0);
		obj.setIdAdherent(1L);
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
		(new HibernateStructurePersister()).create(s);
		obj.setStructure(s);
		cut.create(obj);
		obj.setTypeDepense("Modified");
		cut.update(obj);
		Session session = cut.getSession();
		Depense r = (Depense) session.createQuery("from Depense ORDER BY idDepense DESC").setMaxResults(1).list()
				.get(0);
		assertEquals(r.getTypeDepense(), "Modified");
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateDepensePersister_whenDelete_thenSuccess() {
		Depense obj = new Depense();
		obj.setTypeDepense("Charity");
		obj.setDateDepense(LocalDate.now());
		obj.setIdAdherent(1L);
		obj.setMontant(10000.0);
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
		(new HibernateStructurePersister()).create(s);
		obj.setStructure(s);
		cut.create(obj);
		cut.delete(obj);
		assertNull(cut.read(obj.getIdDepense()));
	}

	@Test
	void givenHibernateDepensePersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}
}