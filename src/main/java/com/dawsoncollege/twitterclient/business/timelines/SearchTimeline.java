
package com.dawsoncollege.twitterclient.business.timelines;

import twitter4j.Query;
import twitter4j.TwitterException;

/** A timeline containing search results. All values are stored internally, and an object to store them must be specified elsewhere
 *
 * @author Yasseen
 */
public interface SearchTimeline {
    /** Searches twitter using the specified query 
     * @param query The query to be executed
     * @throws TwitterException
     */
    public void search(Query query) throws TwitterException;
    /** Adds the next set of search results to the list of results
     * @throws TwitterException
     */
    public void getNext() throws TwitterException;
}
