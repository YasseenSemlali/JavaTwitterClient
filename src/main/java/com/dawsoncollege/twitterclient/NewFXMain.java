
package com.dawsoncollege.twitterclient;

import com.dawsoncollege.twitterclient.controller.AuthenticateController;
import com.dawsoncollege.twitterclient.controller.TwitterRootController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

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
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TwitterRoot.fxml"));

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
    
    private boolean hasCredentials() {
        Properties prop = new Properties();
        try (InputStream propFileStream = new FileInputStream("src/main/resources/twitter4j.properties");){
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

    private void popup() throws IOException {
        Stage stage = new Stage();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Authenticate.fxml"));

            Parent root = loader.load();
            AuthenticateController controller = loader.getController();
            
            Scene scene = new Scene(root);

            stage.setTitle("Twitter Client");

            stage.setScene(scene);
            stage.showAndWait();
    }
    
    private void showPopup() throws IOException {
        AuthenticateController controller = new AuthenticateController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Authenticate.fxml"));
        loader.setController(controller);
        
        Dialog dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        dialog.getDialogPane().setContent((Parent)loader.load());
        
        Node okBtn = dialog.getDialogPane().lookupButton(ButtonType.OK);
        //okBtn.setDisable(true);
        
        dialog.showAndWait();
        /*
        Alert alert = new Alert(Alert.AlertType.NONE, "test", ButtonType.OK);
        alert.getDialogPane().setContent((Parent)loader.load());
        alert.showAndWait();*/
    }

}
