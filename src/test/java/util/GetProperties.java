package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {

    public void leerPropiedades() throws IOException {

        Properties properties = new Properties();
            String namePropertiesFiles="dev.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(namePropertiesFiles);
        if(inputStream != null)
           properties.load(inputStream);
        ConfigENV.host=properties.getProperty("host");
        ConfigENV.user=properties.getProperty("user");
        ConfigENV.password=properties.getProperty("password");

        System.out.println("*******************");
        System.out.println("ConfigENV.host"+ConfigENV.host);
        System.out.println("ConfigENV.user"+ConfigENV.user);
        System.out.println("ConfigENV.password"+ConfigENV.password);
        System.out.println("*******************");

        inputStream.close();
    }

}
