package com.dawsoncollege.twitterclient.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Yasseen
 */
public class SQLPropertiesManager {    
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(SQLPropertiesManager.class);
    private final static String propertiesFileName = "sql.properties";

    private final String propertiesDir;
    
    public SQLPropertiesManager() {
        this("");
    }
    
    public SQLPropertiesManager(String propertiesDir) {
        if(propertiesDir == null || propertiesDir.isBlank()) {
            this.propertiesDir = "";
        } else {
            this.propertiesDir = propertiesDir + "/";
        }
    }
    
    public boolean hasCredentials() {
        Properties prop = new Properties();
        try (InputStream propFileStream = new FileInputStream(this.propertiesDir + propertiesFileName);){
               prop.load(propFileStream);
               
               return(
                     prop.get("url") != null &&
                     prop.get("username") != null &&
                     prop.get("password") != null
                       );
            } catch ( IOException ignored) {
                // Do nothing
            } 
        return false;
    }
    
    public String getConnectionString() {
        return "jdbc:mysql://localhost:3306/TWEETS";
    }
    
    public String getUsername() {
        return "yasseen";
    }
    
    public String getPassword() {
        return "password";
    }
}
