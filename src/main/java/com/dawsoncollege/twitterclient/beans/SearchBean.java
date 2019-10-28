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
public class SearchBean {
    private final StringProperty searchTerm;
    
    public SearchBean() {
        this.searchTerm = new SimpleStringProperty("");
    }
    
    public String getSearchTerm() {
        return this.searchTerm.get();
    }
    
    public void setSearchTerm(String searchTerm) {
        this.searchTerm.set(searchTerm);
    }
    
    public StringProperty searchTermProperty() {
        return this.searchTerm;
    }
}
