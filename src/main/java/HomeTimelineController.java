
import com.dawsoncollege.twitterclient.NewFXMain;
import com.dawsoncollege.twitterclient.business.TimelineType;
import com.dawsoncollege.twitterclient.controller.FeedController;
import com.dawsoncollege.twitterclient.controller.TwitterRootController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.slf4j.LoggerFactory;

public class HomeTimelineController {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(HomeTimelineController.class);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane content;

    @FXML
    void initialize() {
        assert content != null : "fx:id=\"content\" was not injected: check your FXML file 'HomeTimeline.fxml'.";
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(resources);
            loader.setLocation(NewFXMain.class.getResource("/fxml/Feed.fxml"));

            Node homeTimeline = loader.load();
            FeedController controller = loader.getController();
            controller.setTimelineType(TimelineType.HOME);

            this.content.getChildren().setAll(homeTimeline);
        } catch (IOException ex) {
            LOG.error("initMenu error", ex);
            Platform.exit();
        }
    }
}
