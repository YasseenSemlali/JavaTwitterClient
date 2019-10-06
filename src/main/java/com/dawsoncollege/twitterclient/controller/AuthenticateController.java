/**
 * Sample Skeleton for 'Authenticate.fxml' Controller Class
 */

package com.dawsoncollege.twitterclient.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthenticateController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="consumerKeyTxt"
    private TextField consumerKeyTxt; // Value injected by FXMLLoader

    @FXML // fx:id="consumerSecretKeyTxt"
    private TextField consumerSecretKeyTxt; // Value injected by FXMLLoader

    @FXML // fx:id="accessSecretKeyTxt"
    private TextField accessSecretKeyTxt; // Value injected by FXMLLoader

    @FXML // fx:id="accessKeyTxt"
    private TextField accessKeyTxt; // Value injected by FXMLLoader
  
    @FXML // fx:id="okBtn"
    private Button okBtn; // Value injected by FXMLLoader

    /** Create the twitter4j.properties file based on user input
     * @param event
     */
    @FXML
    
    void createProperties(ActionEvent event) {
        String consumerKey = consumerKeyTxt.getText();
        String consumerSecretKey = consumerSecretKeyTxt.getText();
        String accessSecretKey = accessSecretKeyTxt.getText();
        String accessKey = accessKeyTxt.getText();
        
        if(!(consumerKey == null || consumerKey.isBlank() ||
                consumerSecretKey == null || consumerSecretKey.isBlank() ||
                accessSecretKey == null || accessSecretKey.isBlank() ||
                accessKey == null || accessKey.isBlank() )) 
        {
            Properties prop = new Properties();
            prop.setProperty("oauth.consumerKey", consumerKey);
            prop.setProperty("oauth.consumerSecret", consumerSecretKey);
            prop.setProperty("oauth.accessToken", accessSecretKey);
            prop.setProperty("oauth.accessTokenSecret", accessKey);
            
            // Add properties to the prop object
            
            try (OutputStream propFileStream = new FileOutputStream("twitter4j.properties");){
               prop.store(propFileStream, "Twitter Properties");
               Stage stage = (Stage) okBtn.getScene().getWindow();
               stage.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AuthenticateController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
          assert consumerKeyTxt != null : "fx:id=\"consumerKeyTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert consumerSecretKeyTxt != null : "fx:id=\"consumerSecretKeyTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert accessSecretKeyTxt != null : "fx:id=\"accessSecretKeyTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert accessKeyTxt != null : "fx:id=\"accessKeyTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert okBtn != null : "fx:id=\"okBtn\" was not injected: check your FXML file 'Authenticate.fxml'.";

    }
}
