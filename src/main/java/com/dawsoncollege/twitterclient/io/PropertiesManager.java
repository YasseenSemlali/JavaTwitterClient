/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.io;

import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.beans.AuthenticateBean;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1742811
 */
public class PropertiesManager {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(PropertiesManager.class);

    private final String propertiesDir;
    
    // TODO javadoc
    public PropertiesManager(String propertiesDir) {
        this.propertiesDir = propertiesDir;
    }
    
    /** Validates the twitter4j.properties file
     * @return Whether or not the twitter4j.properties file is there and valid
     */
    public boolean hasCredentials() {
        Properties prop = new Properties();
        try (InputStream propFileStream = new FileInputStream(this.propertiesDir);){
               prop.load(propFileStream);
               
               return(
                     prop.get("oauth.consumerKey") != null &&
                     prop.get("oauth.consumerSecret") != null &&
                     prop.get("oauth.accessToken") != null &&
                     prop.get("oauth.accessTokenSecret") != null
                       );
            } catch (FileNotFoundException ignored) {
                // Do nothing
            } catch (IOException ignored) {
                // Do nothing
            }
        return false;
    }
    
    
    
    /** Create the twitter4j.properties file based on user input
     * @param bean The AuthenticateBean that contains form data
     */
    public String createProperties(AuthenticateBean bean) {
        String errorMsg = "";
        
        String consumerKey = bean.getConsumerKey();
        String consumerSecretKey = bean.getConsumerSecretKey();
        String accessSecretKey = bean.getAccessKey();
        String accessKey = bean.getAccessSecretKey();
        
        if(!(consumerKey == null || consumerKey.isBlank() ||
                consumerSecretKey == null || consumerSecretKey.isBlank() ||
                accessSecretKey == null || accessSecretKey.isBlank() ||
                accessKey == null || accessKey.isBlank() )) 
        {
            Properties prop = new CleanProperties();
            prop.setProperty("oauth.consumerKey", consumerKey);
            prop.setProperty("oauth.consumerSecret", consumerSecretKey);
            prop.setProperty("oauth.accessToken", accessSecretKey);
            prop.setProperty("oauth.accessTokenSecret", accessKey);
            
            // Add properties to the prop object
            
            try (OutputStream propFileStream = new FileOutputStream(this.propertiesDir);){
               prop.store(propFileStream, "Twitter Properties");
            }  catch (IOException ex) {
                errorMsg = "Could not write twitter4j.properties file";
                LOG.warn(ex.getMessage());
            }
            
        } else {
            errorMsg = "Please fill out all fields";
        }
        
        return errorMsg;
    }
}
