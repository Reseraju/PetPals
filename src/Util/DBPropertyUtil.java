package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBPropertyUtil {
	Connection connection = null;
    Properties properties = new Properties();
		
	public Connection getConnection() {
        

        try (FileInputStream fis = new FileInputStream("D:\\Eclipse\\Petpals\\src\\Util\\db.properties")) {
            // Load the properties from the file
            properties.load(fis);

            // Extract the database connection details
            String url = properties.getProperty("jdbc.url");
            String user = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            // Establish the database connection using the DriverManager
            connection = DriverManager.getConnection( url, user, password);

        } catch (IOException e) {
            System.err.println("Error reading the properties file: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error establishing the database connection: " + e.getMessage());
        }

        return connection;
    }
	
	public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	
}
