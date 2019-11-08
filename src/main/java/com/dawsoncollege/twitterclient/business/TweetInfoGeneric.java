package com.dawsoncollege.twitterclient.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.dawsoncollege.twitterclient.sql.TweetDAO;
import com.dawsoncollege.twitterclient.sql.TweetDAOImpl;

/** Retrieves a tweet's info from the values it was instantiated with
 *
 * @author Yasseen
 */
public class TweetInfoGeneric implements TweetInfo {

    @Override
    public String toString() {
        return "TweetInfoGeneric{" + "statusId=" + statusId + ", name=" + name + ", handle=" + handle + ", text=" + text + ", profieImageURL=" + profieImageURL + ", date=" + new SimpleDateFormat("yyyyMMddhhmmss").format(date) + ", isRetweet=" + isRetweet + ", isLikedByUser=" + isLikedByUser + ", isRetweetedByUser=" + isRetweetedByUser + ", isFollowingUser=" + isFollowingUser + ", numReplies=" + numReplies + ", numRetweets=" + numRetweets + ", numLikes=" + numLikes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.handle);
        hash = 29 * hash + Objects.hashCode(this.text);
        hash = 29 * hash + Objects.hashCode(this.profieImageURL);
        hash = 29 * hash + Objects.hashCode(this.date);
        hash = 29 * hash + (this.isRetweet ? 1 : 0);
        hash = 29 * hash + (this.isLikedByUser ? 1 : 0);
        hash = 29 * hash + (this.isRetweetedByUser ? 1 : 0);
        hash = 29 * hash + (this.isFollowingUser ? 1 : 0);
        hash = 29 * hash + (int) (this.statusId ^ (this.statusId >>> 32));
        hash = 29 * hash + this.numReplies;
        hash = 29 * hash + this.numRetweets;
        hash = 29 * hash + this.numLikes;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TweetInfoGeneric other = (TweetInfoGeneric) obj;
        if (this.isRetweet != other.isRetweet) {
            return false;
        }
        if (this.isLikedByUser != other.isLikedByUser) {
            return false;
        }
        if (this.isRetweetedByUser != other.isRetweetedByUser) {
            return false;
        }
        if (this.isFollowingUser != other.isFollowingUser) {
            return false;
        }
        if (this.statusId != other.statusId) {
            return false;
        }
        if (this.numReplies != other.numReplies) {
            return false;
        }
        if (this.numRetweets != other.numRetweets) {
            return false;
        }
        if (this.numLikes != other.numLikes) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.handle, other.handle)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.profieImageURL, other.profieImageURL)) {
            return false;
        }
        if (!Objects.equals(this.date.getTime(), other.date.getTime())) {
            return false;
        }
        return true;
    }

    private  long statusId;
    private  String name;
    private String handle;
    private  String text;
    private  String profieImageURL;
    private Date date;
    private  boolean isRetweet;
    private  boolean isLikedByUser;
    private  boolean isRetweetedByUser;
    private  boolean isFollowingUser;
    private  int numReplies;
    private  int numRetweets;
    private  int numLikes;

    public TweetInfoGeneric(TweetInfo info) {
        this(info.getStatusId(), info.getName(), info.getHandle(), info.getText(), info.getProfileImageURL(), info.getDate(), info.isRetweet(), info.isLikedByUser(), info.isRetweetedByUser(), info.isFollowingUser(), info.getNumReplies(), info.getNumRetweets(), info.getNumLikes());
    }
    
    public TweetInfoGeneric(long statusId, String name, String handle, String text, String profieImageURL, Date date, boolean isRetweet, boolean isLikedByUser, boolean isRetweetedByUser, boolean isFollowingUser, int numReplies, int numRetweets, int numLikes) {
        this.statusId = statusId;
        this.name = name;
        this.text = text;
        this.profieImageURL = profieImageURL;
        this.handle = handle;
        this.date = date;
        this.isRetweet = isRetweet;
        this.isLikedByUser = isLikedByUser;
        this.isRetweetedByUser = isRetweetedByUser;
        this.isFollowingUser = isFollowingUser;
        this.numReplies = numReplies;
        this.numRetweets = numRetweets;
        this.numLikes = numLikes;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getProfileImageURL() {
        return this.profieImageURL;
    }

    @Override
    public String getHandle() {
        return this.handle;
    }

    @Override
    public String getDateString() {
        return this.date.toString();
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public boolean isRetweet() {
        return this.isRetweet;
    }

    @Override
    public boolean isLikedByUser() {
        return this.isLikedByUser;
    }

    @Override
    public boolean isRetweetedByUser() {
        return this.isRetweetedByUser;
    }

    @Override
    public long getStatusId() {
        return this.statusId;
    }

    @Override
    public int getNumReplies() {
        return this.numReplies;
    }

    @Override
    public int getNumRetweets() {
        return this.numRetweets;
    }

    @Override
    public int getNumLikes() {
        return this.numLikes;
    }

    @Override
    public boolean isFollowingUser() {
        return this.isFollowingUser;
    }

    @Override
    public void update() {
        //Do nothing, since database info doesn't change when a tweet does
    }

}
