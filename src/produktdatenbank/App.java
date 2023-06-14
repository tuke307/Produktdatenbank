package produktdatenbank;

public class App {
    public static void main(String[] args) {
        DBParser import1 = new DBParser("C:\\Users\\MEISSTO\\Projects\\Produktdatenbank_TINF22C_4567455\\productproject2023.db");

        System.out.print(import1.getFileContent());
    }
}
