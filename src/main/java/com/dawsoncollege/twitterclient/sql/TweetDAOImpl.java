package com.dawsoncollege.twitterclient.sql;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import com.dawsoncollege.twitterclient.io.SQLPropertiesManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Yasseen
 */
public class TweetDAOImpl {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TweetDAOImpl.class);

    private final SQLPropertiesManager propertiesManager;

    public TweetDAOImpl() {
        this.propertiesManager = new SQLPropertiesManager("");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.propertiesManager.getConnectionString(), this.propertiesManager.getUsername(), this.propertiesManager.getPassword());
    }

    public boolean isSaved(TweetInfo info) {
        String query = "SELECT count(*) from tweets WHERE statusId = ?";
        try ( Connection connection = this.getConnection();  PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setLong(1, info.getStatusId());

            try ( ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOG.error("isSaved error, returning false", e);
        }

        return false;
    }

    public int saveTweet(TweetInfo info) throws SQLException{
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
            ps.setDate(6, new java.sql.Date(info.getDate().getTime()));
            ps.setBoolean(7, info.isRetweet());
            ps.setBoolean(8, info.isLikedByUser());
            ps.setBoolean(9, info.isRetweetedByUser());
            ps.setInt(10, info.getNumReplies());
            ps.setInt(11, info.getNumRetweets());
            ps.setInt(12, info.getNumLikes());
            
            result = ps.executeUpdate();

        } 
        
        return result;
    }
    
    public int unsaveTweet(TweetInfo info) throws SQLException{
        SQLPropertiesManager propManager = new SQLPropertiesManager();
        String query = "DELETE FROM tweets WHERE statusId = ?";
        
        int result = 0;
        try ( Connection connection = this.getConnection();  PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setLong(1, info.getStatusId());
            
            result = ps.executeUpdate();
        } 
        
        return result;
    }
    
    public List<TweetInfo> getTweets() {
         String query = "SELECT count(*) from tweets ORDER BY date";
        try ( Connection connection = this.getConnection();  PreparedStatement ps = connection.prepareStatement(query);) {

            //ps.setLong(1, info.getStatusId());

            try ( ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    
                }
            }
        } catch (SQLException e) {
            LOG.error("getTweets error, returning empty list", e);
        }
        
        return null;
    }
}
