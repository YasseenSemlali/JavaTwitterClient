/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import com.dawsoncollege.twitterclient.controller.SendTweetController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author 1742811
 */
public class TweetInfo {
    private final static Logger LOG = LoggerFactory.getLogger(TweetInfo.class);

    private final Status status;
    
    public TweetInfo(Status status) {
        this.status = status;
    }
    
    public String getName() {
        return this.status.getUser().getName();
    }

    public String getText(){
        return this.status.getText();
    }

    public String getProfieImageURL(){
        return this.status.getUser().getProfileImageURL();
    }
    
    public String getHandle() {
      return this.status.getUser().getScreenName();
    }
    
    public String getDateString() {
        return this.status.getCreatedAt().toString();
    }
    
    public boolean isRetweet() {
        return this.status.isRetweet();
    }
    
    public boolean isLikedByUser() {
        return this.status.isFavorited();
    }
    
    public boolean isRetweetedByUser() {
        return this.status.isRetweeted();
    }
    
    public long getStatusId() {
        return this.status.getId();
    }

    public int getNumReplies() {
        Twitter twitter = TwitterFactory.getSingleton();
        
        try{
        Query query = new Query("to:" + this.status.getUser().getScreenName());
        query.setSinceId(this.status.getId());
        return twitter.search(query).getTweets().size();
        } catch(TwitterException e) {
            LOG.debug("getNumTweets error, returning 0");
            return 0;
        }
    }
    
    public int getNumRetweets() {
        return this.status.getRetweetCount();
    }
    
    public int getNumLikes() {
        return this.status.getFavoriteCount();
    }
    
    public String getStatusUrl() {
        String url= "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
        return url;
    }
}
