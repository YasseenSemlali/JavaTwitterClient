package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.business.TimelineType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.slf4j.LoggerFactory;

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
        this.mainContent.getSelectionModel().select(MENTIONS_INDEX);
    }

    @FXML
    void onMyRetweetsClick(ActionEvent event) {
        this.mainContent.getSelectionModel().select(MY_RETWEETS_INDEX);
    }

    @FXML
    void onPostsClick(ActionEvent event) {
        this.mainContent.getSelectionModel().select(POSTS_INDEX);
    }

    @FXML
    void onRetweetedClick(ActionEvent event) {
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
    }
    
    private void initTimeline(Tab tab, TimelineType timelineType) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(resources);
            loader.setLocation(NewFXMain.class.getResource("/fxml/Feed.fxml"));
            
            Node content = (Node) loader.load();
            FeedController controller = loader.getController();
            controller.setTimelineType(timelineType);

            tab.setContent(content);
        } catch (IOException ex) {
            LOG.error("initTimeline error", ex);
            Platform.exit();
        }
    }
}
