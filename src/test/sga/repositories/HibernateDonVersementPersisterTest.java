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

import com.sga.entities.DonVersement;
import com.sga.entities.DonneurMoral;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonVersementPersister;
import com.sga.repositories.HibernateDonneurMoralPersister;
import com.sga.repositories.HibernateStructurePersister;

class HibernateDonVersementPersisterTest {
	HibernateDonVersementPersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateDonVersementPersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateDonVersementPersister_whenCreate_thenPersist() {
		DonVersement obj = new DonVersement();
		obj.setDateDon(LocalDate.now());
		obj.setMontant(10000.0);
		obj.setDateDon(LocalDate.now());
		obj.setNumeroCompteBanque("ABC123");
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
		DonVersement r = (DonVersement) session.createQuery("from DonVersement ORDER BY idDon DESC").setMaxResults(1)
				.list().get(0);
		assertNotNull(r);
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateDonVersementPersister_whenUpdate_thenPersist() {
		DonVersement obj = new DonVersement();
		obj.setDateDon(LocalDate.now());
		obj.setMontant(10000.0);
		obj.setDateDon(LocalDate.now());
		obj.setNumeroCompteBanque("ABC123");
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
		DonVersement r = (DonVersement) session.createQuery("from DonVersement ORDER BY idDon DESC").setMaxResults(1)
				.list().get(0);
		assertEquals(r.getMontant(), 100.0);
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateDonVersementPersister_whenDelete_thenSuccess() {
		DonVersement obj = new DonVersement();
		obj.setDateDon(LocalDate.now());
		obj.setMontant(10000.0);
		obj.setDateDon(LocalDate.now());
		obj.setNumeroCompteBanque("ABC123");
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
	void givenHibernateDonVersementPersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}
}