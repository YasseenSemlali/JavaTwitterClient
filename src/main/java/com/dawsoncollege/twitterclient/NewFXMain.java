
package com.dawsoncollege.twitterclient;

import com.dawsoncollege.twitterclient.controller.AuthenticateController;
import com.dawsoncollege.twitterclient.controller.TwitterRootController;
import com.dawsoncollege.twitterclient.io.TwitterPropertiesManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.slf4j.LoggerFactory;

/**
 *
 * @author Yasseen
 */
public class NewFXMain extends Application {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(NewFXMain.class);
    
    @Override
    public void start(Stage primaryStage) {
        TwitterPropertiesManager propManager = new TwitterPropertiesManager("twitter4j.properties");
        try {
            if(!propManager.hasCredentials()) {
               this.popup();
            }
            
            ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TwitterRoot.fxml"), bundle);

            
            Parent root = loader.load();
            TwitterRootController controller = loader.getController();
            
            Scene scene = new Scene(root);

            primaryStage.setTitle("Twitter Client");

            primaryStage.setScene(scene);
            primaryStage.show();
            

        } catch (IOException | IllegalStateException ex) {
            LOG.error("Error in start method", ex);
            Platform.exit();
        }
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** Displays a popup to create the twitter4j.properties file
     * @throws IOException
     */
    private void popup() throws IOException {
        Stage stage = new Stage();
        
            ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Authenticate.fxml"), bundle);

            Parent root = loader.load();
            AuthenticateController controller = loader.getController();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Twitter Client");
            
            // Close app when X button is clicked
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent arg0) {
                Platform.exit();
            }
            }); 
            
            stage.setScene(scene);
            stage.showAndWait();
    }

}
