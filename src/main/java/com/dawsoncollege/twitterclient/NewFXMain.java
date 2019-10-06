
package com.dawsoncollege.twitterclient;

import com.dawsoncollege.twitterclient.controller.AuthenticateController;
import com.dawsoncollege.twitterclient.controller.TwitterRootController;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import org.slf4j.LoggerFactory;

/**
 *
 * @author 1742811
 */
public class NewFXMain extends Application {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TwitterRootController.class);
    
    @Override
    public void start(Stage primaryStage) {
        try {
            
            if(!this.hasCredentials()) {
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

            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);

            // See code samples for displaying an Alert box if an exception is thrown
        }
    }
    
    /** Validates the twitter4j.properties file
     * @return Whether or not the twitter4j.properties file is there and valid
     */
    private boolean hasCredentials() {
        Properties prop = new Properties();
        try (InputStream propFileStream = new FileInputStream("twitter4j.properties");){
               prop.load(propFileStream);
               
               return(
                     prop.get("oauth.consumerKey") != null &&
                     prop.get("oauth.consumerSecret") != null &&
                     prop.get("oauth.accessToken") != null &&
                     prop.get("oauth.accessTokenSecret") != null
                       );
            } catch (FileNotFoundException ex) {
                // Do nothing
            } catch (IOException ex) {
                // Do nothing
            }
        return false;
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
