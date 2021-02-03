package test.sga.repositories;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sga.entities.Don;
import com.sga.entities.Donneur;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonPersister;
import com.sga.repositories.HibernateDonneurPersister;
import com.sga.repositories.HibernateStructurePersister;

public class HibernateDonPersisterTest {
	HibernateDonPersister cut;

	@BeforeEach
	void setUp() {
		cut = new HibernateDonPersister();
	}

	@AfterEach
	void tearDown() {
		cut = null;
	}

	@Test
	void givenHibernateDonPersister_whenGetAll_thenReturnNotNullList() {
		assertNotNull(cut.getAll());
	}

	@Test
	void givenHibernateDonPersister_whenGetById_thenReturnNotNullRecord() {
		Don o = new Don();
		o.setMontant(10000.0);
		o.setDateDon(LocalDate.now());
		Donneur d = new Donneur();
		d.setAdresse("Test City");
		d.setTelephone("0600000000");
		d.setEmail("test@test.com");
		d.setMotDePasse("S3CR3T");
		d.setDonList(new ArrayList<>());
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
		d.setStructure(s);
		(new HibernateDonneurPersister()).create(d);
		o.setDonneur(d);
		cut.create(o);
		assertNotNull(cut.read(o.getIdDon()));
		(new HibernateDonneurPersister()).delete(d);
		cut.delete(o);
	}
}
