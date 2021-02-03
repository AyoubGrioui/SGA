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

import com.sga.entities.Adherent;
import com.sga.entities.Fonction;
import com.sga.entities.LigneFonction;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.repositories.HibernateFonctionPersister;
import com.sga.repositories.HibernateLigneFonctionPersister;
import com.sga.repositories.HibernateStructurePersister;

class HibernateAdherentPersisterTest {
	HibernateAdherentPersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateAdherentPersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateAdherentPersister_whenCreate_thenPersist() {
		Adherent obj = new Adherent();
		obj.setNom("Test");
		obj.setCin("AB0000");
		obj.setDateNaissance(LocalDate.now());
		obj.setAdresse("Test");
		obj.setEmail("test@test.com");
		obj.setDateAdhesion(LocalDate.now());
		obj.setTelephone("0610238438");
		obj.setLieuNaissance("Test");
		obj.setMotDePasse("********");
		obj.setPhoto("img.png");
		obj.setPrenom("Test");
		obj.setProfession("Test");
		obj.setSexe("Homme");
		LigneFonction ligneFonction = new LigneFonction();
		ligneFonction.setDateDebut(LocalDate.now());
		ligneFonction.setDateFin(LocalDate.now());
		Fonction f = new Fonction();
		f.setRole("Test");
		(new HibernateFonctionPersister()).create(f);
		ligneFonction.setFonction(f);
		(new HibernateLigneFonctionPersister()).create(ligneFonction);
		obj.setLigneFonction(ligneFonction);
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
		Adherent r = (Adherent) session.createQuery("from Adherent where id=:id")
				.setParameter("id", obj.getIdAdherent()).setMaxResults(1).list().get(0);
		assertEquals(r.getNom(), obj.getNom());
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateAdherentPersister_whenUpdate_thenPersist() {
		Adherent obj = new Adherent();
		obj.setNom("Test");
		obj.setCin("AB0000");
		obj.setDateNaissance(LocalDate.now());
		obj.setAdresse("Test");
		obj.setEmail("test@test.com");
		obj.setDateAdhesion(LocalDate.now());
		obj.setTelephone("0610238438");
		obj.setLieuNaissance("Test");
		obj.setMotDePasse("********");
		obj.setPhoto("img.png");
		obj.setPrenom("Test");
		obj.setProfession("Test");
		obj.setSexe("Homme");
		LigneFonction ligneFonction = new LigneFonction();
		ligneFonction.setDateDebut(LocalDate.now());
		ligneFonction.setDateFin(LocalDate.now());
		Fonction f = new Fonction();
		f.setRole("Test");
		(new HibernateFonctionPersister()).create(f);
		ligneFonction.setFonction(f);
		(new HibernateLigneFonctionPersister()).create(ligneFonction);
		obj.setLigneFonction(ligneFonction);
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
		Adherent r = (Adherent) session.createQuery("from Adherent ORDER BY idAdherent DESC").setMaxResults(1).list()
				.get(0);
		assertEquals(r.getNom(), "Modified");
		cut.delete(obj);
		session.close();
	}

	@Test
	void givenHibernateAdherentPersister_whenDelete_thenSuccess() {
		Adherent obj = new Adherent();
		obj.setNom("Test");
		obj.setCin("AB0000");
		obj.setDateNaissance(LocalDate.now());
		obj.setAdresse("Test");
		obj.setEmail("test@test.com");
		obj.setDateAdhesion(LocalDate.now());
		obj.setTelephone("0610238438");
		obj.setLieuNaissance("Test");
		obj.setMotDePasse("********");
		obj.setPhoto("img.png");
		obj.setPrenom("Test");
		obj.setProfession("Test");
		obj.setSexe("Homme");
		LigneFonction ligneFonction = new LigneFonction();
		ligneFonction.setDateDebut(LocalDate.now());
		ligneFonction.setDateFin(LocalDate.now());
		Fonction f = new Fonction();
		f.setRole("Test");
		(new HibernateFonctionPersister()).create(f);
		ligneFonction.setFonction(f);
		(new HibernateLigneFonctionPersister()).create(ligneFonction);
		obj.setLigneFonction(ligneFonction);
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
		assertNull(cut.read(obj.getIdAdherent()));
	}

	@Test
	void givenHibernateAdherentPersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}

	@Test
	void givenHibernateAdherentPersister_whenGetByEmail_thenReturnNotNullRecord() {
		Adherent obj = new Adherent();
		obj.setNom("Test");
		obj.setCin("AB0000");
		obj.setDateNaissance(LocalDate.now());
		obj.setAdresse("Test");
		obj.setEmail("test@test.com");
		obj.setDateAdhesion(LocalDate.now());
		obj.setTelephone("0610238438");
		obj.setLieuNaissance("Test");
		obj.setMotDePasse("********");
		obj.setPhoto("img.png");
		obj.setPrenom("Test");
		obj.setProfession("Test");
		obj.setSexe("Homme");
		LigneFonction ligneFonction = new LigneFonction();
		ligneFonction.setDateDebut(LocalDate.now());
		ligneFonction.setDateFin(LocalDate.now());
		Fonction f = new Fonction();
		f.setRole("Test");
		(new HibernateFonctionPersister()).create(f);
		ligneFonction.setFonction(f);
		(new HibernateLigneFonctionPersister()).create(ligneFonction);
		obj.setLigneFonction(ligneFonction);
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
		Adherent r = cut.getByEmail("test@test.com");
		assertNotNull(r);
		cut.delete(obj);
		session.close();
	}
}