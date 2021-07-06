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
import javafx.scene.layout.VBox;
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
import util.DataDealer;

public class OpenAccount {

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
        rect.setFill(new LinearGradient(0, 0, 1, 1, true, // sizing
                CycleMethod.NO_CYCLE, // cycling
                new Stop(0, Color.web("#FFFFFF")), // colors
                new Stop(1, Color.web("#F5F5F5")))// #ADD8E6
        );
        root.getChildren().add(rect);

        Text tx = new Text("* Password : ");
        tx.setLayoutX(80);
        tx.setLayoutY(150);
        tx.setFill(Color.BLACK);
        root.getChildren().add(tx);

        PasswordField passFl = new PasswordField();
        passFl.setPromptText("password");
        passFl.setLayoutX(80);
        passFl.setLayoutY(160);
        root.getChildren().add(passFl);

        Text tx2 = new Text("* Confirm password : ");
        tx2.setLayoutX(80);
        tx2.setLayoutY(220);
        tx2.setFill(Color.BLACK);
        root.getChildren().add(tx2);

        PasswordField checkPassFl = new PasswordField();
        checkPassFl.setPromptText("confirm password");
        checkPassFl.setLayoutY(230);
        checkPassFl.setLayoutX(80);
        root.getChildren().add(checkPassFl);

        Text tx3 = new Text("Alias (Optional) : ");
        tx3.setLayoutX(80);
        tx3.setLayoutY(290);
        tx3.setFill(Color.BLACK);
        root.getChildren().add(tx3);

        TextField setAlias = new TextField();
        setAlias.setPromptText("alias");
        setAlias.setLayoutY(300);
        setAlias.setLayoutX(80);
        root.getChildren().add(setAlias);

        Button menu = new Button();
        InputStream input4 = new FileInputStream("./icons/mennu.png");
        Image background4 = new Image(input4);
        menu.setBackground(new Background(new BackgroundImage(background4, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        menu.setPadding(new Insets(15, 10, 0, 25));
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

        primaryStage.setTitle("open an account");
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setScene(scene);
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

        Button ok = new Button();

        ok.setTranslateX(245);
        ok.setTranslateY(230);

        InputStream input3 = new FileInputStream("./icons/next.png");
        Image background3 = new Image(input3);
        ok.setBackground(new Background(new BackgroundImage(background3, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ok.setPadding(new Insets(15, 0, 0, 30));

        root.getChildren().add(ok);

        ok.setOnAction((ActionEvent e) -> {

            if (passFl.getText().isEmpty() || checkPassFl.getText().isEmpty()) {

                if (passFl.getText().isEmpty()) {
                    passFl.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                            + "    -fx-border-style: solid;");
                }
                if (checkPassFl.getText().isEmpty()) {
                    checkPassFl.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                            + "    -fx-border-style: solid;");
                }

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Required Fields Empty");
                alert.setContentText("The fields highlighted in red must be filled " + "out.\nPlease try again.");
                alert.showAndWait();

                checkPassFl.setStyle(
                        "-fx-border-color: #FFFAFA;" + "    -fx-border-width: 0px;" + "    -fx-border-style: solid;");

                passFl.setStyle(
                        "-fx-border-color: #FFFAFA;" + "    -fx-border-width: 0px;" + "    -fx-border-style: solid;");

            } else {
                if (passFl.getText().compareTo(checkPassFl.getText()) != 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Passwords don't match!");
                    alert.setContentText("The confirmm password confirmation does not match " + "\nPlease try again.");
                    alert.showAndWait();
                } else {
                    DataDealer req = new DataDealer(6);
                    req.addData("password", passFl.getText());
                    req.addData("alias", setAlias.getText());
                    if (setAlias.getText() == null) {
                        req.addData("fav", "false");
                    } else
                        req.addData("fav", "true");
                    Client.ch.send(req);
                    DataDealer res = Client.ch.recieve();
                    if (res.getStatus() == 206) {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setTitle("Successful");
                        a.setHeaderText("Account Created successfully");
                        a.show();
                        setAlias.clear();
                        passFl.clear();
                        checkPassFl.clear();
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

        VBox vx = new VBox(clock);
        vx.setLayoutX(90);
        vx.setLayoutY(50);
        root.getChildren().add(vx);
    }

}
