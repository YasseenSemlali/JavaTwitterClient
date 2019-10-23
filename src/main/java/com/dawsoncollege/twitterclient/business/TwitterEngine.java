/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author 1742811
 */
//Question Static?
public class TwitterEngine {
    private final static Logger LOG = LoggerFactory.getLogger(TwitterEngine.class);
    
     public List<TweetInfo> getHomeTimeline(int page) throws TwitterException {
        LOG.debug("getTimeLine | page: " + page);
        Twitter twitter = TwitterFactory.getSingleton();
        Paging paging = new Paging();
        paging.setCount(TwitterConstants.TWEETS_PER_UPDATE);
        paging.setPage(page);
        List<Status> statuses = twitter.getHomeTimeline(paging);
        LOG.debug("Retrieved " + statuses.size() + " tweets");
        return statuses.stream().map(s -> new TweetInfo(s)).collect(Collectors.toList());
    }
}
