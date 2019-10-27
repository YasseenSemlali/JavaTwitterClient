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
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Contains all methods that interface with Twitter directly
 * @author 1742811
 */
public class TwitterEngine {
    private final static Logger LOG = LoggerFactory.getLogger(TwitterEngine.class);

    /**  Gets a timeline from twitter
     * @param page The page of the timeline
     * @param timelineType The type of timeline to retrieve
     * @throws TwitterException
     */
    public List<TweetInfo> getTimeline(int page, TimelineType timelineType) throws TwitterException {
        LOG.debug("getTimeLine | page: " + page);
        
        Twitter twitter = TwitterFactory.getSingleton();

        Paging paging = new Paging();
        paging.setCount(TwitterConstants.TWEETS_PER_UPDATE);
        paging.setPage(page);
        List<Status> statuses;// = twitter.getHomeTimeline(paging);

        switch (timelineType) {
            default:
            case HOME:
                statuses = twitter.getHomeTimeline(paging);
                break;
            case USER:
                statuses = twitter.getUserTimeline(paging);
                break;
            case MENTIONS:
                statuses = twitter.getMentionsTimeline(paging);
                break;
            case RETWEETS_OF_ME:
                statuses = twitter.getRetweetsOfMe(paging);
                break;
        }

        LOG.debug("Retrieved " + statuses.size() + " tweets");

        return statuses.stream().map(s -> new TweetInfo(s)).collect(Collectors.toList());
    }
    
     /** Executes a query
     * @param query The query to execute
     * @return The query results
     * @throws TwitterException
     */
    public  QueryResult searchTweets(Query query) throws TwitterException {
        LOG.debug("searchtweets: " + query.getQuery());

        Twitter twitter = TwitterFactory.getSingleton();

        QueryResult result = twitter.search(query);
        
        LOG.debug("Retrieved " + result.getCount() + " tweets");
        
        return result;
    }

    /** Sends a tweet
     * @param tweet
     * @return The tweet's text
     * @throws TwitterException
     */
    public String sendTweet(StatusUpdate tweet) throws TwitterException {
        LOG.debug("sendTweet: " + tweet.getStatus());

        Twitter twitter = TwitterFactory.getSingleton();
        Status status = twitter.updateStatus(tweet);
        return status.getText();
    }

    /** Sends a direct message
     * @param recipientName Recipient's twitter handle
     * @param msg The message to send
     * @return The message's text
     * @throws TwitterException
     */
    public String sendDM(String recipientName, String msg) throws TwitterException {
        LOG.debug("sendDM: " + msg);

        Twitter twitter = TwitterFactory.getSingleton();
        DirectMessage message = twitter.sendDirectMessage(recipientName, msg);
        return message.getText();
    }
}
