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
 * @author Yasseen
 */
public class TwitterPropertiesManager {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TwitterPropertiesManager.class);
    private final static String propertiesFileName = "twitter4j.properties";
    
    private final String propertiesDir;
    
    public TwitterPropertiesManager(String propertiesDir) {
        if(propertiesDir == null || propertiesDir.isBlank()) {
            this.propertiesDir = "";
        } else {
            this.propertiesDir = propertiesDir + "/";
        }
    }
    
    /** Validates the twitter4j.properties file
     * @return Whether or not the twitter4j.properties file is there and valid
     */
    public boolean hasCredentials() {
        System.out.println(this.propertiesDir+"/" + propertiesFileName);
        Properties prop = new Properties();
        try (InputStream propFileStream = new FileInputStream(this.propertiesDir + propertiesFileName);){
               prop.load(propFileStream);
               
               return(
                     prop.get("oauth.consumerKey") != null &&
                     prop.get("oauth.consumerSecret") != null &&
                     prop.get("oauth.accessToken") != null &&
                     prop.get("oauth.accessTokenSecret") != null
                       );
            } catch ( IOException ignored) {
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
            // Add properties to the prop object
            // Not using prop.store because twitter4j doesn't support comments
            try (OutputStream propFileStream = new FileOutputStream(this.propertiesDir+"/" + propertiesFileName);){

            	propFileStream.write(("oauth.consumerKey="+ consumerKey + "\n").getBytes());
            	propFileStream.write(("oauth.consumerSecret="+ consumerSecretKey + "\n").getBytes());
            	propFileStream.write(("oauth.accessToken="+ accessSecretKey + "\n").getBytes());
            	propFileStream.write(("oauth.accessTokenSecret="+ accessKey + "\n").getBytes());
            	
            	
               //prop.store(propFileStream, "Twitter Properties");
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
