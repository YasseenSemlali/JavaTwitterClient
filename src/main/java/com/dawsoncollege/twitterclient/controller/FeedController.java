package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.business.Timeline;
import com.dawsoncollege.twitterclient.business.TimelineCell;
import com.dawsoncollege.twitterclient.business.TimelineImpl;
import com.dawsoncollege.twitterclient.business.TimelineType;
import com.dawsoncollege.twitterclient.business.TweetInfo;
import com.dawsoncollege.twitterclient.business.TwitterEngine;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

public class FeedController {

    private final static Logger LOG = LoggerFactory.getLogger(FeedController.class);
    private Timeline timeline;
    private TimelineType timelineType = TimelineType.HOME;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<TweetInfo> timelineView;

    @FXML
    private BorderPane borderPane;

    @FXML
    void initialize() {
        assert timelineView != null : "fx:id=\"timelineView\" was not injected: check your FXML file 'Feed.fxml'.";
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Feed.fxml'.";

        this.timelineView.setItems(FXCollections.observableArrayList());
        this.timelineView.setCellFactory(p -> new TimelineCell());
        this.initSendTweet();
        
    }

    @FXML
    void updateList(ActionEvent event) {
        this.updateTimeline();
    } 
    
    @FXML
    void refresh(ActionEvent event) {
        this.timeline.reset();
        this.updateTimeline();
    }
    
    public void setTimelineType(TimelineType timelineType) {
        this.timelineType = timelineType;
    }

    private void initSendTweet() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SendTweet.fxml"), resources);
            Node content = loader.load();
            SendTweetController controller = (SendTweetController) loader.getController();

            this.borderPane.setBottom(content);
        } catch (IOException ex) {
            LOG.error("initSendTweet error", ex);
            Platform.exit();
        }
    }

    public void updateTimeline() {
        if (this.timeline == null) {
            this.timeline = new TimelineImpl(this.timelineView.getItems(), this.timelineType);
        }

        try {
            this.timeline.updateTimeline();
        } catch (TwitterException e) {
            LOG.error("Error retrieving timeline", e);
        }
    }
}
