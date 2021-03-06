
package com.dawsoncollege.twitterclient.business.timelines;

/** 
 * Types of timelines that can be retrieved
 * @author Yasseen
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
    RETWEETS_OF_ME,
    /**
     * Posts that are saved to a database
     */
    DATABASE
}
