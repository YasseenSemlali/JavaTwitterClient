package com.dawsoncollege.twitterclient.business.twitterlogic;


import com.dawsoncollege.twitterclient.business.twitterlogic.TwitterEngine;
import twitter4j.DirectMessageList;
import twitter4j.TwitterException;

/**
 *
 * @author Yasseen
 */
public class DirectMessagesImpl implements DirectMessages{

    private final TwitterEngine engine;
    private DirectMessageList currentDirectMessageList;
    
    public DirectMessagesImpl() throws TwitterException {        
        this.engine = new TwitterEngineImpl();        
        this.currentDirectMessageList = engine.getDMs();
        
    }
    
    public void loadNext() throws TwitterException {
        this.currentDirectMessageList = this.engine.getDMs(this.currentDirectMessageList.getNextCursor());
    }
}
