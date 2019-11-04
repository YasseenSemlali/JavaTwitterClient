package com.dawsoncollege.twitterclient.business;

import java.util.Date;

/** Retrieves a tweet's info from the values it was instantiated with
 *
 * @author Yasseen
 */
public class TweetInfoGeneric implements TweetInfo {

    private final String name;
    private final String handle;
    private final String text;
    private final String profieImageURL;
    private Date date;
    private final boolean isRetweet;
    private final boolean isLikedByUser;
    private final boolean isRetweetedByUser;
    private final boolean isFollowingUser;
    private final long statusId;
    private final int numReplies;
    private final int numRetweets;
    private final int numLikes;

    public TweetInfoGeneric(TweetInfo info) {
        this(info.getName(), info.getText(), info.getProfileImageURL(), info.getHandle(), info.getDate(), info.isRetweet(), info.isLikedByUser(), info.isRetweetedByUser(), info.isFollowingUser(), info.getStatusId(), info.getNumReplies(), info.getNumRetweets(), info.getNumLikes());
    }
    
    public TweetInfoGeneric(String name, String text, String profieImageURL, String handle, Date date, boolean isRetweet, boolean isLikedByUser, boolean isRetweetedByUser, boolean isFollowingUser, long statusId, int numReplies, int numRetweets, int numLikes) {
        this.name = name;
        this.text = text;
        this.profieImageURL = profieImageURL;
        this.handle = handle;
        this.date = date;
        this.isRetweet = isRetweet;
        this.isLikedByUser = isLikedByUser;
        this.isRetweetedByUser = isRetweetedByUser;
        this.isFollowingUser = isFollowingUser;
        this.statusId = statusId;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
