package com.dawsoncollege.twitterclient.business;

import com.dawsoncollege.twitterclient.sql.TweetDAO;
import com.dawsoncollege.twitterclient.sql.TweetDAOImpl;
import java.util.List;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

/**
 *
 * @author Yasseen
 */
public class DatabaseTimeline implements Timeline{
    private final static Logger LOG = LoggerFactory.getLogger(DatabaseTimeline.class);

    private final ObservableList<TweetInfo> list;
    private int page;
    
    public DatabaseTimeline(ObservableList<TweetInfo> list) {
        this.list = list;
        this.page = 1;
    }

    @Override
    public void updateTimeline() throws TwitterException {
    	LOG.info("Getting page "+ this.page + " of db timeline ");
        
        
        TweetDAO dao = new TweetDAOImpl();
        List<TweetInfo> timeline = dao.getTweets(page);
        timeline.forEach((info) -> {
            this.list.add(this.list.size(), info);
        });
        
        this.page++;
    }

    @Override
    public void reset() {
        
        this.page = 1;
        this.list.clear();
    }
    
}
