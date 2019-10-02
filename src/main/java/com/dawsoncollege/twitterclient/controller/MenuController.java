/**
 * Sample Skeleton for 'Menu.fxml' Controller Class
 */

package com.dawsoncollege.twitterclient.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;

public class MenuController {
    private TabPane mainContent;

      @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void onFeedClick(ActionEvent event) {
        mainContent.getSelectionModel().select(0);
    }

    @FXML
    void onMessageClick(ActionEvent event) {
        mainContent.getSelectionModel().select(1);
    }

    @FXML
    void onSearchClick(ActionEvent event) {
        mainContent.getSelectionModel().select(2);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    void setTabLayout(TabPane mainContent) {
        this.mainContent = mainContent;
    }
}
