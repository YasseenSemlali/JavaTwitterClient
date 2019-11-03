package com.dawsoncollege.twitterclient.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

/**
 *
 * @author Yasseen
 */
public class TweetSenderImpl implements TweetSender{
    private final static Logger LOG = LoggerFactory.getLogger(TweetSenderImpl.class);

    private final TwitterEngine engine;
    
    public TweetSenderImpl() {
        this.engine = new TwitterEngineImpl();
    }
    
    public void sendTweet(StatusUpdate tweet) throws TwitterException {
    	LOG.info("Sending tweet: " + tweet.getStatus());
    	
        this.engine.sendTweet(tweet);
    }
}
