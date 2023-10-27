package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class H2ConnectionFactory implements ConnectionFactory {
    private PropertyFileLoader propertyFileLoader = new PropertyFileLoader("h2database.properties");
    private Properties properties = propertyFileLoader.getProperties();

    @Override
    public Connection createConnection() {
        try {
            Class.forName(properties.getProperty("jdbc_driver"));
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("db_url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            );
            return connection;

        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static void main(String[] args) {
        try (Connection connection = (new H2ConnectionFactory()).createConnection()) {
            System.out.println("Is connected? " + (connection.isValid(10) ? "True" : "False"));

        } catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }

}


class PropertyFileLoader {
    private final Properties properties = new Properties();

    public PropertyFileLoader(String filename){
        loadPropertyFile(filename);
    }

    private void loadPropertyFile(String filename){
        // Load properties from file
        try (InputStream inputStream = PropertyFileLoader.class
                .getClassLoader()
                .getResourceAsStream(filename)
        ) {
            properties.load(inputStream);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    public Properties getProperties(){
        return properties;
    }
}

