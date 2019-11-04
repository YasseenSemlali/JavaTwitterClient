package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import com.dawsoncollege.twitterclient.business.TwitterEngine;
import com.dawsoncollege.twitterclient.business.TwitterEngineImpl;
import com.dawsoncollege.twitterclient.sql.TweetDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

public class TweetController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TweetController.class);
    private TweetInfo info;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text handle;

    @FXML
    private Text date;

    @FXML
    private Text tweetContents;

     @FXML
    private Text replies;

    @FXML
    private Text retweets;

    @FXML
    private MenuButton retweetBtn;

    @FXML
    private MenuItem retweetNoReplyBtn;
    
    @FXML
    private Text likes;

    @FXML
    private Button likeBtn;
    
    @FXML
    private MenuItem followBtn;

    @FXML
    private MenuItem sendDMBtn;

    @FXML
    private MenuItem saveTweetBtn;

    @FXML
    void follow(ActionEvent event) {   
        LOG.info("EVENT: (un)follow " + this.info.getHandle());
        TwitterEngine engine = new TwitterEngineImpl();
        
        try {
            if(this.info.isFollowingUser()) {
                engine.unfollowUser(info.getHandle());
            } else {
                engine.followUser(info.getHandle());
            }
        } catch (TwitterException ex) {
            LOG.error("Error following user", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resources.getString("err_following_user"));
            
            alert.showAndWait();
        }
        
        this.info.update();
        this.updateContents();
    }

    @FXML
    void sendDM(ActionEvent event) {   
    
    }

    @FXML
    void saveTweet(ActionEvent event) {     
        LOG.info("EVENT: saveTweet " + this.info.getStatusId());  
        
        TweetDAOImpl tweetDAO = new TweetDAOImpl();
        try{
            if(tweetDAO.isSaved(info)) {
                tweetDAO.unsaveTweet(info);
            } else {
                tweetDAO.saveTweet(info);
            }
        } catch (SQLException e) {
            LOG.error("Error saving tweet", e);
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resources.getString("err_saving_tweet"));
            
            alert.showAndWait();
        }
        
        this.updateContents();
    }
    
    @FXML
    void like(ActionEvent event) {     
        LOG.info("EVENT: like " + this.info.getStatusUrl());
        
        try {
            TwitterEngine engine = new TwitterEngineImpl();
            
            if(this.info.isLikedByUser()) {
                engine.unlikeTweet(this.info.getStatusId());
            } else {
                engine.likeTweet(this.info.getStatusId());
            }
                
        } catch (TwitterException ex) {
            LOG.error("Error liking tweet", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resources.getString("err_liking_tweet"));
            
            alert.showAndWait();
        }
        
        this.info.update();
        this.updateContents();
    }

    @FXML
    void retweet(ActionEvent event) {        
        LOG.info("EVENT: retweet " + this.info.getStatusUrl());
        try {
            TwitterEngine engine = new TwitterEngineImpl();
            if(this.info.isRetweetedByUser()) {
                engine.unretweetTweet(this.info.getStatusId());
            } else {
                engine.retweetTweet(this.info.getStatusId());
            }
        } catch (TwitterException ex) {
            LOG.error("Error retweeting tweet", ex);
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resources.getString("err_retweeting_tweet"));
            
            alert.showAndWait();
        }
        
        this.info.update();
        this.updateContents();
    }

    @FXML
    void retweetWithReplies(ActionEvent event) {
        LOG.info("EVENT: retweetWithReplies " + this.info.getStatusUrl());
        if(this.info == null) {
            throw new IllegalStateException("Tweet contents must be initialized");
        }
        
        this.showSendTweet(-1, this.info.getStatusUrl());
    }

    @FXML
    void reply(ActionEvent event) {
        LOG.info("EVENT: reply " + this.info.getStatusUrl());
        if(this.info == null) {
            throw new IllegalStateException("Tweet contents must be initialized");
        }
        
        this.showSendTweet(this.info.getStatusId(), "");
        
        this.info.update();
        this.updateContents();
    }
    
    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert handle != null : "fx:id=\"handle\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert tweetContents != null : "fx:id=\"tweetContents\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert replies != null : "fx:id=\"replies\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert retweets != null : "fx:id=\"retweets\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert likes != null : "fx:id=\"likes\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert likeBtn != null : "fx:id=\"likeBtn\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert retweetBtn != null : "fx:id=\"retweetBtn\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert retweetNoReplyBtn != null : "fx:id=\"retweetNoReplyBtn\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert followBtn != null : "fx:id=\"followBtn\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert sendDMBtn != null : "fx:id=\"sendDMBtn\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert saveTweetBtn != null : "fx:id=\"saveTweetBtn\" was not injected: check your FXML file 'Tweet.fxml'.";
    }
    
    /** Shows a dialog that allows the user to send a tweet
     * @param inReplyTo Tweet that the comment is a reply to. Set to -1 for none.
     * @param retweetOf Tweet that this comment is retweeting. Set to an empty string for none.
     */
    private void showSendTweet(long inReplyTo, String retweetOf) {
    	Objects.requireNonNull(retweetOf, "retweetOf cannot be null");
    	
        DialogPane content = new DialogPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SendTweet.fxml"), resources);
            Node timeline = loader.load();
            
            SendTweetController controller = (SendTweetController) loader.getController();
            controller.setIsAlert(true);
            controller.setInReplyTo(inReplyTo);
            controller.setRetweetOf(retweetOf);
            
            content.setContent(timeline);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            Scene scene = new Scene(content);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            LOG.error("showSendTweet error", ex);
            Platform.exit();
        }
    }
    
    /** Initializes the visual info of the tweet
     * @param twitterInfo
     */
    public void setContents(TweetInfo twitterInfo) {
        this.info = twitterInfo;
        
        this.updateContents();
    }
    
    private void updateContents() {
        this.name.setText(info.getName());
        this.handle.setText(info.getHandle());
        this.date.setText(info.getDateString());
        this.tweetContents.setText(info.getText());
        this.image.setImage(new Image(info.getProfileImageURL()));
        this.replies.setText(info.getNumReplies()+"");
        this.retweets.setText(info.getNumRetweets()+"");
        this.likes.setText(this.info.getNumLikes()+"");
        
        if(this.info.isLikedByUser()) {
            this.likeBtn.setStyle("-fx-background-image: url(\"Images/like_full.png\");");
        } else {
            this.likeBtn.setStyle("-fx-background-image: url(\"Images/like.png\");");
        }
        
        if(this.info.isRetweetedByUser()) {
            this.retweetBtn.setStyle("-fx-background-image: url(\"Images/retweet_full.png\");");
            this.retweetNoReplyBtn.setText(resources.getString("unretweet"));
        } else {
            this.retweetBtn.setStyle("-fx-background-image: url(\"Images/retweet.png\");");
            this.retweetNoReplyBtn.setText(resources.getString("retweet"));
        }
        
        if(this.info.isFollowingUser()) {
            this.followBtn.setText(resources.getString("unfollow"));
        } else {
            this.followBtn.setText(resources.getString("follow"));
        }
        
        TweetDAOImpl tweetDAO = new TweetDAOImpl();
        
        if(tweetDAO.isSaved(info)) {
            this.saveTweetBtn.setText(resources.getString("unsave"));
        } else {
            this.saveTweetBtn.setText(resources.getString("save"));
        }
    }
}
