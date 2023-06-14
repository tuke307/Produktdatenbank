package produktdatenbank.testing;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

public class ProjektTester {
    // Konfiguration eurer Hauptklasse. Bitte vollqualifizierten Klassennamen plus Paket angeben.
    // Z.b. die Klasse MeinProjekt im Paket dhbw.java, muss lauten: 'dhbw.java.MeinProjekt'
    private static final String MAIN_CLASS = "produktdatenbank.App";

    public static void main(String[] args) {
        // project.model.Tests are passing
        boolean passed = true;

        // Produktnetzwerk fuer Jaime Rogers
        passed = passedTestNetzwerk("--produktnetzwerk=53", "iPad Mini,MacBook Air,Samsung Galaxy 5");
        // Produktnetzwerk fuer Roger Walker
        passed &= passedTestNetzwerk("--produktnetzwerk=70", "iPad,Samsung ChromeBook,Samsung Galaxy Tab 3");
        // Produktnetzwerk fuer Brandy Francis
        passed &= passedTestNetzwerk("--produktnetzwerk=181", "Google Nexus 7,iPad,MacBook Air,Samsung Galaxy 5,Samsung Galaxy Tab 3");

        // Firmennetzwerk fuer Jaime Rogers
        passed &= passedTestNetzwerk("--firmennetzwerk=53", "Apple");
        // Firmennetzwerk fuer Roger Walker
        passed &= passedTestNetzwerk("--firmennetzwerk=70", "Apple,Samsung");
        // Firmennetzwerk fuer Brandy Francis
        passed &= passedTestNetzwerk("--firmennetzwerk=181", "Apple,Google");

        if (passed) {
            System.out.println("Alle Tests bestanden :-)");
        } else {
            System.out.println("Leider nicht alle Tests bestanden :-(");
        }
    }

    /**
     * Ueberprueft ob Aufruf den erwarteten Ausgabestring beinhaltet.
     *
     * @param arg          Programmargument
     * @param resultString String, welcher als Ausgabe erwartet wird
     * @return
     */
    private static boolean passedTestNetzwerk(String arg, String resultString) {
        // Der System.out Stream muss umgebogen werden, damit dieser spaeter ueberprueft werden kann.
        PrintStream normalerOutput = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String[] args = {arg};
        try {
            // MainClass mittels Reflection bekommen und main Methode aufrufen
            Class<?> mainClass = Class.forName(MAIN_CLASS);
            Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
            mainMethod.invoke(null, (Object) args);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Main-Klasse konnte nicht geladen werden, bitte Konfiguration pruefen.");
            System.exit(1);
        } finally {
            // System.out wieder zuruecksetzen
            System.setOut(normalerOutput);
        }

        // Ergebnisse ueberpruefen.
        String output = baos.toString();
        String[] lines = output.split(System.lineSeparator());
        // Pryefe ob eine Zeile in der Ausgabe dem Format entspricht
        for (String line : lines) {
            // keine Leerzeichen beachten
            if (line.replace(" ", "").equals(resultString.replace(" ", ""))) {
                return true;
            }
        }
        System.err.println("Feher bei: '" + arg + "'. Erwartetes Ergebnis: '" + resultString + "', erhaltenes Ergebnis: '" + output.replace(System.lineSeparator(), "") + "'");
        return false;
    }

}