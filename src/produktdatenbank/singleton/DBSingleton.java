package produktdatenbank.singleton;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    private static DBSingleton singleInstance = null;

    private DBSingleton() {
        logger = Logger.getLogger(DBSingleton.class.getName());
    }

    public static synchronized DBSingleton getInstance() {
        if (singleInstance == null)
            singleInstance = new DBSingleton();

        return singleInstance;
    }

    // region Getter

    public List<Person> getPersonen() {
        return personen;
    }

    public Person getPersonById(int id) {
        for (Person person : personen) {
            if (person.getId() == id) {
                return person;
            }
        }

        return null;
    }

    public List<Produkt> getProdukte() {
        return produkte;
    }

    public Produkt getProduktById(int id) {
        for (Produkt produkt : produkte) {
            if (produkt.getId() == id) {
                return produkt;
            }
        }

        return null;
    }

    public List<Firma> getFirmen() {
        return firmen;
    }

    public Firma getFirmaById(int id) {
        for (Firma firma : firmen) {
            if (firma.getId() == id) {
                return firma;
            }
        }

        return null;
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

    /**
     * Add person to personen list
     *
     * @param personId        id of the person
     * @param personName      name of the person
     * @param personGeschlecht      geschlecht of the person
     */
    public void addPerson(int personId, String personName, String personGeschlecht) {
        // check if person already exists by checking id
        for (Person person : personen) {
            if (person.getId() == personId) {
                logger.warning("Person with id " + personId + " already exists and will not be imported.");
                return;
            }
        }

        personen.add(new Person(personId, personName, personGeschlecht));
    }

    /**
     * Add produkt to produkte list
     *
     * @param produktId   id of the produkt
     * @param produktName name of the produkt
     */
    public void addProdukt(int produktId, String produktName) {
        // check if produkt already exists by checking id
        for (Produkt produkt : produkte) {
            if (produkt.getId() == produktId) {
                logger.warning("Produkt with id " + produktId + " already exists and will not be imported.");
                return;
            }
        }

        produkte.add(new Produkt(produktId, produktName));
    }

    /**
     * Add firma to firmen list
     * 
     * @param firmaId id of the firma
     * @param firmaName name of the firma
     */
    public void addFirma(int firmaId, String firmaName) {
        // check if firma already exists by checking id
        for (Firma firma : firmen) {
            if (firma.getId() == firmaId) {
                logger.warning("Firma with id " + firmaId + " already exists and will not be imported.");
                return;
            }
        }

        firmen.add(new Firma(firmaId, firmaName));
    }

    /**
     * Add freundschaft to freundschaften list
     *
     * @param personId1 id of the first person
     * @param personId2 id of the second person
     * @param bidirectional if true, add freundschaft both ways
     */
    public void addFreundschaft(int personId1, int personId2, boolean bidirectional) {
        // check if freundschaft already exists by checking personId1 and personId2
        for (Freundschaft f : freundschaften) {
            if (f.getPersonId1() == personId1 && f.getPersonId2() == personId2) {
                logger.warning("Freundschaft with personId1 " + personId1 + " and personId2 " + personId2
                        + " already exists and will not be imported.");
                return;
            }
        }

        // freundschaft is bidirectional, so we need to add two both ways
        freundschaften.add(new Freundschaft(personId1, personId2));
        if (bidirectional)
            freundschaften.add(new Freundschaft(personId2, personId1));
    }

    /**
     * Add besitzt to Besitzt list
     *
     * @param personId  id of the person
     * @param produktId id of the produkt
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

    /**
     * Add herstellung to herstellungen list
     *
     * @param firmaId   id of the firma
     * @param produktId id of the produkt
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

    /**
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
        }else{
            // remove last line separator
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }

    /**
     * Search for produkt by name
     * @param produktName name of the produkt
     * @return all products
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
        else{
            // remove last line separator
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }

    /**
     * Search for all products, that friends from the passed person buyed
     * 
     * @param personId id of the person
     * @return all products
     */
    private List<Produkt> produkteFromFreundschaft(Integer personId) {
        List<Person> freundschaftenFromPerson = new ArrayList<>();
        List<Produkt> produkteFromFreundschaften = new ArrayList<>();

        // get all friends of the person
        for (Freundschaft freundschaft : freundschaften) {
            if (freundschaft.getPersonId1() == personId) {
                freundschaftenFromPerson.add(getPersonById(freundschaft.getPersonId2()));
            }
        }

        // get all products from the friends
        // duplicate products will be ignored
        for (Person person : freundschaftenFromPerson) {
            // iterate over all käufe
            for (Besitzt besitzt : besitze) {
                // if friend buyed a product
                if (besitzt.getPersonId() == person.getId()) {
                    // retrieve product and add it to the list, if not already in list
                    Produkt produkt = getProduktById(besitzt.getProduktId());

                    if (!produkteFromFreundschaften.contains(produkt)) {
                        produkteFromFreundschaften.add(produkt);
                    }
                }
            }
        }

        // order list by alphabatic order of product name
        Collator deCollator = Collator.getInstance(new Locale("de", "DE"));
        produkteFromFreundschaften.sort((a, b) -> deCollator.compare(a.getName(), b.getName()));

        return produkteFromFreundschaften;
    }

    /**
     * Search for all products, that friends from the passed person buyed
     * 
     * @param personId id of the person
     * @return all products
     */
    public String produktnetzwerk(Integer personId) {
        String result = "";
        List<Produkt> produkteFromFreundschaften = produkteFromFreundschaft(personId);

        // get products, which the passed person buyed
        List<Produkt> produkteFromPerson = besitze.stream().filter(b -> b.getPersonId() == personId)
                .map(b -> getProduktById(b.getProduktId())).collect(Collectors.toList());

        // remove products, which the passed person already buyed
        produkteFromFreundschaften.removeAll(produkteFromPerson);

        // create result string
        for (Produkt produkt : produkteFromFreundschaften) {
            result += produkt.getName() + ", ";
        }

        if (result.isEmpty()) {
            result = "Nichts zum Produktnetzwerk gefunden.";
        }else{
            // remove last line separator
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }

    /**
     * Search for all companies, that produce products, that friends from the passed
     * person have buyed
     * 
     * @param personId id of the person
     * @return all companies
     */
    public String firmennetzwerk(Integer personId) {
        String result = "";
        List<Firma> firmenFromFreundschaften = new ArrayList<>();
        List<Produkt> produkteFromFreundschaften = produkteFromFreundschaft(personId);

        // search for companies that produce the products
        for (Produkt produkt : produkteFromFreundschaften) {
            for (Herstellung herstellung : herstellungen) {
                if (herstellung.getProduktId() == produkt.getId()) {
                    Firma firma = getFirmaById(herstellung.getFirmaId());

                    if (!firmenFromFreundschaften.contains(firma)) {
                        firmenFromFreundschaften.add(firma);
                    }
                }
            }
        }

        // get products, which the passed person buyed
        List<Produkt> produkteFromPerson = besitze.stream().filter(b -> b.getPersonId() == personId)
                .map(b -> getProduktById(b.getProduktId())).collect(Collectors.toList());

        // get companies, from products that the passed person buyed
        List<Firma> firmenFromPerson = new ArrayList<>();
        for (Produkt produkt : produkteFromPerson) {
            for (Herstellung herstellung : herstellungen) {
                if (herstellung.getProduktId() == produkt.getId()) {
                    Firma firma = getFirmaById(herstellung.getFirmaId());

                    if (!firmenFromPerson.contains(firma)) {
                        firmenFromPerson.add(firma);
                    }
                }
            }
        }

        // remove companies, which the passed person already buyed products from
        firmenFromFreundschaften.removeAll(firmenFromPerson);

        // order list by alphabatic order of company name
        Collator deCollator = Collator.getInstance(new Locale("de", "DE"));
        firmenFromFreundschaften.sort((a, b) -> deCollator.compare(a.getName(), b.getName()));

        // create result string
        for (Firma firma : firmenFromFreundschaften) {
            result += firma.getName() + ", ";
        }

        if (result.isEmpty()) {
            result = "Nichts zum Firmennetzwerk gefunden.";
        }
        else{
            // remove last line separator
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }

    // endregion
}
