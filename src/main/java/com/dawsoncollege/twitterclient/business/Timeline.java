/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 *
 * @author 1742811
 */
public interface Timeline{
    public void updateTimeline() throws TwitterException;
    public void reset();
}
