package produktdatenbank;

import produktdatenbank.functions.DBParser;
import produktdatenbank.singleton.DBSingleton;

public class App {
    private static String filepath = "C:\\Users\\MEISSTO\\Projects\\Produktdatenbank_TINF22C_4567455\\productproject2023.db";
    private static String ArgPersonensuche = "--personensuche=";
    private static String ArgProduktsuche = "--produktsuche=";
    private static String ArgProduktnetzwerk = "--produktnetzwerk=";
    private static String ArgFirmennetzwerk = "--firmennetzwerk=";

    /**
     * Main method to start the application
     * 
     * @param args 
     *             personensuche: z.B.: --personensuche="Ila"
     *             produktsuche: z.B.: --produktsuche=“Matrix"
     *             produktnetzwerk: z.B.: --produktnetzwerk=4899
     *             firmennetzwerk: z.B.: --firmennetzwerk=1756
     */
    public static void main(String[] args) {
        DBSingleton dbSingleton = DBSingleton.getInstance();
        DBParser importer = new DBParser(filepath);
        importer.readFile();
        importer.parseContent();

        for (String arg : args) {
            if (arg.startsWith(ArgPersonensuche)) {
                String personToSearch = arg.substring(ArgPersonensuche.length());
                System.out.println(dbSingleton.personensuche(personToSearch));
            } else if (arg.startsWith(ArgProduktsuche)) {
                String produktToSearch = arg.substring(ArgProduktsuche.length());
                System.out.println(dbSingleton.produktsuche(produktToSearch));
            } else if (arg.startsWith(ArgProduktnetzwerk)) {
                Integer produktNetzwerk = Integer.parseInt(arg.substring(ArgProduktnetzwerk.length()));
                System.out.println(dbSingleton.produktnetzwerk(produktNetzwerk));
            } else if (arg.startsWith(ArgFirmennetzwerk)) {
                Integer firmenNetzwerk = Integer.parseInt(arg.substring(ArgFirmennetzwerk.length()));
                System.out.println(dbSingleton.firmennetzwerk(firmenNetzwerk));
            } else {
                System.out.println("Argument not supported: " + arg);
            }
        }

        dbSingleton.clear();
    }
}
