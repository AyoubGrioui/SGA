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

import com.sga.entities.DonEspece;
import com.sga.entities.DonneurMoral;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonEspecePersister;
import com.sga.repositories.HibernateDonneurMoralPersister;
import com.sga.repositories.HibernateStructurePersister;

class HibernateDonEspecePersisterTest {
	HibernateDonEspecePersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateDonEspecePersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateDonEspecePersister_whenCreate_thenPersist() {
		DonEspece obj = new DonEspece();
		obj.setDateDon(LocalDate.now());
		obj.setMontant(10000.0);
		DonneurMoral donneur = new DonneurMoral();
		donneur.setAdresse("Test City");
		donneur.setNom("Tester");
		donneur.setEmail("test@test.com");
		donneur.setTelephone("066600000");
		donneur.setMotDePasse("S3CR3T");
		donneur.setDonList(new ArrayList<>());
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
		donneur.setStructure(s);
		(new HibernateDonneurMoralPersister()).create(donneur);
		obj.setDonneur(donneur);
		cut.create(obj);
		Session session = cut.getSession();
		DonEspece r = (DonEspece) session.createQuery("from DonEspece ORDER BY idDon DESC").setMaxResults(1).list()
				.get(0);
		assertNotNull(r);
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateDonEspecePersister_whenUpdate_thenPersist() {
		DonEspece obj = new DonEspece();
		obj.setDateDon(LocalDate.now());
		obj.setMontant(10000.0);
		DonneurMoral donneur = new DonneurMoral();
		donneur.setAdresse("Test City");
		donneur.setNom("Tester");
		donneur.setEmail("test@test.com");
		donneur.setTelephone("066600000");
		donneur.setMotDePasse("S3CR3T");
		donneur.setDonList(new ArrayList<>());
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
		donneur.setStructure(s);
		(new HibernateDonneurMoralPersister()).create(donneur);
		obj.setDonneur(donneur);
		cut.create(obj);
		obj.setMontant(100.0);
		cut.update(obj);
		Session session = cut.getSession();
		DonEspece r = (DonEspece) session.createQuery("from DonEspece ORDER BY idDon DESC").setMaxResults(1).list()
				.get(0);
		assertEquals(r.getMontant(), 100.0);
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateDonEspecePersister_whenDelete_thenSuccess() {
		DonEspece obj = new DonEspece();
		obj.setDateDon(LocalDate.now());
		obj.setMontant(10000.0);
		DonneurMoral donneur = new DonneurMoral();
		donneur.setAdresse("Test City");
		donneur.setNom("Tester");
		donneur.setEmail("test@test.com");
		donneur.setTelephone("066600000");
		donneur.setMotDePasse("S3CR3T");
		donneur.setDonList(new ArrayList<>());
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
		donneur.setStructure(s);
		(new HibernateDonneurMoralPersister()).create(donneur);
		obj.setDonneur(donneur);
		cut.create(obj);
		cut.delete(obj);
		assertNull(cut.read(obj.getIdDon()));
	}

	@Test
	void givenHibernateDonEspecePersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}
}