package client;


import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class UserPanel
{
    UserPanel(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        InputStream input = getClass().getResourceAsStream("./pics/background7.jpg");
        Image background = new Image(input);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setLayoutY(0);
        backgroundView.setLayoutX(3);
        backgroundView.setFitHeight(500);
        backgroundView.setFitWidth(300);
        root.getChildren().add(backgroundView);

        primaryStage.setHeight(480);
        primaryStage.setWidth(320);



        Button bt1 = new Button("OPEN AN ACCOUNT");
        InputStream input2 = getClass().getResourceAsStream("./icons/1.png");
        Image background2 = new Image(input2);
        bt1.setGraphic(new ImageView(background2));
        bt1.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY , Insets.EMPTY)));
        bt1.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 12));
        bt1.setTranslateX(30);
        bt1.setTranslateY(90);
        root.getChildren().add(bt1);
        bt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    OpenAccount sg = new OpenAccount(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        Button bt2 = new Button("ACCOUNT INFORMATION");
        InputStream input3 = getClass().getResourceAsStream("./icons/2.png");
        Image background3 = new Image(input3);
        bt2.setGraphic(new ImageView(background3));
        bt2.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY , Insets.EMPTY)));
        bt2.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 12));
        bt2.setTranslateX(30);
        bt2.setTranslateY(130);

        root.getChildren().add(bt2);

        bt2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                   ViewAccountInformation vi = new ViewAccountInformation(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });



        Button bt3 = new Button("ACCOUNT MANAGEMENT");
        InputStream input4 = getClass().getResourceAsStream("./icons/3.png");
        Image background4 = new Image(input4);
        bt3.setGraphic(new ImageView(background4));
        bt3.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY , Insets.EMPTY)));
        bt3.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 12));
        bt3.setTranslateX(30);
        bt3.setTranslateY(170);

        root.getChildren().add(bt3);


        Button bt4 = new Button("FREQUENTLY USED ACCOUNT");
        InputStream input5 = getClass().getResourceAsStream("./icons/4.png");
        Image background5 = new Image(input5);
        bt4.setGraphic(new ImageView(background5));
        bt4.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY , Insets.EMPTY)));
        bt4.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 12));
        bt4.setTranslateX(30);
        bt4.setTranslateY(210);

        root.getChildren().add(bt4);

        bt4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    FrequentlyUsedAccount fru = new FrequentlyUsedAccount(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });



        Button bt5 = new Button("MONEY TRANSFER");
        InputStream input6 = getClass().getResourceAsStream("./icons/5.png");
        Image background6 = new Image(input6);
        bt5.setGraphic(new ImageView(background6));
        bt5.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY , Insets.EMPTY)));
        bt5.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 12));
        bt5.setTranslateX(30);
        bt5.setTranslateY(250);

        root.getChildren().add(bt5);
        bt5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    MoneyTransfer mt = new MoneyTransfer(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        Button bt6 = new Button("PAYING THE BILL");
        InputStream input7 = getClass().getResourceAsStream("./icons/6.png");
        Image background7 = new Image(input7);
        bt6.setGraphic(new ImageView(background7));
        bt6.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY , Insets.EMPTY)));
        bt6.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 12));
        bt6.setTranslateX(30);
        bt6.setTranslateY(290);

        root.getChildren().add(bt6);

        bt6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    PayingTheBill pb = new PayingTheBill(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        Button bt7 = new Button("APPLY FOR A LOAN");
        InputStream input8 = getClass().getResourceAsStream("./icons/7.png");
        Image background8 = new Image(input8);
        bt7.setGraphic(new ImageView(background8));
        bt7.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY , Insets.EMPTY)));
        bt7.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 12));
        bt7.setTranslateX(30);
        bt7.setTranslateY(330);

        root.getChildren().add(bt7);

        bt7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    ApplyForALoan afl = new ApplyForALoan(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        Button bt8 = new Button("CLOSE ACCOUNT");
        InputStream input9 = getClass().getResourceAsStream("./icons/8.png");
        Image background9 = new Image(input9);
        bt8.setGraphic(new ImageView(background9));
        bt8.setBackground(new Background(new BackgroundFill(Color.WHITE , CornerRadii.EMPTY , Insets.EMPTY)));
        bt8.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 12));
        bt8.setTranslateX(30);
        bt8.setTranslateY(370);

        root.getChildren().add(bt8);

        bt8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    CloseAccount ca = new CloseAccount(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

