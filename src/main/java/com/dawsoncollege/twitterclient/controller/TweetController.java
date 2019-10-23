package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TweetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text handle;

    @FXML
    private Text date;

    @FXML
    private Text tweetContents;

    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert handle != null : "fx:id=\"handle\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert tweetContents != null : "fx:id=\"tweetContents\" was not injected: check your FXML file 'Tweet.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'Tweet.fxml'.";

    }
    
    public void setContents(TweetInfo info) {
        this.name.setText(info.getName());
        this.handle.setText(info.getHandle());
        this.date.setText(info.getDateString());
        this.tweetContents.setText(info.getText());
        this.image.setImage(new Image(info.getImageURL()));
    }
}
