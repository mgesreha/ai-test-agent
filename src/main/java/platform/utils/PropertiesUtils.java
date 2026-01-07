package platform.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

     public static Properties getProperties(String fileName) {
        File propertyFile = new File("target/classes/" + fileName);
        Properties props = new Properties();
         try {
            props.load(new FileInputStream(propertyFile));
        } catch (IOException ex) {
            throw new RuntimeException("[AUT-ERROR] Properties were not loaded from file with name :: "
                    + fileName + "instead throw exception:", ex);}
        return props;
    }
}
