/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import java.util.List;
import javafx.collections.ObservableList;
import twitter4j.TwitterException;

/**
 *
 * @author 1742811
 */
public class TimelineImpl implements Timeline{

    private final TwitterEngine engine;
    private final ObservableList<TweetInfo> list;
    private int page;
    private final TimelineType timelineType;
    
    public TimelineImpl(ObservableList<TweetInfo> list, TimelineType timelineType) {
        this.engine = new TwitterEngine();
        this.list = list;
        this.page = 1;
        this.timelineType = timelineType;
    }
    
    //Question Why size?
    public void updateTimeline() throws TwitterException {
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
