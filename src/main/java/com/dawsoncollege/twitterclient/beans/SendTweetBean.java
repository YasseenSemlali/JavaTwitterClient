/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yasseen
 */
public class SendTweetBean {
    private final StringProperty tweetProperty;
    
    public SendTweetBean() {
        this.tweetProperty = new SimpleStringProperty("");
    }
    
    public String getTweet() {
        return this.tweetProperty.get();
    }
    
    public void setTweet(String tweet) {
        this.tweetProperty.set(tweet);
    }
    
    public StringProperty tweetProperty() {
        return this.tweetProperty;
    }
}
