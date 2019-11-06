/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.sql;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Yasseen
 */
public interface TweetDAO {

    List<TweetInfo> getTweets(int page);

    boolean isSaved(TweetInfo info);

    int saveTweet(TweetInfo info) throws SQLException;

    int unsaveTweet(TweetInfo info) throws SQLException;
    
}
