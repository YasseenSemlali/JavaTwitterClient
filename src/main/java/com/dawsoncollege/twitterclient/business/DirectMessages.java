/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import twitter4j.TwitterException;

/**
 *
 * @author Yasseen
 */
public interface DirectMessages {
    public void loadNext() throws TwitterException;
}
