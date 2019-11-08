
package com.dawsoncollege.twitterclient.controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dawsoncollege.twitterclient.beans.SendTweetBean;
import com.dawsoncollege.twitterclient.business.twitterlogic.TweetSender;
import com.dawsoncollege.twitterclient.business.twitterlogic.TweetSenderImpl;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
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
    	LOG.debug("EVENT: sendTweet");
    	
        this.errMsg.setText("");
        try{
            String appendMsg = this.retweetOf.isBlank() ? "" : (" " + this.retweetOf);
            StatusUpdate  tweet = new StatusUpdate(this.sendTweetBean.getTweet() + appendMsg);
            
            
            tweet.setInReplyToStatusId(inReplyTo);
            
            
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
    
    /** Sets the tweet that the current tweet will be a reply to. Default value is -1 for a standalone tweet.
     * @param id
     */
    public void setInReplyTo(long id) {
        this.inReplyTo = id;
    }
    
    /** The twitter URL to append to the end of the message. This is not validated, and if it is invalid the tweet may exceed
     * the 280 character limit
     * @param tweetUrl Cannot be null
     */
    public void setRetweetOf(String tweetUrl) {
        Objects.requireNonNull(tweetUrl, "Tweet URL cannot be null");
        this.retweetOf = tweetUrl;
    }
    
    /** If set to true, the stage will be closed once the tweet is sent
     * @param isAlert
     */
    public void setIsAlert(boolean isAlert) {
        this.isAlert = isAlert;
    }
}
