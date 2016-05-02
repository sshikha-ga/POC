package com.ga.domain.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {
	
	Connection con = null;
	Statement stmt = null;
	String userName = null;
	String driver = null;
	String db_url = null;
	String password = null;
	
	public Properties loadPropertiesFile() throws Exception {
	    Properties prop = new Properties();
        String propertyFile = "jdbc.properties";
        prop.load(getClass().getClassLoader().getResourceAsStream(propertyFile));
        return prop;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{

	    Properties prop;
        try {
            prop = loadPropertiesFile();
            driver = prop.getProperty("MYSQLJDBC.driver");
            db_url = prop.getProperty("MYSQLJDBC.url");
            userName = prop.getProperty("MYSQLJDBC.username");
            password = prop.getProperty("MYSQLJDBC.password");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
	    Class.forName(driver);
		
		con = DriverManager.getConnection(db_url,userName,password);
		
		return con;
	}
	
	
}
