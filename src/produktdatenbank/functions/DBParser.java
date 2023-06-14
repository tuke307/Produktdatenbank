package produktdatenbank.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import produktdatenbank.model.Person;
import produktdatenbank.model.Produkt;
import produktdatenbank.singleton.DBSingleton;
import produktdatenbank.model.Firma;
import produktdatenbank.model.Freundschaft;
import produktdatenbank.model.Besitzt;
import produktdatenbank.model.Herstellung;

public class DBParser {
    private String filepath;
    private String fileContent;

    private DBSingleton dbSingleton;

    private static final String INSERT_PERSON = "New_Entity: person_id, person_name, person_gender";
    private static final String INSERT_PROUDKT = "New_Entity: product_id,product_name";
    private static final String INSERT_FIRMA = "New_Entity: company_id,company_name";
    private static final String INSERT_FREUNDSCHAFT = "New_Entity: person1_id,person2_id";
    private static final String INSERT_BESITZT = "New_Entity: person_id,product_id";
    private static final String INSERT_HERSTELLUNG = "New_Entity: product_id,company_id";

    public DBParser(String filepath) {
        this.filepath = filepath;

        dbSingleton = DBSingleton.getInstance();
    }

    public void parseContent() {
        // split fileContent into lines
        List<String> lines = new ArrayList<>();
        lines.addAll(Arrays.asList(fileContent.split(System.lineSeparator())));

        // iterate over lines and get index of lines which are starting with New_Entity:*
        int indexPerson = lines.indexOf(INSERT_PERSON);
        int indexProdukt = lines.indexOf(INSERT_PROUDKT);
        int indexFirma = lines.indexOf(INSERT_FIRMA);
        int indexFreundschaft = lines.indexOf(INSERT_FREUNDSCHAFT);
        int indexBesitzt = lines.indexOf(INSERT_BESITZT);
        int indexHerstellung = lines.indexOf(INSERT_HERSTELLUNG);

        // subList: lower bound is inclusive, The upper bound is exclusive
        List<String> listPerson = lines.subList(indexPerson + 1, indexProdukt);
        List<String> listProdukt = lines.subList(indexProdukt + 1, indexFirma);
        List<String> listFirma = lines.subList(indexFirma + 1, indexFreundschaft);
        List<String> listFreundschaft = lines.subList(indexFreundschaft + 1, indexBesitzt);
        List<String> listBesitzt = lines.subList(indexBesitzt + 1, indexHerstellung);
        List<String> listHerstellung = lines.subList(indexHerstellung + 1, lines.size());

        // iterate over listPerson and create Person objects
        for (String line : listPerson) {
            String[] person = line.split(",", -1);
            dbSingleton.addPerson(Integer.parseInt(person[0]), person[1], person[2]);
        }

        // iterate over listProdukt and create Produkt objects
        for (String line : listProdukt) {
            String[] produkt = line.split(",", -1);
            dbSingleton.addProdukt(Integer.parseInt(produkt[0]), produkt[1]);
        }

        // iterate over listFirma and create Firma objects
        for (String line : listFirma) {
            String[] firma = line.split(",", -1);
            dbSingleton.addFirma(Integer.parseInt(firma[0]), firma[1]);
        }

        // iterate over listFreundschaft and create Freundschaft objects
        for (String line : listFreundschaft) {
            String[] freundschaft = line.split(",", -1);
            dbSingleton.addFreundschaft(Integer.parseInt(freundschaft[0]), Integer.parseInt(freundschaft[1]));
        }

        // iterate over listBesitzt and create Besitzt objects
        for (String line : listBesitzt) {
            String[] besitzt = line.split(",", -1);
            dbSingleton.addBesitzt(Integer.parseInt(besitzt[0]), Integer.parseInt(besitzt[1]));
        }

        // iterate over listHerstellung and create Herstellung objects
        for (String line : listHerstellung) {
            String[] herstellung = line.split(",", -1);
            dbSingleton.addHerstellung(Integer.parseInt(herstellung[0]), Integer.parseInt(herstellung[1]));
        }
    }

    public void readFile() {
        File f = new File(this.filepath);
        this.fileContent = "";

        try (BufferedReader bf = new BufferedReader(
                new FileReader(f))) {
            String line = null;
            while ((line = bf.readLine()) != null) {
                this.fileContent += line + System.lineSeparator();
            }

            // replacements
            this.fileContent = this.fileContent.replaceAll("\"", ""); // Entfernt Anführungszeichen
            this.fileContent = this.fileContent.replaceAll("(?m)^[ \t]*\r?\n", ""); // Entfernt Leerzeilen
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
