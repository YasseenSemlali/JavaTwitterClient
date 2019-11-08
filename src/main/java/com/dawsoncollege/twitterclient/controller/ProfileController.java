package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.business.timelines.TimelineType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.LoggerFactory;
import twitter4j.Query;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class ProfileController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(ProfileController.class);
    
    private static final int POSTS_INDEX = 0;
    private static final int MY_RETWEETS_INDEX = 1;
    private static final int RETWEETED_INDEX = 2;
    private static final int MENTIONS_INDEX = 3;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location; 
    
    @FXML
    private TabPane mainContent;

    @FXML
    private Tab postsTab;

    @FXML
    private Tab myRetweetsTab;

    @FXML
    private Tab retweetsOfMeTab;

    @FXML
    private Tab mentionsTab;

    @FXML
    void onMentionsClick(ActionEvent event) {
    	LOG.debug("EVENT: onMentionsClick");
        this.mainContent.getSelectionModel().select(MENTIONS_INDEX);
    }

    @FXML
    void onMyRetweetsClick(ActionEvent event) {
    	LOG.debug("EVENT: onMyRetweetsClick");
        this.mainContent.getSelectionModel().select(MY_RETWEETS_INDEX);
    }

    @FXML
    void onPostsClick(ActionEvent event) {
    	LOG.debug("EVENT: onPostsClick");
        this.mainContent.getSelectionModel().select(POSTS_INDEX);
    }

    @FXML
    void onRetweetedClick(ActionEvent event) {
    	LOG.debug("EVENT: onRetweetedClick");
        this.mainContent.getSelectionModel().select(RETWEETED_INDEX);
    }

    @FXML
    void initialize() {
        assert mainContent != null : "fx:id=\"mainContent\" was not injected: check your FXML file 'Profile.fxml'.";
        assert postsTab != null : "fx:id=\"postsTab\" was not injected: check your FXML file 'Profile.fxml'.";
        assert myRetweetsTab != null : "fx:id=\"myRetweetsTab\" was not injected: check your FXML file 'Profile.fxml'.";
        assert retweetsOfMeTab != null : "fx:id=\"retweetsOfMeTab\" was not injected: check your FXML file 'Profile.fxml'.";
        assert mentionsTab != null : "fx:id=\"mentionsTab\" was not injected: check your FXML file 'Profile.fxml'.";

        this.initTimeline(postsTab, TimelineType.USER);
        this.initTimeline(retweetsOfMeTab, TimelineType.RETWEETS_OF_ME);
        this.initTimeline(mentionsTab, TimelineType.MENTIONS);
        
        Twitter twitter = TwitterFactory.getSingleton();
        try{
            this.initRetweetTimeline(myRetweetsTab, "from:"+twitter.getScreenName() + " filter:retweets");
        } catch(TwitterException e) {
            LOG.error("Error retrieving screen name", e);
        }
    }
    
    private void initTimeline(Tab tab, TimelineType timelineType) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(resources);
            loader.setLocation(NewFXMain.class.getResource("/fxml/Timeline.fxml"));
            
            BorderPane content = (BorderPane) loader.load();
            content.setPrefHeight(this.mainContent.getPrefHeight());
            
            TimelineController controller = loader.getController();
            tab.setContent(content);
            
            controller.setTimelineType(timelineType);
            controller.updateTimeline();
            
        } catch (IOException ex) {
            LOG.error("initTimeline error in ProfileController", ex);
            Platform.exit();
        }
    }
    
     private void initRetweetTimeline(Tab tab, String searchTerm) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(resources);
            loader.setLocation(NewFXMain.class.getResource("/fxml/SearchTimeline.fxml"));
            
            Node content = (Node) loader.load();
            SearchTimelineController controller = loader.getController();
            tab.setContent(content);
            
            Query query = new Query(searchTerm);
            query.setResultType(Query.ResultType.recent);
            try {
            	 controller.search(query);
            } catch(TwitterException e) {
            	 LOG.error("Error displaying retweets in ProfileController", e);
            }
           
            
        } catch (IOException ex) {
            LOG.error("initRetweetTimeline error", ex);
            Platform.exit();
        }
    }
}
