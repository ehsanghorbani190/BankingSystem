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
import javafx.scene.control.PasswordField;
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

public class OpenAccount
{

    OpenAccount(Stage primaryStage) throws FileNotFoundException {
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

        Text txt = new Text("OPEN ACCOUNT");
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

        Text text = new Text("Types of bank accounts:");
        text.setLayoutX(80);
        text.setLayoutY(140);
        text.setFill(Color.BLACK);
        root.getChildren().add(text);

        ChoiceBox chb = new ChoiceBox();
        chb.getItems().add("قرض الحسنه پس انداز");
        chb.getItems().add("قرض الحسنه جاری    ");
        chb.getItems().add("سپرده کوتاه مدت    ");
        chb.getItems().add("سپرده بلند مدت     ");
        chb.getItems().add("سپرده طرح دار      ");
        chb.setLayoutY(160);
        chb.setLayoutX(80);
        root.getChildren().add(chb);


        Text tx = new Text("Password : ");
        tx.setLayoutX(80);
        tx.setLayoutY(220);
        tx.setFill(Color.BLACK);
        root.getChildren().add(tx);




        PasswordField passFl = new PasswordField();
        passFl.setPromptText("password");
        passFl.setLayoutX(80);
        passFl.setLayoutY(230);
        root.getChildren().add(passFl);





        Text tx2 = new Text("Confirm password : ");
        tx2.setLayoutX(80);
        tx2.setLayoutY(290);
        tx2.setFill(Color.BLACK);
        root.getChildren().add(tx2);
        //passBx.


        PasswordField checkPassFl = new PasswordField();
        checkPassFl.setPromptText("confirm password");
        checkPassFl.setLayoutY(310);
        checkPassFl.setLayoutX(80);
        root.getChildren().add(checkPassFl);


        Button ok = new Button();
        InputStream input3 = new FileInputStream("./icons/ok.png");
        Image background3 = new Image(input3);
        ok.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ok.setPadding(new Insets(10,30,10,40));
        ok.setTranslateY(checkPassFl.getLayoutY()+50);
        ok.setLayoutX(checkPassFl.getLayoutX()+40);
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
        ok.setOnAction((ActionEvent e )-> {


            if  (passFl.getText().isEmpty()  || checkPassFl.getText().isEmpty() || chb.getItems().isEmpty())
            {

               if(passFl.getText().isEmpty())
               {
                   passFl.setStyle("-fx-border-color: #800000;" +
                           "    -fx-border-width: 1px;" +
                           "    -fx-border-style: solid;");
               }
               if(checkPassFl.getText().isEmpty())
               {
                   checkPassFl.setStyle("-fx-border-color: #800000;" +
                           "    -fx-border-width: 1px;" +
                           "    -fx-border-style: solid;");
               }




                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Required Fields Empty");
                alert.setContentText("The fields highlighted in red must be filled "
                        + "out.\nPlease try again.");
                alert.showAndWait();

                checkPassFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");

                passFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");


            }

        });




        primaryStage.setTitle("open an account");
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setScene(scene);
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

