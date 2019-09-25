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

    @FXML // fx:id="homeBtn"
    private Button homeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="mentionsBtn"
    private Button mentionsBtn; // Value injected by FXMLLoader

    @FXML
    void onHomeClick(ActionEvent event) {
        mainContent.getSelectionModel().select(0);
    }

    @FXML
    void onMentionsClick(ActionEvent event) {
        mainContent.getSelectionModel().select(1);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert homeBtn != null : "fx:id=\"homeBtn\" was not injected: check your FXML file 'Menu.fxml'.";
        assert mentionsBtn != null : "fx:id=\"mentionsBtn\" was not injected: check your FXML file 'Menu.fxml'.";

    }

    void setTabLayout(TabPane mainContent) {
        this.mainContent = mainContent;
    }
}
