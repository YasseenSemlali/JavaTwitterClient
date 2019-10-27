
package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.beans.SendTweetBean;
import com.dawsoncollege.twitterclient.business.TweetSender;
import com.dawsoncollege.twitterclient.business.TweetSenderImpl;
import com.dawsoncollege.twitterclient.business.TwitterEngine;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

public class SendTweetController {
    private final static Logger LOG = LoggerFactory.getLogger(SendTweetController.class);
    
    private TweetSender tweetSender;
    private final SendTweetBean sendTweetBean;
    
    private boolean isAlert = false;
    private long inReplyTo = -1;
    private String retweetOf = "";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea contents;

    @FXML
    private Text errMsg;

    @FXML
    void sendTweet(ActionEvent event) {
        this.errMsg.setText("");
        try{
            StatusUpdate  tweet = new StatusUpdate(this.sendTweetBean.getTweet() + (this.retweetOf.isBlank() ? "" : (" " + this.retweetOf)));
            
            if(this.inReplyTo != -1) {
                tweet.setInReplyToStatusId(inReplyTo);
            }
            
            this.tweetSender.sendTweet(tweet);
            this.sendTweetBean.setTweet("");
            
            if(this.isAlert) {
                Node source = (Node) event.getSource();
                Stage  stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        } catch(TwitterException e) {
            LOG.debug("Error sending tweet", e);
            this.errMsg.setText(this.resources.getString("err_sending_tweet"));
        }
    }
    
    @FXML
    void initialize() {
        assert contents != null : "fx:id=\"contents\" was not injected: check your FXML file 'SendTweet.fxml'.";
        assert errMsg != null : "fx:id=\"errMsg\" was not injected: check your FXML file 'SendTweet.fxml'.";

        this.tweetSender = new TweetSenderImpl();
        
        Bindings.bindBidirectional(this.sendTweetBean.tweetProperty(), this.contents.textProperty());
    }

    public SendTweetController() {
        this.sendTweetBean = new SendTweetBean(); 
    }
    
    public void setInReplyTo(long id) {
        this.inReplyTo = id;
    }
    
    public void setRetweetOf(String tweetUrl) {
        Objects.requireNonNull(tweetUrl, "Tweet URL cannot be null");
        this.retweetOf = tweetUrl;
    }
    
    public void setIsAlert(boolean isAlert) {
        this.isAlert = isAlert;
    }
}
