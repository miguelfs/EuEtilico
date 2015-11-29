package seawolf.com.euetilico.models;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Miguel on 02/10/2015.
 */
public final class Constants {

    public static final String[][] pares = {
            {"Cerveja Bohemia", "7.00"},
            {"Cerveja Antártica", "6.00"},
            {"Gourjão de Frango", "14.00"},
            {"Trio Mineiro", "20.00"},
            {"Itaipava", "7.10"},
            {"Rabo de Galo", "5.42"},
            {"Aipim frito", "16.00"},
            {"Brahma", "7.00"},
            {"Cachaça 51", "3.00"},
            {"Cachaça Boazinha", "4.00"},
            {"Cachaça Ypioca", "3.00"},
            {"Cachaça Salinas", "5.00"},
            {"Fogo Paulista", "4.00"},
            {"Red Label", "50.00"},
            {"Black Label", "70.00"}
    };

    public static String[] PRICE_CONSTANTS = new String[]{
            "7.00",
            "6.00",
            "14.00",
            "14.00",
            "5.50",
            "8.00",
            "14.00",
            "6.00",
            "2.00",
            "5.00",
            "6.00",
    };

    public static String[] DIVIDE_CONSTANTS = new String[]{
            "Uma pessoa",
            "Duas pessoas",
            "Três pessoas",
            "Quatro pessoas",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "Dividi com",
    };

    public static String[] UNITS_CONSTANTS = new String[]{
            "Uma pessoa",
            "Duas pessoas",
            "Três pessoas",
            "Quatro pessoas",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "Dividi com",
    };

    public static final HashMap<String, String> PRODUCT_HASHMAP_CONSTANTS;
    static {
        PRODUCT_HASHMAP_CONSTANTS = new HashMap<String, String>();
        for (String[] pair : pares) {
            PRODUCT_HASHMAP_CONSTANTS.put(pair[0], pair[1]);
        }
    }
    public static final String[] CONSUMABLE_CONSTANTS =  PRODUCT_HASHMAP_CONSTANTS.keySet().toArray(new String[0]);

}
