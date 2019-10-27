package com.dawsoncollege.twitterclient.business;

import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

/**
 * Handles the creation of tweets
 * @author 1742811
 */
public interface TweetSender {
    
     public void sendTweet(StatusUpdate tweet) throws TwitterException;
    
}
