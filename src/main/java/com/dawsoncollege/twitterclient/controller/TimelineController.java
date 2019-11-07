package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.business.DatabaseTimeline;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dawsoncollege.twitterclient.business.Timeline;
import com.dawsoncollege.twitterclient.business.TimelineCell;
import com.dawsoncollege.twitterclient.business.TimelineImpl;
import com.dawsoncollege.twitterclient.business.TimelineType;
import com.dawsoncollege.twitterclient.business.TweetInfo;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import twitter4j.TwitterException;

public class TimelineController {

    private final static Logger LOG = LoggerFactory.getLogger(TimelineController.class);
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
        assert timelineView != null : "fx:id=\"timelineView\" was not injected: check your FXML file 'Timeline.fxml'.";
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Timeline.fxml'.";

        this.timelineView.setItems(FXCollections.observableArrayList());
        this.timelineView.setCellFactory(p -> new TimelineCell());
    }

    @FXML
    void updateList(ActionEvent event) {
    	LOG.debug("EVENT: updateList");
        this.updateTimeline();
    } 
    
    @FXML
    void refresh(ActionEvent event) {
    	LOG.debug("EVENT: refresh");
        this.timeline.reset();
        this.updateTimeline();
    }
    
    public void setTimelineType(TimelineType timelineType) {
        this.timelineType = timelineType;
    }

    public void updateTimeline() {
        if (this.timeline == null) {
            if(timelineType == TimelineType.DATABASE) {
                 this.timeline = new DatabaseTimeline(this.timelineView.getItems());
            } else {
                this.timeline = new TimelineImpl(this.timelineView.getItems(), this.timelineType);
            }
        }

        try {
            this.timeline.updateTimeline();
        } catch (TwitterException e) {
            LOG.error("Error retrieving " + this.timelineType  + " timeline", e);
        }
    }
}
