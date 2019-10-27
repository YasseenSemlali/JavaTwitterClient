
package com.dawsoncollege.twitterclient.business;

import java.util.List;
import twitter4j.Query;
import twitter4j.TwitterException;

/**
 *
 * @author 1742811
 */
public interface SearchTimeline {
    public void search(Query query) throws TwitterException;
    public void getNext() throws TwitterException;
}
