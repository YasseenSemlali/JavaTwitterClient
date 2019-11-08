/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business.twitterlogic;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import com.dawsoncollege.twitterclient.business.timelines.TimelineType;
import java.util.List;
import twitter4j.DirectMessageList;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

/**
 * Contains all methods that interface with Twitter directly
 * @author Yasseen
 */
public interface TwitterEngine {

    /**  Gets a timeline from twitter
     * @param page The page of the timeline
     * @param timelineType The type of timeline to retrieve
     * @throws TwitterException
     */
    public List<TweetInfo> getTimeline(int page, TimelineType timelineType) throws TwitterException;
    
     /** Executes a query
     * @param query The query to execute
     * @return The query results
     * @throws TwitterException
     */
    public  QueryResult searchTweets(Query query) throws TwitterException;

    /** Sends a tweet
     * @param tweet
     * @return The tweet's text
     * @throws TwitterException
     */
    public String sendTweet(StatusUpdate tweet) throws TwitterException;

    /** Sends a direct message
     * @param recipientName Recipient's twitter handle
     * @param msg The message to send
     * @return The message's text
     * @throws TwitterException
     */
    public String sendDM(String recipientName, String msg) throws TwitterException;
    
    /** Gets a list of direct messages, starting from the cursor
     * @param cursor
     * @return
     * @throws TwitterException
     */
    public DirectMessageList getDMs(String cursor) throws TwitterException;
    
    /** Gets a list of direct messages, starting from the most recent
     * @return
     * @throws TwitterException
     */
    public DirectMessageList getDMs() throws TwitterException;
    
    /** Checks if the source user is following the target user
     * @param source Source user's twitter handle
     * @param target Target user's twitter handle
     * @return Whether the source is following the target
     * @throws TwitterException
     */
    public boolean isFollowingUser(String source, String target) throws TwitterException;
    
    /** Retrieves the user's @ handle
     * @return The user's handle
     * @throws TwitterException
     */
     public String getUserHandle() throws TwitterException;
     
     public void likeTweet(long statusId) throws TwitterException;
     
     public void unlikeTweet(long statusId) throws TwitterException;
     
     public void retweetTweet(long statusId) throws TwitterException;
     
     public void unretweetTweet(long statusId) throws TwitterException;
     
     public Status getStatus(long id)  throws TwitterException;
     
     public void followUser(String handle) throws TwitterException;
     
     public void unfollowUser(String handle) throws TwitterException;
}
