package client;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    public static ClientHand ch;
    public static void main(String[] args) {
        ch = new ClientHand();
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