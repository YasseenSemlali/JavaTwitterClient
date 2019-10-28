/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yasseen
 */
public class AuthenticateBean {
    private final StringProperty consumerKey;
    private final StringProperty consumerSecretKey;
    private final StringProperty accessKey;
    private final StringProperty accessSecretKey;
    
    public AuthenticateBean() {
        this.consumerKey = new SimpleStringProperty("");
        this.consumerSecretKey = new SimpleStringProperty("");
        this.accessKey = new SimpleStringProperty("");
        this.accessSecretKey = new SimpleStringProperty("");
    }

    public String getConsumerKey() {
        return consumerKey.get();
    }

    public String getConsumerSecretKey() {
        return consumerSecretKey.get();
    }

    public String getAccessKey() {
        return accessKey.get();
    }

    public String getAccessSecretKey() {
        return accessSecretKey.get();
    }

    public void setConsumerKey(String key) {
        consumerKey.set(key);
    }

    public void setConsumerSecretKey(String key) {
        consumerSecretKey.set(key);
    }

    public void setAccessKey(String key) {
        accessKey.set(key);
    }

    public void setAccessSecretKey(String key) {
        accessSecretKey.set(key);
    }
    
    public StringProperty consumerKeyProperty() {
        return consumerKey;
    }

    public StringProperty consumerSecretKeyProperty() {
        return consumerSecretKey;
    }

    public StringProperty accessKeyProperty() {
        return accessKey;
    }

    public StringProperty accessSecretKeyProperty() {
        return accessSecretKey;
    }
    
}
