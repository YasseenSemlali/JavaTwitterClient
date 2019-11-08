DROP TABLE IF EXISTS tweets;

CREATE TABLE tweets (
  statusId bigint NOT NULL,

  name varchar(50) NOT NULL,
  handle varchar(15) NOT NULL,
  text varchar(500) NOT NULL default '',
  profileImageURL varchar(75) NOT NULL,
  date TIMESTAMP NOT NULL,
  isRetweet BOOLEAN NOT NULL default false,
  isLikedByUser BOOLEAN NOT NULL default false,
  isRetweetedByUser BOOLEAN NOT NULL default false,
  isFollowingUser BOOLEAN NOT NULL default false,
  numReplies int NOT NULL default 0,
  numRetweets int NOT NULL default 0,
  numLikes int NOT NULL default 0,
  PRIMARY KEY  (statusId)
);

INSERT INTO TWEETS(statusId, name, handle, text, profileImageURL, date, isRetweet, isLikedByUser, isRetweetedByUser, isFollowingUser, numReplies, numRetweets, numLikes) VALUES
(0, 'Ken Fogel', '@static', 'First tweet in DB', 'url.com', '20191107000000', false, false, false, false, 0,0,0),
(1, 'Ken Fogel', '@static', 'Second Tweet In DB', 'url.com','20191108100000', true, true, true, true, 1,2,3),
(2, 'Yasseen Semlali', '@asdf', 'Python <3', 'example.com', '20200101000000', true, false, true, false, 500,8000,9999),
(3, 'Okuyasu Nijimura', '@ZaHando', 'Oi Josuke', '', '19990101000000', false, true, false, false, 0,3,0),
(4, 'Dio Brando', '@ZaWarudo', 'WRYYYYYYYY (1/2)', 'roadroller.com', '19890101000000', false, false, false, false, 0,0,0),
(5, 'Dio Brando', '@ZaWarudo', 'YYYYYYYYYY (2/2)', 'roadroller.com', '19890101000000', false, false, false, false, 0,0,0),
(6, '', '', '', '', '20191107000000', false, false, false, false, 0,0,0)
;

