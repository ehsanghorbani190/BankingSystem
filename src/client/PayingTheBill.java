package client;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
import javafx.util.Duration;

public class PayingTheBill
{
    PayingTheBill(Stage primaryStage) throws FileNotFoundException {

        Group root = new Group();
        Scene scene = new Scene(root);

        InputStream input = new FileInputStream("./pics/background7.png");
        Image background = new Image(input);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setLayoutY(0);
        backgroundView.setLayoutX(3);
        backgroundView.setFitHeight(500);
        backgroundView.setFitWidth(300);
        root.getChildren().add(backgroundView);

        Text txt = new Text("PAYING THE BILL");
        txt.setLayoutX(80);
        txt.setLayoutY(40);
        txt.setFont(Font.font("T", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 18));
        txt.setFill(Color.WHITE);
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



        Text text = new Text("Source card number:");
        text.setLayoutX(80);
        text.setLayoutY(180);
        text.setFill(Color.GRAY);
        root.getChildren().add(text);


        ChoiceBox scCard = new ChoiceBox();
        scCard.setLayoutY(190);
        scCard.setLayoutX(80);
        root.getChildren().add(scCard);



        Text text2 = new Text("Billing code :");
        text2.setLayoutX(80);
        text2.setLayoutY(240);
        text2.setFill(Color.GRAY);
        root.getChildren().add(text2);


        TextField bId = new TextField();
        bId.setPromptText("billing code");
        bId.setLayoutY(250);
        bId.setLayoutX(80);
        root.getChildren().add(bId);

        Text text3 = new Text("Payment code :");
        text3.setLayoutX(80);
        text3.setLayoutY(290);
        text3.setFill(Color.GRAY);
        root.getChildren().add(text3);

        TextField pCode = new TextField();
        pCode.setPromptText("payment code");
        pCode.setLayoutY(300);
        pCode.setLayoutX(80);
        root.getChildren().add(pCode);

        Button ok = new Button();
        ok.setTranslateX(245);
        ok.setTranslateY(230);

        InputStream input3 = new FileInputStream("./icons/next.png");
        Image background3 = new Image(input3);
        ok.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ok.setPadding(new Insets(15,0,0,30));



        root.getChildren().add(ok);


        Button menu = new Button();
        InputStream input4 = new FileInputStream("./icons/mennu.png");
        Image background4 = new Image(input4);
        menu.setBackground(new Background(new BackgroundImage(background4 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        menu.setPadding(new Insets(15,10,0,25));
        menu.setTranslateY(0);
        menu.setLayoutX(267);
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

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(pCode.getText().isEmpty()|| bId.getText().isEmpty())
                {

                    if(pCode.getText().isEmpty())
                    {
                        pCode.setStyle("-fx-border-color: #800000;" +
                                "    -fx-border-width: 1px;" +
                                "    -fx-border-style: solid;");
                    }
                    if(bId.getText().isEmpty())
                    {
                        bId.setStyle("-fx-border-color: #800000;" +
                                "    -fx-border-width: 1px;" +
                                "    -fx-border-style: solid;");
                    }


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Required Fields Empty");
                    alert.setContentText("The fields highlighted in red must be filled "
                            + "out.\nPlease try again.");
                    alert.showAndWait();

                    bId.setStyle("-fx-border-color: #FFFAFA;" +
                            "    -fx-border-width: 0px;" +
                            "    -fx-border-style: solid;");

                    pCode.setStyle("-fx-border-color: #FFFAFA;" +
                            "    -fx-border-width: 0px;" +
                            "    -fx-border-style: solid;");
                }
                else
                {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Successfuly");
                    alert2.setHeaderText("Successful paying the bill");
                    alert2.setContentText("You can see the changes in the 'Account Information' section");
                    alert2.showAndWait();
                }


            }
        });



        primaryStage.setTitle("paying the bill");
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.show();

        Text clock = new Text();
        DateFormat format = DateFormat.getInstance();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Calendar cal = Calendar.getInstance();
                clock.setText(format.format(cal.getTime()));
                clock.setFill(Color.BLACK);
                clock.setFont(Font.font("T", FontWeight.BOLD, FontPosture.ITALIC, 14));

            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



        VBox vx = new VBox(clock);
        vx.setLayoutX(90);
        vx.setLayoutY(50);
        root.getChildren().add(vx);

    }
}
