package produktdatenbank.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import produktdatenbank.model.Besitzt;
import produktdatenbank.model.Firma;
import produktdatenbank.model.Freundschaft;
import produktdatenbank.model.Herstellung;
import produktdatenbank.model.Person;
import produktdatenbank.model.Produkt;

public class DBSingleton {
    private List<Person> personen = new ArrayList<>();
    private List<Produkt> produkte = new ArrayList<>();
    private List<Firma> firmen = new ArrayList<>();
    private List<Freundschaft> freundschaften = new ArrayList<>();
    private List<Besitzt> besitze = new ArrayList<>();
    private List<Herstellung> herstellungen = new ArrayList<>();

    private Logger logger;

    // Static variable reference of single_instance of type DBSingleton
    private static DBSingleton single_instance = null;

    private DBSingleton() {
        logger = Logger.getLogger(DBSingleton.class.getName());
    }

    // Static method to create instance of Singleton class
    public static synchronized DBSingleton getInstance() {
        if (single_instance == null)
            single_instance = new DBSingleton();

        return single_instance;
    }

    // region Getter

    public List<Person> getPersonen() {
        return personen;
    }

    public List<Produkt> getProdukte() {
        return produkte;
    }

    public List<Firma> getFirmen() {
        return firmen;
    }

    public List<Freundschaft> getFreundschaften() {
        return freundschaften;
    }

    public List<Besitzt> getBesitze() {
        return besitze;
    }

    public List<Herstellung> getHerstellungen() {
        return herstellungen;
    }

    // endregion

    // region Setter

    /*
     * Add person to personen list
     */
    public void addPerson(int id, String name, String geschlecht) {
        // check if person already exists by checking id
        for (Person person : personen) {
            if (person.getId() == id) {
                logger.warning("Person with id " + id + " already exists and will not be imported.");
                return;
            }
        }

        personen.add(new Person(id, name, geschlecht));
    }

    /*
     * Add produkt to produkte list
     */
    public void addProdukt(int id, String name) {
        // check if produkt already exists by checking id
        for (Produkt produkt : produkte) {
            if (produkt.getId() == id) {
                logger.warning("Produkt with id " + id + " already exists and will not be imported.");
                return;
            }
        }

        produkte.add(new Produkt(id, name));
    }

    /**
     * Add firma to firmen list
     * 
     * @param id   id of the firma
     * @param name name of the firma
     */
    public void addFirma(int id, String name) {
        // check if firma already exists by checking id
        for (Firma firma : firmen) {
            if (firma.getId() == id) {
                logger.warning("Firma with id " + id + " already exists and will not be imported.");
                return;
            }
        }

        firmen.add(new Firma(id, name));
    }

    /**
     * Add freundschaft to freundschaften list
     * 
     * @param personId1 person id 1
     * @param personId2 person id 2
     */
    public void addFreundschaft(int personId1, int personId2) {
        // freundschaft is bidirectional, so we need to check both directions
        for (Freundschaft f : freundschaften) {
            if ((f.getPerson_id1() == personId1 && f.getPerson_id2() == personId2)
                    || (f.getPerson_id1() == personId2 && f.getPerson_id2() == personId1)) {
                logger.warning("Freundschaft with personId1 " + personId1 + " and personId2 " + personId2
                        + " already exists and will not be imported.");
            }
        }

        freundschaften.add(new Freundschaft(personId1, personId2));
    }

    /*
     * Add besitzt to besitze list
     */
    public void addBesitzt(int personId, int produktId) {
        // check if besitzt already exists by checking personId and produktId
        for (Besitzt besitz : besitze) {
            if (besitz.getPersonId() == personId && besitz.getProduktId() == produktId) {
                logger.warning("Besitzt with personId " + personId + " and produktId " + produktId
                        + " already exists and will not be imported.");
                return;
            }
        }

        besitze.add(new Besitzt(personId, produktId));
    }

    /*
     * Add herstellung to herstellungen list
     */
    public void addHerstellung(int firmaId, int produktId) {
        // check if herstellung already exists by checking firmaId and produktId
        for (Herstellung herstellung : herstellungen) {
            if (herstellung.getFirmaId() == firmaId && herstellung.getProduktId() == produktId) {
                logger.warning("Herstellung with firmaId " + firmaId + " and produktId " + produktId
                        + " already exists and will not be imported.");
                return;
            }
        }

        herstellungen.add(new Herstellung(firmaId, produktId));
    }

    // endregion

    // region Searching methods

    /*
     * Search for person by name
     */
    public String personensuche(String personName) {
        String result = "";

        for (Person person : personen) {
            if (person.getName().toLowerCase().contains(personName.toLowerCase())) {
                result += person.toString() + System.lineSeparator();
            }
        }

        if (result.isEmpty()) {
            result = "Keine Personen gefunden.";
        }

        return result;
    }

    /*
     * Search for produkt by name
     */
    public String produktsuche(String produktName) {
        String result = "";

        for (Produkt produkt : produkte) {
            if (produkt.getName().toLowerCase().contains(produktName.toLowerCase())) {
                result += produkt.toString() + System.lineSeparator();
            }
        }

        if (result.isEmpty()) {
            result = "Keine Produkte gefunden.";
        }

        return result;
    }

    public List<Freundschaft> getFreundschaftenForPerson(int person_id) {
        List<Freundschaft> result = new ArrayList<>();
        for (Freundschaft f : freundschaften) {
            if (f.getPerson_id1() == person_id || f.getPerson_id2() == person_id) {
                result.add(f);
            }
        }
        return result;
    }

    /**
     * Search for all products, that friends from the passed person buyed
     * 
     * @param personId
     * @return all product names comma separated
     */
    public String produktnetzwerk(Integer personId) {
        String result = "";
        List<Freundschaft> freundschaftenWithPerson = new ArrayList<>();

        // get all friends of the person
        for (Freundschaft f : freundschaften) {
            if (f.getPerson_id1() == personId || f.getPerson_id2() == personId) {
                freundschaftenWithPerson.add(f);
            }
        }

        // get all products of the friends
        for (Freundschaft f : freundschaftenWithPerson) {
            for (Besitzt b : besitze) {
                if (b.getPersonId() == f.getPerson_id1() || b.getPersonId() == f.getPerson_id2()) {
                    for (Produkt p : produkte) {
                        if (p.getId() == b.getProduktId()) {
                            result += p.getName() + ", ";
                        }
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result = "Nichts zum Produktnetzwerk gefunden.";
        }

        return result;
    }

    public String firmennetzwerk(Integer firmaId) {
        String result = "";

        /*
         * for (Herstellung herstellung : herstellungen) {
         * if (herstellung.getFirmaId() == Integer.parseInt(firmaId)) {
         * for (Produkt produkt : produkte) {
         * if (produkt.getId() == herstellung.getProduktId()) {
         * result += produkt.toString() + System.lineSeparator();
         * }
         * }
         * }
         * }
         */

        if (result.isEmpty()) {
            result = "Nichts zum Firmennetzwerk gefunden.";
        }

        return result;
    }

    // endregion

    public void clear() {
        personen.clear();
        produkte.clear();
        firmen.clear();
        freundschaften.clear();
        besitze.clear();
        herstellungen.clear();
    }
}
