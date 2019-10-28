package com.dawsoncollege.twitterclient.controller;


import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.business.TimelineType;
import com.dawsoncollege.twitterclient.controller.TimelineController;
import com.dawsoncollege.twitterclient.controller.TwitterRootController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.slf4j.LoggerFactory;

public class HomeTimelineController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(HomeTimelineController.class);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane content;

    @FXML
    void initialize() {
        assert content != null : "fx:id=\"content\" was not injected: check your FXML file 'HomeTimeline.fxml'.";
        
        this.initTimeline();
        this.initSendTweet();
    }
    
    private void initTimeline() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(resources);
            loader.setLocation(NewFXMain.class.getResource("/fxml/Timeline.fxml"));

            Node homeTimeline = loader.load();
            TimelineController controller = loader.getController();
            
            controller.setTimelineType(TimelineType.HOME);
            controller.updateTimeline();

            this.content.setCenter(homeTimeline);
        } catch (IOException ex) {
            LOG.error("initTimeline error", ex);
            Platform.exit();
        }
    }
    

    private void initSendTweet() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SendTweet.fxml"), resources);
            Node timeline = loader.load();
            SendTweetController controller = (SendTweetController) loader.getController();

            this.content.setBottom(timeline);
        } catch (IOException ex) {
            LOG.error("initSendTweet error", ex);
            Platform.exit();
        }
    }
}
