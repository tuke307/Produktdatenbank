package produktdatenbank.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import produktdatenbank.Constants;
import produktdatenbank.singleton.DBSingleton;

public class DBParser {
    private String fileContent;

    private DBSingleton dbSingleton;

    public DBParser() {
        dbSingleton = DBSingleton.getInstance();
    }

    public void parseContent() {
        // split fileContent into lines
        List<String> lines = new ArrayList<>();
        lines.addAll(Arrays.asList(fileContent.split(System.lineSeparator())));

        // iterate over lines and get index of lines which are starting with New_Entity:*
        int indexPerson = lines.indexOf(Constants.InsertPerson);
        int indexProdukt = lines.indexOf(Constants.InsertProdukt);
        int indexFirma = lines.indexOf(Constants.InsertFirma);
        int indexFreundschaft = lines.indexOf(Constants.InsertFreundschaft);
        int indexBesitzt = lines.indexOf(Constants.InsertBesitzt);
        int indexHerstellung = lines.indexOf(Constants.InsertHerstellung);

        // subList: lower bound is inclusive, The upper bound is exclusive
        List<String> listPerson = lines.subList(indexPerson + 1, indexProdukt);
        List<String> listProdukt = lines.subList(indexProdukt + 1, indexFirma);
        List<String> listFirma = lines.subList(indexFirma + 1, indexFreundschaft);
        List<String> listFreundschaft = lines.subList(indexFreundschaft + 1, indexBesitzt);
        List<String> listBesitzt = lines.subList(indexBesitzt + 1, indexHerstellung);
        List<String> listHerstellung = lines.subList(indexHerstellung + 1, lines.size());

        for (String line : listPerson) {
            String[] person = line.split(",", -1);
            dbSingleton.addPerson(Integer.parseInt(person[0]), person[1], person[2]);
        }

        for (String line : listProdukt) {
            String[] produkt = line.split(",", -1);
            dbSingleton.addProdukt(Integer.parseInt(produkt[0]), produkt[1]);
        }

        for (String line : listFirma) {
            String[] firma = line.split(",", -1);
            dbSingleton.addFirma(Integer.parseInt(firma[0]), firma[1]);
        }

        for (String line : listFreundschaft) {
            String[] freundschaft = line.split(",", -1);
            dbSingleton.addFreundschaft(Integer.parseInt(freundschaft[0]), Integer.parseInt(freundschaft[1]), false);
        }

        for (String line : listBesitzt) {
            String[] besitzt = line.split(",", -1);
            dbSingleton.addBesitzt(Integer.parseInt(besitzt[0]), Integer.parseInt(besitzt[1]));
        }

        for (String line : listHerstellung) {
            String[] herstellung = line.split(",", -1);
            dbSingleton.addHerstellung(Integer.parseInt(herstellung[0]), Integer.parseInt(herstellung[1]));
        }
    }

    public String readFile() {
        File f = new File(Constants.Filepath);
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

        return fileContent;
    }

    public String getFileContent() {
        return fileContent;
    }
}
