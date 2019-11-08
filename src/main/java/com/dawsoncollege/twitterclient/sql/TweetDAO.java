/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.sql;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import com.dawsoncollege.twitterclient.business.TwitterConstants;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Yasseen
 */
public interface TweetDAO {

    /** Returns the tweet with specified statusId
     * @param statusId
     * @return The tweet with the status it, or null if doesn't exist
     */
    TweetInfo getTweet(long statusId);

    /** Gets all tweets on the specified page. The number of tweets per page is specified in {@link TwitterConstants}
     * @param page The page of the resultset. Starts at 1.
     * @return The list of results
     */
    List<TweetInfo> getTweets(int page);
    
    /** Gets all tweets on the specified page.
     * @param page The page of the resultset. Starts at 1.
     * @param tweetsPerPage Number of tweets in a page. Defaults to TWEET_PER_PAGE in {@link TwitterConstants} if tweetsPerPage is -1
     * @return The list of results
     */
    List<TweetInfo> getTweets(int page, int tweetsPerPage);

    /** Checks if a tweet with the specified statusId is stored in the database
     * @param statusId
     * @return 
     */
    boolean isSaved(long statusId);

    /** Saves a tweet in the database
     * @param info
     * @return 1 if the tweet was saved, 0 if an error occured
     * @throws SQLException
     */
    int saveTweet(TweetInfo info) throws SQLException;

    /** Removes a tweet in the database
     * @param info
     * @return 1 if the tweet was unsaved, 0 if an error occured
     * @throws SQLException
     */
    int unsaveTweet(long statusId) throws SQLException;
    
}
