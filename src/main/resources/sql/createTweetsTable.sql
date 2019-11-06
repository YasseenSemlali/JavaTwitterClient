DROP TABLE IF EXISTS tweets;

CREATE TABLE tweets (
  statusId bigint NOT NULL,

  name varchar(50) NOT NULL,
  handle varchar(15) NOT NULL,
  text varchar(500) NOT NULL default '',
  profileImageURL varchar(75) NOT NULL,
  date DATE NOT NULL,
  isRetweet BOOLEAN NOT NULL default false,
  isLikedByUser BOOLEAN NOT NULL default false,
  isRetweetedByUser BOOLEAN NOT NULL default false,
  isFollowingUser BOOLEAN NOT NULL default false,
  numReplies int NOT NULL default 0,
  numRetweets int NOT NULL default 0,
  numLikes int NOT NULL default 0,
  PRIMARY KEY  (statusId)
)

