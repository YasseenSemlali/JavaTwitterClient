/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.business.TimelineType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.slf4j.LoggerFactory;

public class DatabaseTimelineController {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(DatabaseTimelineController.class);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane content;

    @FXML
    void initialize() {
        assert content != null : "fx:id=\"content\" was not injected: check your FXML file 'HomeTimeline.fxml'.";

        this.initTimeline();
    }

    private void initTimeline() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(resources);
            loader.setLocation(NewFXMain.class.getResource("/fxml/Timeline.fxml"));

            Node homeTimeline = loader.load();
            TimelineController controller = loader.getController();

            controller.setTimelineType(TimelineType.DATABASE);
            controller.updateTimeline();

            this.content.getChildren().setAll(homeTimeline);
        } catch (IOException ex) {
            LOG.error("initTimeline error in DatabaseTimelineController", ex);
        }
    }

}
