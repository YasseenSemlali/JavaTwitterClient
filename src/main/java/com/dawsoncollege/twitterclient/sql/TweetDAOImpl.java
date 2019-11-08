package com.dawsoncollege.twitterclient.sql;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import com.dawsoncollege.twitterclient.business.TweetInfoGeneric;
import com.dawsoncollege.twitterclient.business.TwitterConstants;
import com.dawsoncollege.twitterclient.io.SQLPropertiesManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Yasseen
 */
public class TweetDAOImpl implements TweetDAO {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TweetDAOImpl.class);

    private final SQLPropertiesManager propertiesManager;

    public TweetDAOImpl() {
        this.propertiesManager = new SQLPropertiesManager();
    }

    public TweetDAOImpl(String propertiesURL) {
        this.propertiesManager = new SQLPropertiesManager(propertiesURL);
    }

    private Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(this.propertiesManager.getUrl(), this.propertiesManager.getUsername(), this.propertiesManager.getPassword());
    }

    @Override
    public boolean isSaved(long statusId) {
        String query = "SELECT count(*) from tweets WHERE statusId = ?";
        try ( Connection connection = this.getConnection();  PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setLong(1, statusId);

            try ( ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOG.error("isSaved sql error, returning false", e);
        } catch (IOException e) {
            LOG.error("isSaved IO error, returning false", e);
        }

        return false;
    }

    @Override
    public int saveTweet(TweetInfo info) throws SQLException {
        SQLPropertiesManager propManager = new SQLPropertiesManager();
        String query = "INSERT INTO tweets(statusId, name, handle, text, profileImageURL, date, isRetweet, isLikedByUser, isRetweetedByUser, numReplies, numRetweets, numLikes) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

        int result = 0;
        try ( Connection connection = this.getConnection();  PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setLong(1, info.getStatusId());
            ps.setString(2, info.getName());
            ps.setString(3, info.getHandle());
            ps.setString(4, info.getText());
            ps.setString(5, info.getProfileImageURL());
            ps.setTimestamp(6, new java.sql.Timestamp(info.getDate().getTime()));
            ps.setBoolean(7, info.isRetweet());
            ps.setBoolean(8, info.isLikedByUser());
            ps.setBoolean(9, info.isRetweetedByUser());
            ps.setInt(10, info.getNumReplies());
            ps.setInt(11, info.getNumRetweets());
            ps.setInt(12, info.getNumLikes());

            result = ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error("saveTweet error, returning empty list", e);
        } catch (IOException e) {
            LOG.error("saveTweet IO error, returning 0", e);
        }

        return result;
    }

    @Override
    public int unsaveTweet(long statusId) throws SQLException {
        SQLPropertiesManager propManager = new SQLPropertiesManager();
        String query = "DELETE FROM tweets WHERE statusId = ?";

        int result = 0;
        try ( Connection connection = this.getConnection();  PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setLong(1, statusId);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("unsaveTweet error, returning empty list", e);
        } catch (IOException e) {
            LOG.error("unsaveTweet IO error, returning false", e);
        }

        return result;
    }

    @Override
    public List<TweetInfo> getTweets(int page) {
        return this.getTweets(page, -1);
    }

    @Override
    public List<TweetInfo> getTweets(int page, int tweetsPerPage) {
        String query = "SELECT statusId, name, handle, text, profileImageURL, date, isRetweet, isLikedByUser, isRetweetedByUser, isFollowingUser, numReplies, numRetweets, numLikes from tweets "
                + "ORDER BY date DESC, statusid ASC LIMIT ? OFFSET ?";
        try ( Connection connection = this.getConnection();  PreparedStatement ps = connection.prepareStatement(query);) {

            int numTweets = tweetsPerPage == -1 ? TwitterConstants.TWEETS_PER_UPDATE : tweetsPerPage;

            ps.setInt(1, numTweets);
            ps.setInt(2, (page - 1) * numTweets);

            try ( ResultSet resultSet = ps.executeQuery()) {
                List<TweetInfo> tweets = new ArrayList<TweetInfo>();
                while (resultSet.next()) {
                    TweetInfo tweetInfo = new TweetInfoGeneric(
                            resultSet.getLong("statusId"),
                            resultSet.getString("name"),
                            resultSet.getString("handle"),
                            resultSet.getString("text"),
                            resultSet.getString("profileImageURL"),
                            resultSet.getTimestamp("date"),
                            resultSet.getBoolean("isRetweet"),
                            resultSet.getBoolean("isLikedByUser"),
                            resultSet.getBoolean("isRetweetedByUser"),
                            resultSet.getBoolean("isFollowingUser"),
                            resultSet.getInt("numReplies"),
                            resultSet.getInt("numRetweets"),
                            resultSet.getInt("numLikes")
                    );

                    tweets.add(tweetInfo);
                }

                return tweets;
            }
        } catch (SQLException e) {
            LOG.error("getTweets error, returning empty list", e);
        } catch (IOException e) {
            LOG.error("getTweets IO error, returning false", e);
        }

        return new ArrayList<TweetInfo>();
    }

    @Override
    public TweetInfo getTweet(long statusId) {
        String query = "SELECT statusId, name, handle, text, profileImageURL, date, isRetweet, isLikedByUser, isRetweetedByUser, isFollowingUser, numReplies, numRetweets, numLikes from tweets "
                + "WHERE statusId = ? ";
        try ( Connection connection = this.getConnection();  PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setLong(1, statusId);

            try ( ResultSet resultSet = ps.executeQuery()) {
                resultSet.next();
                TweetInfo tweetInfo = new TweetInfoGeneric(
                        resultSet.getLong("statusId"),
                        resultSet.getString("name"),
                        resultSet.getString("handle"),
                        resultSet.getString("text"),
                        resultSet.getString("profileImageURL"),
                        resultSet.getTimestamp("date"),
                        resultSet.getBoolean("isRetweet"),
                        resultSet.getBoolean("isLikedByUser"),
                        resultSet.getBoolean("isRetweetedByUser"),
                        resultSet.getBoolean("isFollowingUser"),
                        resultSet.getInt("numReplies"),
                        resultSet.getInt("numRetweets"),
                        resultSet.getInt("numLikes")
                );

                return tweetInfo;
            }
        } catch (SQLException e) {
            LOG.error("getTweets error, returning empty list", e);
        } catch (IOException e) {
            LOG.error("getTweets IO error, returning false", e);
        }

        return null;
    }
}
