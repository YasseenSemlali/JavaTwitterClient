package com.dawsoncollege.twitterclient.business;

/**
 * Wrapper class for the {@link Status} object
 * @author Yasseen
 */
public interface TweetInfo {
    public String getName();
    
    public String getHandle();

    public String getText();

    public String getProfileImageURL();
    
    public String getDateString();
    
    public boolean isRetweet();
    
    public boolean isLikedByUser();
    
    public boolean isRetweetedByUser();
    
    public boolean isFollowingUser();
    
    public default boolean isSaved() {
        return false;
    }
    
    public long getStatusId();

    public int getNumReplies(); 
    
    public int getNumRetweets();
    
    public int getNumLikes();
    
    public long getId();
    
    default String getStatusUrl() {
        String url= "https://twitter.com/" + this.getHandle() + "/status/" + this.getId();
        return url;
    }
}
