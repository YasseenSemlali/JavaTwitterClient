
package com.dawsoncollege.twitterclient.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SendTweetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea contents;

    @FXML
    private Text errMsg;

    @FXML
    void sendTweet(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert contents != null : "fx:id=\"contents\" was not injected: check your FXML file 'SendTweet.fxml'.";
        assert errMsg != null : "fx:id=\"errMsg\" was not injected: check your FXML file 'SendTweet.fxml'.";

    }
}
