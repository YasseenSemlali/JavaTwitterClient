package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.business.SearchTimeline;
import com.dawsoncollege.twitterclient.business.SearchTimelineImpl;
import com.dawsoncollege.twitterclient.business.TimelineCell;
import com.dawsoncollege.twitterclient.business.TimelineImpl;
import com.dawsoncollege.twitterclient.business.TweetInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Query;
import twitter4j.TwitterException;

public class SearchTimelineController {

    private final static Logger LOG = LoggerFactory.getLogger(SearchTimelineController.class);
    private SearchTimeline timeline;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<TweetInfo> timelineView;

    @FXML
    void updateList(ActionEvent event) {
        if (this.timeline != null) {
            try {
                this.timeline.getNext();
            } catch (TwitterException e) {
                LOG.error("Error retrieving next search results", e);
            }
        }
    }

    @FXML
    void initialize() {
        assert timelineView != null : "fx:id=\"timelineView\" was not injected: check your FXML file 'SearchTimeline.fxml'.";

        this.timelineView.setItems(FXCollections.observableArrayList());
        this.timelineView.setCellFactory(p -> new TimelineCell());
    }

    public void search(String searchTerm) {
        if (this.timeline == null) {
            this.timeline = new SearchTimelineImpl(this.timelineView.getItems());
        }

        try {
            this.timeline.search(new Query(searchTerm));
        } catch (TwitterException e) {
            LOG.error("Error retrieving search results", e);
        }
    }
}
