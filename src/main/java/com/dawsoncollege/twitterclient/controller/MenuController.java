package com.dawsoncollege.twitterclient.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MenuController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(MenuController.class);
    private TabPane mainContent;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void onFeedClick(ActionEvent event) {
    	LOG.debug("EVENT: onFeedClick");
        mainContent.getSelectionModel().select(TwitterRootController.FEED_INDEX);
    }

    @FXML
    void onMessageClick(ActionEvent event) {
    	LOG.debug("EVENT: onMessageClick");
        mainContent.getSelectionModel().select(TwitterRootController.DM_INDEX);
    }

    @FXML
    void onSearchClick(ActionEvent event) {
    	LOG.debug("EVENT: onSearchClick");
        mainContent.getSelectionModel().select(TwitterRootController.SEARCH_INDEX);
    }

    @FXML
    void onProfileClick(ActionEvent event) {
    	LOG.debug("EVENT: onProfileClick");
        mainContent.getSelectionModel().select(TwitterRootController.PROFILE_INDEX);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    void setTabLayout(TabPane mainContent) {
        this.mainContent = mainContent;
    }
}
