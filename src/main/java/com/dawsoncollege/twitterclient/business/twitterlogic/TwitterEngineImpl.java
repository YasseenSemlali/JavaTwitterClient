package com.dawsoncollege.twitterclient.business.twitterlogic;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import com.dawsoncollege.twitterclient.business.TweetInfoImpl;
import com.dawsoncollege.twitterclient.business.TwitterConstants;
import com.dawsoncollege.twitterclient.business.timelines.TimelineType;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
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
 * @author Yasseen
 */
public class TwitterEngineImpl implements TwitterEngine {

    private final static Logger LOG = LoggerFactory.getLogger(TwitterEngineImpl.class);

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

        return statuses.stream().map(s -> new TweetInfoImpl(s)).collect(Collectors.toList());
    }

    public QueryResult searchTweets(Query query) throws TwitterException {
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

    public DirectMessageList getDMs(String cursor) throws TwitterException {
        LOG.debug("getDM: " + cursor);

        Twitter twitter = TwitterFactory.getSingleton();
        DirectMessageList messages = twitter.getDirectMessages(TwitterConstants.DIRECT_MESSAGES_PER_UPDATE, cursor);

        return messages;
    }

    public DirectMessageList getDMs() throws TwitterException {
        LOG.debug("getDM: ");

        Twitter twitter = TwitterFactory.getSingleton();
        DirectMessageList messages = twitter.getDirectMessages(TwitterConstants.DIRECT_MESSAGES_PER_UPDATE);

        return messages;
    }

    public boolean isFollowingUser(String source, String target) throws TwitterException {
        LOG.debug("isFollowingUser: " + source + " following " + target);
        
        Twitter twitter = TwitterFactory.getSingleton();
        return twitter.showFriendship(source, target).isSourceFollowingTarget();

    }

    public String getUserHandle() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();

        return twitter.getScreenName();
    }

    @Override
    public void likeTweet(long statusId) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();

        twitter.createFavorite(statusId);

    }

    @Override
    public void unlikeTweet(long statusId) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();

        twitter.destroyFavorite(statusId);
    }

    @Override
    public void retweetTweet(long statusId) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();

        twitter.retweetStatus(statusId);
    }

    @Override
    public void unretweetTweet(long statusId) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.unRetweetStatus(statusId);
    }

    @Override
    public void followUser(String handle) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        
        twitter.createFriendship(handle);
    }

    @Override
    public void unfollowUser(String handle) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        
        twitter.destroyFriendship(handle);
    }

    @Override
    public Status getStatus(long id) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        
        return twitter.showStatus(id);
    }
}
