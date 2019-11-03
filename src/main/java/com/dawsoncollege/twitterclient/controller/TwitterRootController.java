package com.dawsoncollege.twitterclient.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;

import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.business.TimelineType;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class TwitterRootController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TwitterRootController.class);
    private MenuController menuController;

    public static int FEED_INDEX = 0;
    public static int SEARCH_INDEX = 1;
    public static int DM_INDEX = 2;
    public static int PROFILE_INDEX = 3;
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mainContent"
    private TabPane mainContent; // Value injected by FXMLLoader

    @FXML // fx:id="feedTab"
    private Tab feedTab; // Value injected by FXMLLoader

    @FXML // fx:id="searchTab"
    private Tab searchTab; // Value injected by FXMLLoader

    @FXML // fx:id="dmTab"
    private Tab dmTab; // Value injected by FXMLLoader

    @FXML // fx:id="profileTab"
    private Tab profileTab; // Value injected by FXMLLoader

    @FXML // fx:id="menuPane"
    private AnchorPane menuPane; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mainContent != null : "fx:id=\"mainContent\" was not injected: check your FXML file 'TwitterRoot.fxml'.";
        assert feedTab != null : "fx:id=\"feedTab\" was not injected: check your FXML file 'TwitterRoot.fxml'.";
        assert searchTab != null : "fx:id=\"searchTab\" was not injected: check your FXML file 'TwitterRoot.fxml'.";
        assert dmTab != null : "fx:id=\"dmTab\" was not injected: check your FXML file 'TwitterRoot.fxml'.";
        assert profileTab != null : "fx:id=\"profileTab\" was not injected: check your FXML file 'TwitterRoot.fxml'.";
        assert menuPane != null : "fx:id=\"menuPane\" was not injected: check your FXML file 'TwitterRoot.fxml'.";

        this.initMenu();
        
        this.initTab(this.feedTab, "/fxml/HomeTimeline.fxml");
        
        //this.initTab(this.searchTab, "/fxml/Search.fxml");
        
        //this.initTab(this.dmTab, "/fxml/DM.fxml");
        
        //this.initTab(this.profileTab, "/fxml/Profile.fxml");
    }
    
    /** Initializes the sidebar
     * 
     */
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
    
    /** Initialized the specified tab with the specified fxml
     * @param tab The tab to be populated
     * @param path The path to the fxml
     */
    private void initTab(Tab tab, String path) {
        LOG.debug("initTab: " + path);
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(resources);
            loader.setLocation(getClass().getResource(path));
            
            Node content = (Node) loader.load();

            tab.setContent(content);
        } catch (IOException ex) {
            LOG.error("initTab error. Path: " + path, ex);
            Platform.exit();
        }
    }
}



