/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import com.dawsoncollege.twitterclient.controller.SearchController;
import static java.util.Collections.list;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 *
 * @author 1742811
 */
public class SearchTimelineImpl implements SearchTimeline {
    private final static Logger LOG = LoggerFactory.getLogger(SearchController.class);

    private final TwitterEngine engine;
    private final ObservableList<TweetInfo> list;
    private QueryResult currentQuery;

    /** 
     * @param list The ObservableList that contains the tweets to display
     */
    public SearchTimelineImpl(ObservableList<TweetInfo> list) {
        
        this.engine = new TwitterEngine();
        this.list = list;
    }

    @Override
    public void search(Query query) throws TwitterException {
        this.list.clear();
        
        this.executeSearch(query);
    }

    @Override
    public void getNext() throws TwitterException {
        if(this.currentQuery == null) {
            LOG.debug("getNext called without a prior search, ignoring");
            return;
        }
        
        this.executeSearch(this.currentQuery.nextQuery());
    }
    
    /** Updates the query in memory to retrieve the next page, and updates the ObservableList with the results
     * @param query The query to execute
     * @throws TwitterException
     */
    private void executeSearch(Query query) throws TwitterException {
    	query.setCount(TwitterConstants.TWEETS_PER_SEARCH);
    	LOG.info("Searching: " + query.getQuery());
    	
    	
        this.currentQuery = this.engine.searchTweets(query);
        List<TweetInfo> statuses = this.currentQuery.getTweets().stream().map(s -> new TweetInfo(s)).collect(Collectors.toList());
        
        statuses.forEach((info) -> {
            this.list.add(this.list.size(), info);
        });
    }
    
}
