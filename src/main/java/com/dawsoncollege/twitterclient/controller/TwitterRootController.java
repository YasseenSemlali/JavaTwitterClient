package com.dawsoncollege.twitterclient.controller;


import com.dawsoncollege.twitterclient.NewFXMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.slf4j.LoggerFactory;

public class TwitterRootController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TwitterRootController.class);
    private MenuController menuController;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mainContent"
    private TabPane mainContent; // Value injected by FXMLLoader

    @FXML // fx:id="menuPane"
    private AnchorPane menuPane; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {        
        assert mainContent != null : "fx:id=\"mainContent\" was not injected: check your FXML file 'TwitterRoot.fxml'.";
        assert menuPane != null : "fx:id=\"menuPane\" was not injected: check your FXML file 'TwitterRoot.fxml'.";

        this.initMenu();
    }
    
    private void initMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(resources);

            loader.setLocation(NewFXMain.class
                    .getResource("/fxml/Menu.fxml"));
            VBox menu = (VBox) loader.load();

            // Give the controller the data object.
            this.menuController = loader.getController();
            this.menuController.setTabLayout(this.mainContent);

            this.menuPane.getChildren().add(menu);
        } catch (IOException ex) {
            LOG.error("initMenu error", ex);
            Platform.exit();
        }
    }
}



