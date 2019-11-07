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
public class SQLCredentialsBean {
    private final StringProperty url;
    private final StringProperty username;
    private final StringProperty password;
    
    public SQLCredentialsBean() {
        this.url = new SimpleStringProperty("");
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
    }

    public String getUrl() {
        return url.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    public void setUrl(String key) {
        url.set(key);
    }

    public void setUsername(String key) {
        username.set(key);
    }

    public void setPassword(String key) {
        password.set(key);
    }
    
    public StringProperty urlProperty() {
        return url;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }
    
}
