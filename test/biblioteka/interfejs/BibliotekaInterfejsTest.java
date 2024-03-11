package biblioteka.interfejs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import biblioteka.Knjiga;

public abstract class BibliotekaInterfejsTest {

	BibliotekaInterfejs b;
	Knjiga k,k2;
	
	@BeforeEach
	void setUp() throws Exception {
		
		b = getInstance();
		
	    k = new Knjiga();
		k.setIsbn(1234567890);
		k.setNaslov("Knjiga 1");
		k.setIzdanje(1);
		k.setIzdavac("Laguna");
		
		k2 = new Knjiga();
		k2.setIsbn(9234967899l);
		k2.setNaslov("Knjiga 2");
		k2.setIzdanje(2);
		k2.setIzdavac("Vulkan");

		
	}

	public abstract BibliotekaInterfejs getInstance();

	@AfterEach
	void tearDown() throws Exception {
		
		b = null;
		k  = null;
		k2 = null;
		
	}

	@Test
	void testDodajKnjiguNull() {
		assertThrows(java.lang.NullPointerException.class, () -> b.dodajKnjigu(null));
		
	}
	
	@Test
	void testDodajKnjiguDuplikat() {

		b.dodajKnjigu(k);
		assertThrows(java.lang.IllegalArgumentException.class, () -> b.dodajKnjigu(k));
		
	}
	
	@Test
	void testDodajKnjigu() {

		b.dodajKnjigu(k);
		assertThrows(java.lang.IllegalArgumentException.class, () -> b.dodajKnjigu(k));
		
		List<Knjiga> sve = b.vratiSveKnjige();
		
		assertEquals(1, sve.size());
		assertEquals(k, sve.get(0));
		
		
	}
	
	@Test
	void testDodajKnjiguViseKomada() {

		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		
		
		List<Knjiga> sve = b.vratiSveKnjige();
		
		assertEquals(2, sve.size());
		assertTrue(sve.contains(k));
		assertTrue(sve.contains(k2));
		
		
	}

	@Test
	void testObrisiKnjiguNull() {
		
		assertThrows(java.lang.NullPointerException.class, () -> b.obrisiKnjigu(null) );
		
		
	}

	@Test
	void testObrisiKnjiguNePostoji() {
		
		b.dodajKnjigu(k);
		
		assertThrows(java.lang.IllegalArgumentException.class, () -> b.obrisiKnjigu(k2) );
		
		
	}
	
	@Test
	void testObrisiKnjigu() {
		
		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		
		b.obrisiKnjigu(k);
		
		List<Knjiga> sve = b.vratiSveKnjige();
		assertEquals(sve.size(), 1);
		assertEquals(k2, sve.get(0));
		
		
	}
	
	@Test
	void testPronadjiKnjigu() {
		
		assertThrows(java.lang.IllegalArgumentException.class, () -> b.pronadjiKnjigu(null, 0, null, null));
		
		
	}
	
	@Test
	void testPronadjiKnjiguNemaRezultata() {
		
		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		
		List<Knjiga> rez = b.pronadjiKnjigu(null, 0, "Avantura", null);
		assertEquals(0, rez.size());
		
	}
	
	@Test
	void testPronadjiKnjiguJedna() {
		
		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		
		List<Knjiga> rez = b.pronadjiKnjigu(null, 0, "1", null);
		assertEquals(1, rez.size());
		assertEquals(k, rez.get(0));
		
	}
	
	@Test
	void testPronadjiKnjiguVise() {
		
		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		
		List<Knjiga> rez = b.pronadjiKnjigu(null, 0, "knj", null);
		assertEquals(2, rez.size());
		assertEquals(true, rez.contains(k));
		assertEquals(true, rez.contains(k2));
		
	}

}
