package produktdatenbank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import produktdatenbank.Constants;
import produktdatenbank.functions.DBParser;
import produktdatenbank.singleton.DBSingleton;

import java.util.logging.Logger;
import java.util.stream.Stream;

public class SearchTest {
    private Logger logger = Logger.getLogger(SearchTest.class.getName());
    private static DBSingleton dbSingleton = null;

    @BeforeAll
    public static void initializeSingleton(){
        dbSingleton = DBSingleton.getInstance();

        DBParser importer = new DBParser(Constants.Filepath);
        importer.readFile();
        importer.parseContent();
    }

    @ParameterizedTest
    @MethodSource("testPersonensuche")
    public void testPersonensuche(String personToSearch, String expectedString) {
        logger.info("Personensuche fuer " + personToSearch);

        assertEquals(expectedString, dbSingleton.personensuche(personToSearch));
    }

    static Stream<Arguments> testPersonensuche() {
        return Stream.of(
                Arguments.of("Roger", "Person [id=53, name=Jaime Rogers, geschlecht=Male]" + System.lineSeparator() +
                        "Person [id=70, name=Roger Walker, geschlecht=Male]"),
                Arguments.of("Bobbie", "Person [id=175, name=Bobbie Moran, geschlecht=Female]"),
                Arguments.of("Brown", "Person [id=17, name=Darryl Brown, geschlecht=Male]" + System.lineSeparator() +
                        "Person [id=113, name=Sandra Brown, geschlecht=Female]"));
    }


    @ParameterizedTest
    @MethodSource("testProduktsuche")
    public void testProduktsuche(String produktToSearch, String expectedString) {
        logger.info("Produktsuche fuer " + produktToSearch);

        assertEquals(expectedString, dbSingleton.produktsuche(produktToSearch));
    }

    static Stream<Arguments> testProduktsuche() {
        return Stream.of(
                Arguments.of("iPad", "Produkt [id=203, name=iPad]" + System.lineSeparator() +
                        "Produkt [id=205, name=iPad Mini]"),
                Arguments.of("Tab", "Produkt [id=210, name=Samsung Galaxy Tab 3]"),
                Arguments.of("Samsung", "Produkt [id=208, name=Samsung ChromeBook]" + System.lineSeparator() +
                        "Produkt [id=209, name=Samsung Galaxy 5]" + System.lineSeparator() +
                        "Produkt [id=210, name=Samsung Galaxy Tab 3]"));
    }
}
