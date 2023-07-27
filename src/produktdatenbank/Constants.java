package produktdatenbank;

public class Constants {
    public static final String Filepath = "C:\\Users\\MEISSTO\\Projects\\Produktdatenbank_TINF22C_4567455\\productproject2023.db";
    public static final String ArgPersonensuche = "--personensuche=";
    public static final String ArgProduktsuche = "--produktsuche=";
    public static final String ArgProduktnetzwerk = "--produktnetzwerk=";
    public static final String ArgFirmennetzwerk = "--firmennetzwerk=";
    public static final String InsertPerson = "New_Entity: person_id, person_name, person_gender";
    public static final String InsertProdukt = "New_Entity: product_id,product_name";
    public static final String InsertFirma = "New_Entity: company_id,company_name";
    public static final String InsertFreundschaft = "New_Entity: person1_id,person2_id";
    public static final String InsertBesitzt = "New_Entity: person_id,product_id";
    public static final String InsertHerstellung = "New_Entity: product_id,company_id";
}
