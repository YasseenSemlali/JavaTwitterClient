package com.dawsoncollege.twitterclient.business;

import twitter4j.TwitterException;

/** A timeline containinga twitter timeline. All values are stored internally, and an object to store them must be specified elsewhere
 *
 * @author Yasseen
 */
public interface Timeline{
    /** Gets the next set of tweets from the timeline
     * @throws TwitterException
     */
    public void updateTimeline() throws TwitterException;
    /** Clears all tweets
     * 
     */
    public void reset();
}
