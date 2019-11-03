package com.dawsoncollege.twitterclient.business;

/** Retrieves a tweet's info from the values it was instantiated with
 *
 * @author Yasseen
 */
public class TweetInfoGeneric implements TweetInfo {

    private final String name;
    private final String handle;
    private final String text;
    private final String profieImageURL;
    private final String dateString;
    private final boolean isRetweet;
    private final boolean isLikedByUser;
    private final boolean isRetweetedByUser;
    private final boolean isFollowingUser;
    private final long statusId;
    private final int numReplies;
    private final int numRetweets;
    private final int numLikes;
    private final long id;

    public TweetInfoGeneric(TweetInfo info) {
        this(info.getName(), info.getText(), info.getProfileImageURL(), info.getHandle(), info.getDateString(), info.isRetweet(), info.isLikedByUser(), info.isRetweetedByUser(), info.isFollowingUser(), info.getStatusId(), info.getNumReplies(), info.getNumRetweets(), info.getNumLikes(), info.getId());
    }
    
    public TweetInfoGeneric(String name, String text, String profieImageURL, String handle, String dateString, boolean isRetweet, boolean isLikedByUser, boolean isRetweetedByUser, boolean isFollowingUser, long statusId, int numReplies, int numRetweets, int numLikes, long id) {
        this.name = name;
        this.text = text;
        this.profieImageURL = profieImageURL;
        this.handle = handle;
        this.dateString = dateString;
        this.isRetweet = isRetweet;
        this.isLikedByUser = isLikedByUser;
        this.isRetweetedByUser = isRetweetedByUser;
        this.isFollowingUser = isFollowingUser;
        this.statusId = statusId;
        this.numReplies = numReplies;
        this.numRetweets = numRetweets;
        this.numLikes = numLikes;
        this.id = id;
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
        return this.dateString;
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
    public long getId() {
        return this.id;
    }

    @Override
    public boolean isFollowingUser() {
        return this.isFollowingUser;
    }

}
