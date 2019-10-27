package com.dawsoncollege.twitterclient.controller;

import com.dawsoncollege.twitterclient.beans.SearchBean;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Query;

public class SearchController {
    private final static Logger LOG = LoggerFactory.getLogger(SearchController.class);
    
    private SearchTimelineController timelineController;
    
    private final SearchBean searchBean;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane content;

    @FXML
    private TextField searchTermTxt;

    @FXML
    void executeSearch(ActionEvent event) {
        Query query = new Query(this.searchBean.getSearchTerm());
        this.timelineController.search(query);
    }

    @FXML
    void initialize() {
        assert content != null : "fx:id=\"content\" was not injected: check your FXML file 'Search.fxml'.";
        assert searchTermTxt != null : "fx:id=\"searchTermTxt\" was not injected: check your FXML file 'Search.fxml'.";

        this.initTimeline();
        Bindings.bindBidirectional(this.searchBean.searchTermProperty(), this.searchTermTxt.textProperty());
    }
    
    public SearchController() {
        this.searchBean = new SearchBean();
    }
    
     private void initTimeline() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SearchTimeline.fxml"), resources);
            Node content = loader.load();
            SearchTimelineController controller = (SearchTimelineController) loader.getController();

            this.content.setCenter(content);
            this.timelineController = controller;
        } catch (IOException ex) {
            LOG.error("initSendTweet error", ex);
            Platform.exit();
        }
    }

}
