package com.dawsoncollege.twitterclient.io;

import com.dawsoncollege.twitterclient.beans.SQLCredentialsBean;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        if (propertiesDir == null || propertiesDir.isBlank()) {
            this.propertiesDir = "";
        } else {
            this.propertiesDir = propertiesDir + "/";
        }
    }

    public boolean hasCredentials() {
        Properties prop = new Properties();
        try ( InputStream propFileStream = new FileInputStream(this.propertiesDir + propertiesFileName);) {
            prop.load(propFileStream);

            return (prop.getProperty("url") != null && !prop.getProperty("url").isBlank()
                    && prop.getProperty("username") != null && !prop.getProperty("username").isBlank()
                    && prop.getProperty("password") != null && !prop.getProperty("password").isBlank());
        } catch (IOException ignored) {
            // Do nothing
        }
        return false;
    }

    public String getConnectionString() throws IOException {
        try ( InputStream propFileStream = new FileInputStream(this.propertiesDir + propertiesFileName);) {
            Properties prop = new Properties();
            prop.load(propFileStream);

            if(prop.get("url") != null  && !prop.getProperty("url").isBlank()) {
                return prop.getProperty("url");
            } else {
                return "";
            }
        }
    }

    public String getUsername() throws IOException {
        try ( InputStream propFileStream = new FileInputStream(this.propertiesDir + propertiesFileName);) {
            Properties prop = new Properties();
            prop.load(propFileStream);

            if(prop.get("username") != null  && !prop.getProperty("username").isBlank()) {
                return prop.getProperty("username");
            } else {
                return "";
            }
        }
    }

    public String getPassword() throws IOException {
        try ( InputStream propFileStream = new FileInputStream(this.propertiesDir + propertiesFileName);) {
            Properties prop = new Properties();
            prop.load(propFileStream);

            if(prop.get("password") != null  && !prop.getProperty("password").isBlank()) {
                return prop.getProperty("password");
            } else {
                return "";
            }
        }
    }

    public String createProperties(SQLCredentialsBean bean) {
        String errorMsg = "";
        
        String url = bean.getUrl();
        String username = bean.getUsername();
        String password = bean.getPassword();
        
        if(!(url == null || url.isBlank() ||
                username == null || username.isBlank() ||
                password == null || password.isBlank() )) 
        {   
            try (OutputStream propFileStream = new FileOutputStream(this.propertiesDir + propertiesFileName);){
                Properties props = new Properties();
                props.setProperty("url", url);
                props.setProperty("username", username);
                props.setProperty("password", password);
                
            	props.store(propFileStream, "MySql connection properties");
            }  catch (IOException ex) {
                errorMsg = "Could not write sql.properties file";
                LOG.error(ex.getMessage(), ex);
            }
            
        } else {
            errorMsg = "Please fill out all fields";
        }
        
        return errorMsg;
    }
}
