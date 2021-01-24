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

import com.sga.entities.DonneurMoral;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonneurMoralPersister;
import com.sga.repositories.HibernateStructurePersister;

class HibernateDonneurMoralPersisterTest {
	HibernateDonneurMoralPersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateDonneurMoralPersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateDonneurMoralPersister_whenCreate_thenPersist() {
		DonneurMoral obj = new DonneurMoral();
		obj.setNom("Tester");
		obj.setAdresse("Test City");
		obj.setEmail("test@test.com");
		obj.setMotDePasse("S3CR3T");
		obj.setTelephone("057367894");
		obj.setDonList(new ArrayList<>());
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
		DonneurMoral r = (DonneurMoral) session.createQuery("from DonneurMoral ORDER BY idDonneur DESC")
				.setMaxResults(1).list().get(0);
		assertEquals(r.getNom(), obj.getNom());
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateDonneurMoralPersister_whenUpdate_thenPersist() {
		DonneurMoral obj = new DonneurMoral();
		obj.setNom("Tester");
		obj.setAdresse("Test City");
		obj.setEmail("test@test.com");
		obj.setMotDePasse("S3CR3T");
		obj.setTelephone("057367894");
		obj.setDonList(new ArrayList<>());
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
		obj.setNom("Modified");
		cut.update(obj);
		Session session = cut.getSession();

		DonneurMoral r = (DonneurMoral) session.createQuery("from DonneurMoral ORDER BY idDonneur DESC")
				.setMaxResults(1).list().get(0);

		assertEquals(r.getNom(), "Modified");
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateDonneurMoralPersister_whenDelete_thenSuccess() {
		DonneurMoral obj = new DonneurMoral();
		obj.setNom("Tester");
		obj.setAdresse("Test City");
		obj.setEmail("test@test.com");
		obj.setMotDePasse("S3CR3T");
		obj.setTelephone("057367894");
		obj.setDonList(new ArrayList<>());
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
		assertNull(cut.read(obj.getIdDonneur()));
	}

	@Test
	void givenHibernateDonneurMoralPersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}
}