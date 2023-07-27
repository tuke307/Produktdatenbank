package produktdatenbank.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import produktdatenbank.Constants;
import produktdatenbank.functions.DBParser;
import produktdatenbank.model.Firma;
import produktdatenbank.model.Besitzt;
import produktdatenbank.model.Produkt;
import produktdatenbank.model.Freundschaft;
import produktdatenbank.model.Person;
import produktdatenbank.model.Herstellung;
import produktdatenbank.singleton.DBSingleton;

public class ImporterTest {

    @Test
    public void testFileImport() {
        DBParser importer = new DBParser();

        assertNotNull(importer.readFile());
    }

    @Test
    public void testDBParser() {
        DBSingleton dbSingleton;
        dbSingleton = DBSingleton.getInstance();

        DBParser importer = new DBParser();
        importer.readFile();
        importer.parseContent();

        assertEquals(200, dbSingleton.getPersonen().size());
        assertEquals(10, dbSingleton.getProdukte().size());
        assertEquals(3, dbSingleton.getFirmen().size());
        assertEquals(793, dbSingleton.getFreundschaften().size());
        assertEquals(441, dbSingleton.getBesitze().size());
        assertEquals(10, dbSingleton.getHerstellungen().size());

        Person firstPerson = dbSingleton.getPersonen().get(0);
        assertEquals(0, firstPerson.getId());
        assertEquals("Ellis Blair", firstPerson.getName());
        assertEquals("Male", firstPerson.getGeschlecht());

        Produkt firstProdukt = dbSingleton.getProdukte().get(0);
        assertEquals(203, firstProdukt.getId());
        assertEquals("iPad", firstProdukt.getName());

        Firma firstFirma = dbSingleton.getFirmen().get(0);
        assertEquals(200, firstFirma.getId());
        assertEquals("Apple", firstFirma.getName());

        Freundschaft firstFreundschaft = dbSingleton.getFreundschaften().get(0);
        assertEquals(0, firstFreundschaft.getPersonId1());
        assertEquals(89, firstFreundschaft.getPersonId2());

        Besitzt firstBesitzt = dbSingleton.getBesitze().get(0);
        assertEquals(196, firstBesitzt.getPersonId());
        assertEquals(203, firstBesitzt.getProduktId());

        Herstellung firstHerstellung = dbSingleton.getHerstellungen().get(0);
        assertEquals(204, firstHerstellung.getProduktId());
        assertEquals(200, firstHerstellung.getFirmaId());
    }
}
