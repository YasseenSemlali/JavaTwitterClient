package com.dawsoncollege.twitterclient.business.twitterlogic;

import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

/**
 * Handles the creation of tweets
 * @author Yasseen
 */
public interface TweetSender {
    
     public void sendTweet(StatusUpdate tweet) throws TwitterException;
    
}
