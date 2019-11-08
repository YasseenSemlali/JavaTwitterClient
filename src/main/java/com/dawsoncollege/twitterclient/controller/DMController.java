package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.business.twitterlogic.TwitterEngine;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import org.slf4j.LoggerFactory;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
import twitter4j.TwitterException;

public class DMController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(DMController.class);
    DirectMessageList dmList;
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
    }
}
