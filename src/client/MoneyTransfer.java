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
import javafx.scene.control.ChoiceBox;
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

public class MoneyTransfer {
    MoneyTransfer(Stage primaryStage) throws FileNotFoundException {
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

        Text txt = new Text("MONEY TRANSFER");
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

        Text text = new Text("Source card number:");
        text.setLayoutX(80);
        text.setLayoutY(180);
        text.setFill(Color.GRAY);
        root.getChildren().add(text);

        ChoiceBox scCard = new ChoiceBox();
        scCard.setLayoutY(text.getLayoutY() + 10);
        scCard.setLayoutX(80);
        root.getChildren().add(scCard);
        DataDealer d = new DataDealer(2);
        Client.ch.send(d);
        d = Client.ch.recieve();
        if (d.getStatus() == 202) {
            String id = d.getData("0");
            for (int i = 1; id != null; i++) {
                scCard.getItems().add(id);
                id = d.getData(String.valueOf(i));
            }
        } else {
            Alert a = new Alert(AlertType.WARNING);
            a.setTitle("Warning");
            a.setContentText(d.getError());
        }
        // scCard.setPromptText("Source card");

        Text text2 = new Text("password:");
        text2.setLayoutX(80);
        text2.setLayoutY(scCard.getLayoutY() + 50);
        text2.setFill(Color.GRAY);
        root.getChildren().add(text2);

        PasswordField scPass = new PasswordField();
        scPass.setLayoutY(text2.getLayoutY() + 10);
        scPass.setLayoutX(80);
        root.getChildren().add(scPass);
        scPass.setPromptText("password");

        Text text4 = new Text("Value:");
        text4.setLayoutX(80);
        text4.setLayoutY(scPass.getLayoutY() + 50);
        text4.setFill(Color.GRAY);
        root.getChildren().add(text4);

        TextField amount = new TextField();
        amount.setLayoutY(text4.getLayoutY() + 10);
        amount.setLayoutX(80);
        root.getChildren().add(amount);
        amount.setPromptText("value to transfer");

        Text text3 = new Text("Destination card number:");
        text3.setLayoutX(80);
        text3.setLayoutY(amount.getLayoutY() + 50);
        text3.setFill(Color.GRAY);
        root.getChildren().add(text3);

        TextField dsCard = new TextField();
        dsCard.setLayoutY(text3.getLayoutY() + 10);
        dsCard.setLayoutX(80);
        root.getChildren().add(dsCard);
        dsCard.setPromptText("destination card");

        Button ok = new Button();
        ok.setTranslateX(245);
        ok.setTranslateY(230);

        InputStream input3 = new FileInputStream("./icons/next.png");
        Image background3 = new Image(input3);
        ok.setBackground(new Background(new BackgroundImage(background3, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ok.setPadding(new Insets(15, 0, 0, 30));

        root.getChildren().add(ok);

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
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (dsCard.getText().isEmpty() || scCard.getItems().isEmpty()) {

                    if (scCard.getItems().isEmpty()) {
                        scCard.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                                + "    -fx-border-style: solid;");
                    }
                    if (scPass.getText().isEmpty()) {
                        scPass.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                                + "    -fx-border-style: solid;");
                    }
                    if (dsCard.getText().isEmpty()) {
                        dsCard.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                                + "    -fx-border-style: solid;");
                    }
                    if (amount.getText().isEmpty()) {
                        amount.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                                + "    -fx-border-style: solid;");
                    }

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Required Fields Empty");
                    alert.setContentText("The fields highlighted in red must be filled " + "out.\nPlease try again.");
                    alert.showAndWait();

                    scCard.setStyle("-fx-border-color: #FFFAFA;" + "    -fx-border-width: 0px;"
                            + "    -fx-border-style: solid;");

                    scPass.setStyle("-fx-border-color: #FFFAFA;" + "    -fx-border-width: 0px;"
                            + "    -fx-border-style: solid;");

                    dsCard.setStyle("-fx-border-color: #FFFAFA;" + "    -fx-border-width: 0px;"
                            + "    -fx-border-style: solid;");
                    amount.setStyle("-fx-border-color: #FFFAFA;" + "    -fx-border-width: 0px;"
                            + "    -fx-border-style: solid;");
                } else {
                    DataDealer req = new DataDealer(8);
                    req.addData("id", (String) scCard.getValue());
                    req.addData("value", amount.getText());
                    req.addData("password", scPass.getText());
                    req.addData("dest", dsCard.getText());
                    Client.ch.send(req);
                    DataDealer res = Client.ch.recieve();
                    if (res.getStatus() == 208) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Successful");
                        alert2.setHeaderText("Successfully transfered money from " + (String) scCard.getValue() + " to "
                                + dsCard.getText());
                        alert2.setContentText("You can see the changes in the 'Account Information' section");
                        alert2.showAndWait();
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

        primaryStage.setTitle("money transfer");
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setScene(scene);
        primaryStage.show();
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
