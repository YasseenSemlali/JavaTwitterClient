
package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.beans.SendTweetBean;
import com.dawsoncollege.twitterclient.business.TweetSender;
import com.dawsoncollege.twitterclient.business.TweetSenderImpl;
import com.dawsoncollege.twitterclient.business.TwitterEngine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

public class SendTweetController {
    private final static Logger LOG = LoggerFactory.getLogger(SendTweetController.class);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea contents;

    @FXML
    private Text errMsg;
    private TweetSender tweetSender;
    private final SendTweetBean sendTweetBean;

    @FXML
    void sendTweet(ActionEvent event) {
        this.sendTweetBean.setTweet("");
        try{
            this.tweetSender.sendTweet( this.sendTweetBean.getTweet());
        } catch(TwitterException e) {
            LOG.debug("Error sending tweet", e);
            this.errMsg.setText(this.resources.getString("err_sending_tweet"));
        }
    }

    public SendTweetController() {
        this.sendTweetBean = new SendTweetBean(); 
    }
    
    @FXML
    void initialize() {
        assert contents != null : "fx:id=\"contents\" was not injected: check your FXML file 'SendTweet.fxml'.";
        assert errMsg != null : "fx:id=\"errMsg\" was not injected: check your FXML file 'SendTweet.fxml'.";

        this.tweetSender = new TweetSenderImpl();
        
        Bindings.bindBidirectional(this.sendTweetBean.tweetProperty(), this.contents.textProperty());
    }
}
