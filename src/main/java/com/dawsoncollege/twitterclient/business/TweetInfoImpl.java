/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import com.dawsoncollege.twitterclient.business.twitterlogic.TwitterEngineImpl;
import com.dawsoncollege.twitterclient.business.twitterlogic.TwitterEngine;
import java.util.Date;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/** Retrieves a tweet's info using the twitter API
 *
 * @author Yasseen
 */
public class TweetInfoImpl implements TweetInfo{
    private final static Logger LOG = LoggerFactory.getLogger(TweetInfoImpl.class);

    private Status status;
    
    public TweetInfoImpl(Status status) {
        this.status = status;
    }
    
    @Override
    public String getName() {
        return this.status.getUser().getName();
    }

    @Override
    public String getText(){
        return this.status.getText();
    }

    @Override
    public String getProfileImageURL(){
        return this.status.getUser().getProfileImageURL();
    }
    
    @Override
    public String getHandle() {
      return this.status.getUser().getScreenName();
    }
    
    @Override
    public String getDateString() {
        return this.status.getCreatedAt().toString();
    }

    @Override
    public Date getDate() {
        return this.status.getCreatedAt();
    }
    
    @Override
    public boolean isRetweet() {
        return this.status.isRetweet();
    }
    
    @Override
    public boolean isLikedByUser() {
        return this.status.isFavorited();
    }
    
    @Override
    public boolean isRetweetedByUser() {
        return this.status.isRetweeted();
    }
    
    @Override
    public long getStatusId() {
        return this.status.getId();
    }

    @Override
    public int getNumReplies() {
        TwitterEngine engine = new TwitterEngineImpl();
        
        try{
            Query query = new Query("to:" + this.getHandle());
            query.setSinceId(this.status.getId());
            return engine.searchTweets(query).getTweets().size();
        } catch(TwitterException e) {
            LOG.debug("getNumTweets error, returning 0");
            return 0;
        }
    }
    
    @Override
    public int getNumRetweets() {
        return this.status.getRetweetCount();
    }
    
    @Override
    public int getNumLikes() {
        return this.status.getFavoriteCount();
    }

    @Override
    public boolean isFollowingUser() {
        TwitterEngine engine = new TwitterEngineImpl();
        
        try{
            return engine.isFollowingUser(engine.getUserHandle(), this.getHandle());
        } catch(TwitterException e) {
            LOG.debug("isFollowingUser error, returning false");
            return false;
        }
    }

    @Override
    public void update() {
        TwitterEngine engine = new TwitterEngineImpl();
        try {
            this.status = engine.getStatus(this.status.getId());
        } catch (TwitterException e) {
            LOG.error("Could retrieve updated status, leaving it as is", e);
        }
    }
}
