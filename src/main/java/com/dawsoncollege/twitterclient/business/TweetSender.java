package com.dawsoncollege.twitterclient.business;

import twitter4j.TwitterException;

/**
 *
 * @author 1742811
 */
public interface TweetSender {
    
     public void sendTweet(String tweet) throws TwitterException;
    
}
