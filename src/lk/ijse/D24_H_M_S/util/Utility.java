package lk.ijse.D24_H_M_S.util;

import java.io.IOException;
import java.util.Properties;

public class Utility {
    public static Properties getProperties(){

        Properties properties = new Properties();

        try {

            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        return properties;

    }
}
