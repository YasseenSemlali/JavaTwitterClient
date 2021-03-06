/**
 * Sample Skeleton for 'Authenticate.fxml' Controller Class
 */

package com.dawsoncollege.twitterclient.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;

import com.dawsoncollege.twitterclient.beans.AuthenticateBean;
import com.dawsoncollege.twitterclient.io.TwitterPropertiesManager;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthenticateController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(AuthenticateController.class);
    private final AuthenticateBean authenticateBean;

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
  
    @FXML // fx:id="errorMsgTxt"
    private Label errorMsgTxt; // Value injected by FXMLLoader
    
    @FXML // fx:id="okBtn"
    private Button okBtn; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert consumerKeyTxt != null : "fx:id=\"consumerKeyTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert consumerSecretKeyTxt != null : "fx:id=\"consumerSecretKeyTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert accessKeyTxt != null : "fx:id=\"accessKeyTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert accessSecretKeyTxt != null : "fx:id=\"accessSecretKeyTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert errorMsgTxt != null : "fx:id=\"errorMsgTxt\" was not injected: check your FXML file 'Authenticate.fxml'.";
        assert okBtn != null : "fx:id=\"okBtn\" was not injected: check your FXML file 'Authenticate.fxml'.";

        Bindings.bindBidirectional(consumerKeyTxt.textProperty(), this.authenticateBean.consumerKeyProperty());
        Bindings.bindBidirectional(consumerSecretKeyTxt.textProperty(), this.authenticateBean.consumerSecretKeyProperty());
        Bindings.bindBidirectional(accessKeyTxt.textProperty(), this.authenticateBean.accessKeyProperty());
        Bindings.bindBidirectional(accessSecretKeyTxt.textProperty(), this.authenticateBean.accessSecretKeyProperty());
    }
    
    public AuthenticateController() {
        this.authenticateBean = new AuthenticateBean();
    }

    /** Create the twitter4j.properties file based on user input
     * @param event
     */
    @FXML
    void createProperties(ActionEvent event) {
    	LOG.debug("EVENT: createProperties");
        TwitterPropertiesManager propManager = new TwitterPropertiesManager();
        
        String msg = propManager.createProperties(this.authenticateBean);
        if(msg.equals("")) {
           Stage stage = (Stage) okBtn.getScene().getWindow();
           stage.close();
         } else {
           this.errorMsgTxt.setText(msg);
         }
    }
}
