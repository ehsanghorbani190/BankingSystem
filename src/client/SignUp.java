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
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.DataDealer;

public class SignUp {
    SignUp(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        TextField usernamFl = new TextField();
        usernamFl.setPromptText("name");
        HBox usernameBx = new HBox( usernamFl);
        usernameBx.setPadding(new Insets(20,20,20,20));
        usernameBx.setAlignment(Pos.BOTTOM_RIGHT);

        TextField nlCode = new TextField();
        nlCode.setPromptText("national code");
        HBox nlBx = new HBox( nlCode);
        nlBx.setPadding(new Insets(20,20,20,20));
        nlBx.setAlignment(Pos.BOTTOM_RIGHT);

        PasswordField passFl = new PasswordField();
        passFl.setPromptText("password");
        HBox passBx = new HBox(passFl);
        passBx.setPadding(new Insets(20, 20, 20, 20));
        passBx.setAlignment(Pos.BOTTOM_RIGHT);

        PasswordField checkPassFl = new PasswordField();
        checkPassFl.setPromptText("confirm password");
        HBox checkPassBx = new HBox(checkPassFl);
        checkPassBx.setPadding(new Insets(20, 20, 20, 20));
        checkPassBx.setAlignment(Pos.BOTTOM_RIGHT);

        TextField phoneFl = new TextField();
        phoneFl.setPromptText("phone number");
        HBox phoneBx = new HBox(phoneFl);
        phoneBx.setPadding(new Insets(20, 20, 20, 20));

        TextField mailFl = new TextField();
        mailFl.setPromptText("e-mail");
        HBox mailBx = new HBox(mailFl);
        mailBx.setPadding(new Insets(20, 20, 20, 20));
        mailBx.setAlignment(Pos.BOTTOM_RIGHT);


        VBox vb = new VBox(usernameBx ,nlBx, mailBx , passBx , checkPassBx , phoneBx );
        vb.setLayoutX(60);
        vb.setLayoutY(90);
        vb.setSpacing(-11);

        InputStream input2 = new FileInputStream("./pics/back6.png");
        Image background2 = new Image(input2);
        ImageView backgroundView = new ImageView(background2);
        backgroundView.setLayoutX(-5);
        backgroundView.setLayoutY(-90);
        backgroundView.setPreserveRatio(false);

        root.getChildren().add(backgroundView);

        Button exit = new Button();
        exit.setLayoutX(20);
        exit.setLayoutY(235);

        InputStream ex = new FileInputStream("./icons/back.png");
        Image backEx = new Image(ex);
        exit.setBackground(new Background(new BackgroundImage(backEx , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        exit.setPadding(new Insets(15,0,0,30));
        exit.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 15));

        root.getChildren().add(exit);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    LogIn lg = new LogIn(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        Button bt = new Button();
        InputStream input = new FileInputStream("./icons/next.png");
        Image background = new Image(input);
        bt.setBackground(new Background(new BackgroundImage(background , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        bt.setPadding(new Insets(15,0,0,30));
        bt.setTranslateX(257);
        bt.setTranslateY(240);
        root.getChildren().add(bt);

        bt.setOnAction((ActionEvent e )-> {


            if  (usernamFl.getText().isEmpty() || passFl.getText().isEmpty() || mailFl.getText().isEmpty() || phoneFl.getText().isEmpty() || checkPassFl.getText().isEmpty() || nlCode.getText().isEmpty())
            {

                if (usernamFl.getText().isEmpty()) {
                    usernamFl.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                            + "    -fx-border-style: solid;");
                }

                if (nlCode.getText().isEmpty())
                {
                    nlCode.setStyle("-fx-border-color: #800000;" +
                            "    -fx-border-width: 1px;" +
                            "    -fx-border-style: solid;");
                }
                if(passFl.getText().isEmpty())
                {
                    passFl.setStyle("-fx-border-color: #800000;" +
                            "    -fx-border-width: 1px;" +
                            "    -fx-border-style: solid;");
                }
                if (checkPassFl.getText().isEmpty()) {
                    checkPassFl.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                            + "    -fx-border-style: solid;");
                }
                if (mailFl.getText().isEmpty()) {
                    mailFl.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                            + "    -fx-border-style: solid;");
                }
                if (phoneFl.getText().isEmpty()) {
                    phoneFl.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                            + "    -fx-border-style: solid;");
                }

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Required Fields Empty");
                alert.setContentText("The fields highlighted in red must be filled " + "out.\nPlease try again.");
                alert.showAndWait();

                usernamFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");

                nlCode.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");

                passFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");
                checkPassFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");
                mailFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");
                phoneFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");
            }
            else
            {
                if(passFl.getText().compareTo(checkPassFl.getText())!=0)
                {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Passwords don't match!");
                    alert.setContentText("The confirmm password confirmation does not match " + "\nPlease try again.");
                    alert.showAndWait();
                } else {
                    DataDealer req = new DataDealer(1);
                    req.addData("melliCode", nlCode.getText());
                    req.addData("password", passFl.getText());
                    req.addData("name", usernamFl.getText());
                    req.addData("email", mailFl.getText());
                    req.addData("phone", phoneFl.getText());
                    Client.ch.send(req);
                    DataDealer res = Client.ch.recieve();
                    if (res.getStatus() == 201) {
                        primaryStage.close();
                        try {
                            UserPanel up = new UserPanel(primaryStage);
                        } catch (FileNotFoundException problem) {
                            problem.printStackTrace();
                        }
                    } else {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setTitle("Error");
                        a.setHeaderText("Error " + res.getStatus());
                        a.setContentText(res.getError());
                        a.showAndWait();
                    }
                }
            }

        });




        root.getChildren().add(vb);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Sign up");
        primaryStage.show();

        Text clock = new Text();
        DateFormat format = DateFormat.getInstance();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
