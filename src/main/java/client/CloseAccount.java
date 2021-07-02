package client;


import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CloseAccount
{
    CloseAccount(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root);

        InputStream input = getClass().getResourceAsStream("./pics/background7.jpg");
        Image background = new Image(input);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setLayoutY(0);
        backgroundView.setLayoutX(3);
        backgroundView.setFitHeight(500);
        backgroundView.setFitWidth(300);
        root.getChildren().add(backgroundView);

        Text txt = new Text("CLOSE ACCOUNT");
        txt.setLayoutX(80);
        txt.setLayoutY(40);
        txt.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 18));
        txt.setFill(Color.BLACK);
        root.getChildren().add(txt);


        Rectangle rect = new Rectangle();
        rect.setHeight(300);
        rect.setWidth(250);
        rect.setLayoutY(100);
        rect.setLayoutX(30);
        rect.setStroke(Color.BLACK);
        rect.setFill(Color.WHITE);
        rect.setFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#FFFFFF")),     //colors
                new Stop(1, Color.web("#F5F5F5")))//  #ADD8E6
        );
        root.getChildren().add(rect);

        Text text = new Text("Password :");
        text.setLayoutX(130);
        text.setLayoutY(140);
        text.setFill(Color.GRAY);
        root.getChildren().add(text);



        PasswordField pass = new PasswordField();
        pass.setPromptText("password");
        pass.setLayoutY(pass.getLayoutY()+150);
        pass.setLayoutX(80);
        root.getChildren().add(pass);


        Button bt = new Button();
        InputStream input2 = getClass().getResourceAsStream("./icons/okPadding.png");
        Image background2 = new Image(input2);
        bt.setBackground(new Background(new BackgroundImage(background2 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        bt.setPadding(new Insets(10,15,10,15));
        bt.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 15));
        bt.setTranslateY(pass.getLayoutY()+30);
        bt.setLayoutX(pass.getLayoutX()+50);
        root.getChildren().add(bt);


        Text text2 = new Text("If the account is not empty, transfer the current balance\n to the card whose number you enter!");
        text2.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 10));
        text2.setLayoutX(35);
        text2.setLayoutY(240);
        text2.setFill(Color.RED);
        root.getChildren().add(text2);


        Text text3 = new Text("Destination card number :");
        text3.setLayoutX(80);
        text3.setLayoutY(300);
        text3.setFill(Color.GRAY);
        root.getChildren().add(text3);



        TextField card = new TextField();
        card.setPromptText("destination card");
        card.setLayoutY(310);
        card.setLayoutX(80);
        root.getChildren().add(card);

        Button ok = new Button();
        InputStream input3 = getClass().getResourceAsStream("./icons/okPadding.png");
        Image background3 = new Image(input3);
        ok.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ok.setPadding(new Insets(10,15,10,15));
        ok.setTranslateY(card.getLayoutY()+30);
        ok.setLayoutX(card.getLayoutX()+50);
        root.getChildren().add(ok);


        Button menu = new Button();
        InputStream input4 = getClass().getResourceAsStream("./icons/mennu.png");
        Image background4 = new Image(input4);
        menu.setBackground(new Background(new BackgroundImage(background4 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        menu.setPadding(new Insets(25,15,10,40));
        menu.setTranslateY(10);
        menu.setLayoutX(0);
        root.getChildren().add(menu);
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    UserPanel up = new UserPanel(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });




        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

