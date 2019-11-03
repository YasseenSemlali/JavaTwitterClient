/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient.business;

import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.controller.TweetController;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Yasseen
 */
public class TimelineCell extends ListCell<TweetInfo>{
    private final static Logger LOG = LoggerFactory.getLogger(TimelineCell.class);

    
    @Override
    protected void updateItem(TweetInfo item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(getTimelineCell(item));
        }
    }
   /** Generates a JavaFX node based on TweetInfo
     *
     * @param info  
     * @return The JavaFX node
     */
    private Node getTimelineCell(TweetInfo info) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tweet.fxml"),  ResourceBundle.getBundle(TwitterConstants.RESOURCE_BUNDLE_DIR));            
            VBox content = (VBox) loader.load();            
            TweetController controller =  (TweetController) loader.getController();
            
            controller.setContents(info);
            
            return content;
        } catch (IOException ex) {
            LOG.error("getTimelineCell error. Path: /fxml/Tweet.fxml", ex);
            
            return new HBox();
        }
    }
}
