package client;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Client extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        Group root = new Group();
        primaryStage.setScene(new Scene(root, 600, 600));
        LogIn lg = new LogIn(primaryStage);
        primaryStage.show();
    }
}