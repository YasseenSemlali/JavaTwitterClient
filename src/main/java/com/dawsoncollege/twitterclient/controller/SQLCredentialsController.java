/**
 * Sample Skeleton for 'Authenticate.fxml' Controller Class
 */

package com.dawsoncollege.twitterclient.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;

import com.dawsoncollege.twitterclient.beans.AuthenticateBean;
import com.dawsoncollege.twitterclient.beans.SQLCredentialsBean;
import com.dawsoncollege.twitterclient.io.SQLPropertiesManager;
import com.dawsoncollege.twitterclient.io.TwitterPropertiesManager;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SQLCredentialsController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(SQLCredentialsController.class);
    private final SQLCredentialsBean credentialsBean;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="urlTxt"
    private TextField urlTxt; // Value injected by FXMLLoader

    @FXML // fx:id="usernameTxt"
    private TextField usernameTxt; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTxt"
    private TextField passwordTxt; // Value injected by FXMLLoader
  
    @FXML // fx:id="errorMsgTxt"
    private Label errorMsgTxt; // Value injected by FXMLLoader
    
    @FXML // fx:id="okBtn"
    private Button okBtn; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert urlTxt != null : "fx:id=\"urlTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert usernameTxt != null : "fx:id=\"usernameTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert errorMsgTxt != null : "fx:id=\"errorMsgTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert okBtn != null : "fx:id=\"okBtn\" was not injected: check your FXML file 'Authenticate.fxml'.";

        Bindings.bindBidirectional(urlTxt.textProperty(), this.credentialsBean.urlProperty());
        Bindings.bindBidirectional(usernameTxt.textProperty(), this.credentialsBean.usernameProperty());
        Bindings.bindBidirectional(passwordTxt.textProperty(), this.credentialsBean.passwordProperty());
    }
    
    public SQLCredentialsController() {
        this.credentialsBean = new SQLCredentialsBean();
    }

    /** Create the twitter4j.properties file based on user input
     * @param event
     */
    @FXML
    void createProperties(ActionEvent event) {
    	LOG.debug("EVENT: createProperties");
        SQLPropertiesManager propManager = new SQLPropertiesManager();
        
        String msg = propManager.createProperties(this.credentialsBean);
        if(msg.equals("")) {
           Stage stage = (Stage) okBtn.getScene().getWindow();
           stage.close();
         } else {
           this.errorMsgTxt.setText(msg);
         }
    }
}
