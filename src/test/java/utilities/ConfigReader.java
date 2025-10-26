package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties prop = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("❌ Unable to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
