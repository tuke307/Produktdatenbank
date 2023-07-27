package produktdatenbank;

import produktdatenbank.functions.DBParser;
import produktdatenbank.singleton.DBSingleton;

public class App {
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
        DBParser importer = new DBParser();
        importer.readFile();
        importer.parseContent();

        for (String arg : args) {
            if (arg.startsWith(Constants.ArgPersonensuche)) {
                String personToSearch = arg.substring(Constants.ArgPersonensuche.length());
                System.out.println(dbSingleton.personensuche(personToSearch));
            } else if (arg.startsWith(Constants.ArgProduktsuche)) {
                String produktToSearch = arg.substring(Constants.ArgProduktsuche.length());
                System.out.println(dbSingleton.produktsuche(produktToSearch));
            } else if (arg.startsWith(Constants.ArgProduktnetzwerk)) {
                Integer produktNetzwerk = Integer.parseInt(arg.substring(Constants.ArgProduktnetzwerk.length()));
                System.out.println(dbSingleton.produktnetzwerk(produktNetzwerk));
            } else if (arg.startsWith(Constants.ArgFirmennetzwerk)) {
                Integer firmenNetzwerk = Integer.parseInt(arg.substring(Constants.ArgFirmennetzwerk.length()));
                System.out.println(dbSingleton.firmennetzwerk(firmenNetzwerk));
            } else {
                System.out.println("Argument not supported: " + arg);
            }
        }
    }
}
