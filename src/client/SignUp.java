package client;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SignUp
{
    SignUp(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        TextField usernamFl = new TextField();
        usernamFl.setPromptText("username");
        HBox usernameBx = new HBox( usernamFl);
        usernameBx.setPadding(new Insets(20,20,20,20));
        usernameBx.setAlignment(Pos.BOTTOM_RIGHT);

        PasswordField passFl = new PasswordField();
        passFl.setPromptText("password");
        HBox passBx = new HBox( passFl);
        passBx.setPadding(new Insets(20,20,20,20));
        passBx.setAlignment(Pos.BOTTOM_RIGHT);


        PasswordField checkPassFl = new PasswordField();
        checkPassFl.setPromptText("confirm password");
        HBox checkPassBx = new HBox( checkPassFl);
        checkPassBx.setPadding(new Insets(20,20,20,20));
        checkPassBx.setAlignment(Pos.BOTTOM_RIGHT);

        TextField phoneFl = new TextField();
        phoneFl.setPromptText("phone number");
        HBox phoneBx = new HBox( phoneFl);
        phoneBx.setPadding(new Insets(20,20,20,20));


        TextField mailFl = new TextField();
        mailFl.setPromptText("e-mail");
        HBox mailBx = new HBox( mailFl);
        mailBx.setPadding(new Insets(20,20,20,20));
        mailBx.setAlignment(Pos.BOTTOM_RIGHT);


        Button bt = new Button();
        InputStream input = new FileInputStream("./icons/button3.jpeg");
        Image background = new Image(input);
        bt.setBackground(new Background(new BackgroundImage(background , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        bt.setPadding(new Insets(10,50,10,50));
        bt.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 15));
        bt.setTranslateX(45);
        bt.setTranslateY(25);
        bt.setTextFill(Color.MIDNIGHTBLUE);

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    LogIn Lg = new LogIn(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vb = new VBox(usernameBx , mailBx , passBx , checkPassBx , phoneBx  , bt);
        vb.setLayoutX(60);
        vb.setLayoutY(90);
        vb.setSpacing(-11);

        InputStream input2 = new FileInputStream("./pics/back6.png");
        Image background2 = new Image(input2);
        ImageView backgroundView = new ImageView(background2);
        backgroundView.setLayoutX(0);
        backgroundView.setLayoutY(-90);
        backgroundView.setPreserveRatio(false);

        root.getChildren().add(backgroundView);

        root.getChildren().add(vb);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Sign up");
        primaryStage.show();
    }
}

