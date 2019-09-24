
package com.dawsoncollege.twitterclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author 1742811
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXML.fxml"));

            Parent root = loader.load();
            com.dawsoncollege.twitterclient.controller.FXMLController controller = loader.getController();

            Scene scene = new Scene(root);

            primaryStage.setTitle("Twitter Client");

            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (IOException | IllegalStateException ex) {

            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);

            // See code samples for displaying an Alert box if an exception is thrown
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
