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
 *
 * @author 1742811
 */
//Question Static?
public class TwitterEngine {

    private final static Logger LOG = LoggerFactory.getLogger(TwitterEngine.class);


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

    /*
    public  List<TweetInfo> searchTweets(Query query) throws TwitterException {
        LOG.debug("searchtweets: " + query.getQuery());

        Twitter twitter = TwitterFactory.getSingleton();

        QueryResult result = twitter.search(query);
        List<Status> statuses = result.getTweets();
        
        LOG.debug("Retrieved " + result.getCount() + " tweets");
        
        return statuses.stream().map(s -> new TweetInfo(s)).collect(Collectors.toList());
    }
*/
    
     public  QueryResult searchTweets(Query query) throws TwitterException {
        LOG.debug("searchtweets: " + query.getQuery());

        Twitter twitter = TwitterFactory.getSingleton();

        QueryResult result = twitter.search(query);
        
        LOG.debug("Retrieved " + result.getCount() + " tweets");
        
        return result;
    }

    public String sendTweet(StatusUpdate tweet) throws TwitterException {
        LOG.debug("sendTweet: " + tweet.getStatus());

        Twitter twitter = TwitterFactory.getSingleton();
        Status status = twitter.updateStatus(tweet);
        return status.getText();
    }

    public String sendDM(String recipientName, String msg) throws TwitterException {
        LOG.debug("sendDM: " + msg);

        Twitter twitter = TwitterFactory.getSingleton();
        DirectMessage message = twitter.sendDirectMessage(recipientName, msg);
        return message.getText();
    }
}
