package produktdatenbank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import produktdatenbank.model.Person;
import produktdatenbank.model.Produkt;
import produktdatenbank.model.Firma;
import produktdatenbank.model.Freundschaft;
import produktdatenbank.model.Besitzt;
import produktdatenbank.model.Herstellung;


public class DBParser {
    private String filepath;
    private String fileContent;

    private List<Person> personen = new ArrayList<>();
    private List<Produkt> produkte = new ArrayList<>();
    private List<Firma> firmen = new ArrayList<>();
    private List<Freundschaft> freundschaften = new ArrayList<>();
    private List<Besitzt> besitze = new ArrayList<>();
    private List<Herstellung> herstellungen = new ArrayList<>();

    private static final String INSERT_PERSON = "New_Entity: person_id, person_name, person_gender";
    private static final String INSERT_PROUDKT = "New_Entity: product_id,product_name";
    private static final String INSERT_FIRMA= "New_Entity: company_id,company_name";
    private static final String INSERT_FREUNDSCHAFT = "New_Entity: person1_id,person2_id";
    private static final String INSERT_BESITZT = "New_Entity: person_id,product_id";
    private static final String INSERT_HERSTELLUNG = "New_Entity: product_id,company_id";

    public DBParser(String filepath) {
        this.filepath = filepath;

        readFileToString();
        parseContent();
    }

    private void parseContent(){
        // split fileContent into lines
        List<String> lines = new ArrayList<String>();
        lines.addAll(Arrays.asList(fileContent.split(System.lineSeparator())));

        // iterate over lines and get index of INSERT_PERSON, INSERT_PROUDKT, INSERT_FIRMA, INSERT_FREUNDSCHAFT, INSERT_BESITZT, INSERT_HERSTELLUNG
        int indexPerson = lines.indexOf(INSERT_PERSON);
        int indexProdukt = lines.indexOf(INSERT_PROUDKT);
        int indexFirma = lines.indexOf(INSERT_FIRMA);
        int indexFreundschaft = lines.indexOf(INSERT_FREUNDSCHAFT);
        int indexBesitzt = lines.indexOf(INSERT_BESITZT);
        int indexHerstellung = lines.indexOf(INSERT_HERSTELLUNG);

        // lower bound is inclusive, The upper bound is exclusive
        List<String> listPerson = lines.subList(indexPerson + 1, indexProdukt);
        List<String> listProdukt = lines.subList(indexProdukt + 1, indexFirma);
        List<String> listFirma = lines.subList(indexFirma + 1, indexFreundschaft);
        List<String> listFreundschaft = lines.subList(indexFreundschaft + 1, indexBesitzt);
        List<String> listBesitzt = lines.subList(indexBesitzt + 1, indexHerstellung);
        List<String> listHerstellung = lines.subList(indexHerstellung + 1, lines.size());
        
        // iterate over listPerson and create Person objects
        for (String line : listPerson) {
            String[] person = line.split(",", -1);
            Person p = new Person(Integer.parseInt(person[0]), person[1], person[2]);
            personen.add(p);
        }

        // iterate over listProdukt and create Produkt objects
        for (String line : listProdukt) {
            String[] produkt = line.split(",");
            Produkt p = new Produkt(Integer.parseInt(produkt[0]), produkt[1]);
            produkte.add(p);
        }

        // iterate over listFirma and create Firma objects
        for (String line : listFirma) {
            String[] firma = line.split(",");
            Firma f = new Firma(Integer.parseInt(firma[0]), firma[1]);
            firmen.add(f);
        }

        // iterate over listFreundschaft and create Freundschaft objects
        for (String line : listFreundschaft) {
            String[] freundschaft = line.split(",");
            Freundschaft f = new Freundschaft(Integer.parseInt(freundschaft[0]), Integer.parseInt(freundschaft[1]));
            freundschaften.add(f);
        }

        // iterate over listBesitzt and create Besitzt objects
        for (String line : listBesitzt) {
            String[] besitzt = line.split(",");
            Besitzt b = new Besitzt(Integer.parseInt(besitzt[0]), Integer.parseInt(besitzt[1]));
            besitze.add(b);
        }

        // iterate over listHerstellung and create Herstellung objects
        for (String line : listHerstellung) {
            String[] herstellung = line.split(",");
            Herstellung h = new Herstellung(Integer.parseInt(herstellung[0]), Integer.parseInt(herstellung[1]));
            herstellungen.add(h);
        }
    }

    private void readFileToString() {
        File f = new File(this.filepath);
        this.fileContent = "";

        try (BufferedReader bf = new BufferedReader(
                new FileReader(f))) {
            String line = null;
            while ((line = bf.readLine()) != null) {
                this.fileContent += line + System.lineSeparator();
            }

            this.fileContent = this.fileContent.replaceAll("\"", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getFilepath() {
        return filepath;
    }

    public String getFileContent() {
        return fileContent;
    }
}
