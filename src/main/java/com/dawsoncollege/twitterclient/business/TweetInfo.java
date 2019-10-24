/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import twitter4j.Status;

/**
 *
 * @author 1742811
 */
public class TweetInfo {

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

    public String getImageURL(){
        return this.status.getUser().getProfileImageURL();
    }
    
    public String getHandle() {
      return this.status.getUser().getScreenName();
    }
    
    public String getDateString() {
        return this.status.getCreatedAt().toString();
    }
    
    public boolean getIsRetweet() {
        return this.status.isRetweet();
    }

}
