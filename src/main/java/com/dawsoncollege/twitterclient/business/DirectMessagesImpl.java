package com.dawsoncollege.twitterclient.business;


import com.dawsoncollege.twitterclient.business.TwitterEngine;
import twitter4j.DirectMessageList;
import twitter4j.TwitterException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yasseen
 */
public class DirectMessagesImpl implements DirectMessages{

    private final TwitterEngine engine;
    private DirectMessageList currentDirectMessageList;
    
    public DirectMessagesImpl() throws TwitterException {        
        this.engine = new TwitterEngine();        
        this.currentDirectMessageList = engine.getDMs();
        
    }
    
    public void loadNext() throws TwitterException {
        this.currentDirectMessageList = this.engine.getDMs(this.currentDirectMessageList.getNextCursor());
    }
}
