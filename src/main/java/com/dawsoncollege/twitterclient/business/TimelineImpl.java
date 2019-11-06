/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.ObservableList;
import twitter4j.TwitterException;

/**
 *
 * @author Yasseen
 */
public class TimelineImpl implements Timeline{  
    private final static Logger LOG = LoggerFactory.getLogger(TimelineImpl.class);

    private final TwitterEngine engine;
    private final ObservableList<TweetInfo> list;
    private int page;
    private final TimelineType timelineType;
    
    public TimelineImpl(ObservableList<TweetInfo> list, TimelineType timelineType) {
        this.engine = new TwitterEngineImpl();
        this.list = list;
        this.page = 1;
        this.timelineType = timelineType;
    }
    
    public void updateTimeline() throws TwitterException {
    	LOG.info("Getting page "+ this.page + " of timeline " + this.timelineType);
    	
        List<TweetInfo> timeline = this.engine.getTimeline(this.page, this.timelineType);
        timeline.forEach((info) -> {
            this.list.add(this.list.size(), info);
        });
        
        this.page++;
    }
    
    public void reset() {
        this.page = 1;
        this.list.clear();
    }
}
