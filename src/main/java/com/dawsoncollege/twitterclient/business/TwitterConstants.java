package com.dawsoncollege.twitterclient.business;

import twitter4j.Paging;

/**
 * Contains misc. values that are used in the application
 * @author Yasseen
 */
public class TwitterConstants {
    /**
     * Number of tweets to display per timeline update
     */
    public static final int TWEETS_PER_UPDATE = 20;
    /**
     * Number of tweets to display per search
     */
    public static final int TWEETS_PER_SEARCH = 20;
    
    
    public static final int DIRECT_MESSAGES_PER_UPDATE = 10;
    /**
     * The path to the application's resource bundle
     */
    public static final String RESOURCE_BUNDLE_DIR = "ResourceBundle";
    
    private TwitterConstants() {
        
    }
    
}
