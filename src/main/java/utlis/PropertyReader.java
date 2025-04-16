package utlis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String PropertyReader(String Path, String Key) throws IOException {
        String Value = null;
        try (InputStream input = new FileInputStream(Path)) {
            Properties prop = new Properties();
            prop.load(input);
            Value = prop.getProperty(Key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Value;

    }
}
