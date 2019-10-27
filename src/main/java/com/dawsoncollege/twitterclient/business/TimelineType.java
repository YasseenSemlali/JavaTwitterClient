
package com.dawsoncollege.twitterclient.business;

/** 
 * Types of timelines that can be retrieved
 * @author 1742811
 */
public enum TimelineType {
    /**
     * The user's feed that is displayed on the twitter home page
     */
    HOME, 
    /**
     * The user's posts that are displayed on their profile
     */
    USER, 
    /**
     * All posts mentioning the user
     */
    MENTIONS, 
    /**
     * All posts that retweet the user
     */
    RETWEETS_OF_ME
}
