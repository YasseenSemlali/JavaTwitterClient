package com.dawsoncollege.twitterclient.business;

import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

/**
 *
 * @author 1742811
 */
public class TweetSenderImpl implements TweetSender{

    private final TwitterEngine engine;
    
    public TweetSenderImpl() {
        this.engine = new TwitterEngine();
    }
    
    public void sendTweet(StatusUpdate tweet) throws TwitterException {
        this.engine.sendTweet(tweet);
    }
}
