package com.dawsoncollege.twitterclient.business;

import java.util.Date;

/**
 * Wrapper class for the {@link Status} object
 * @author Yasseen
 */
public interface TweetInfo {
    
    public long getStatusId();
    
    public String getName();
    
    public String getHandle();

    public String getText();

    public String getProfileImageURL();
    
    public String getDateString();
    
    public Date getDate();
    
    public boolean isRetweet();
    
    public boolean isLikedByUser();
    
    public boolean isRetweetedByUser();
    
    public boolean isFollowingUser();
    
    public default boolean isSaved() {
        return false;
    }

    public int getNumReplies(); 
    
    public int getNumRetweets();
    
    public int getNumLikes();
    
    default String getStatusUrl() {
        String url= "https://twitter.com/" + this.getHandle() + "/status/" + this.getStatusId();
        return url;
    }
    
    public void update();
}
