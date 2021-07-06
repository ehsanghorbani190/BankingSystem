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

public class CloseAccount {
    CloseAccount(Stage primaryStage) throws FileNotFoundException {
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

        Text txt = new Text("CLOSE ACCOUNT");
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

        Text text = new Text("Selected card number:");
        text.setLayoutX(80);
        text.setLayoutY(130);
        text.setFill(Color.GRAY);
        root.getChildren().add(text);

        ChoiceBox scCard = new ChoiceBox();
        scCard.setLayoutY(150);
        scCard.setLayoutX(100);
        scCard.setPadding(new Insets(0, 30, -5, 30));
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
        Text text2 = new Text("Password :");
        text2.setLayoutX(80);
        text2.setLayoutY(195);
        text2.setFill(Color.GRAY);
        root.getChildren().add(text2);

        PasswordField pass = new PasswordField();
        pass.setPromptText("password");
        pass.setLayoutY(210);
        pass.setLayoutX(80);
        root.getChildren().add(pass);

        Text text3 = new Text(
                "\t  If the account is not empty\n,\t transfer the current balance\n to the card whose number you enter!");
        text3.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 10));
        text3.setLayoutX(75);
        text3.setLayoutY(340);
        text3.setFill(Color.RED);
        root.getChildren().add(text3);

        Text text4 = new Text("Destination card number :");
        text4.setLayoutX(80);
        text4.setLayoutY(270);
        text4.setFill(Color.GRAY);
        root.getChildren().add(text4);

        TextField card = new TextField();
        card.setPromptText("destination card");
        card.setLayoutY(280);
        card.setLayoutX(80);
        root.getChildren().add(card);

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

                if (pass.getText().isEmpty() || card.getText().isEmpty() || scCard.getItems().isEmpty()) {
                    if (pass.getText().isEmpty()) {
                        pass.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                                + "    -fx-border-style: solid;");
                    }
                    if (card.getText().isEmpty()) {
                        card.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                                + "    -fx-border-style: solid;");
                    }
                    if (scCard.getItems().isEmpty()) {
                        scCard.setStyle("-fx-border-color: #800000;" + "    -fx-border-width: 1px;"
                                + "    -fx-border-style: solid;");
                    }

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Required Fields Empty");
                    alert.setContentText("The fields highlighted in red must be filled " + "out.\nPlease try again.");
                    alert.showAndWait();

                    pass.setStyle("-fx-border-color: #FFFAFA;" + "    -fx-border-width: 0px;"
                            + "    -fx-border-style: solid;");
                    card.setStyle("-fx-border-color: #FFFAFA;" + "    -fx-border-width: 0px;"
                            + "    -fx-border-style: solid;");
                } else {
                    DataDealer req = new DataDealer(11);
                    req.addData("id", (String) scCard.getValue());
                    req.addData("password", pass.getText());
                    req.addData("dest", card.getText());
                    Client.ch.send(req);
                    DataDealer res = Client.ch.recieve();
                    if(res.getStatus() == 2011){
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Successful");
                        a.setHeaderText("Successfully closed account");
                        a.show();
                        pass.clear();
                        card.clear();
                    }else{
                        Alert a = new Alert(AlertType.ERROR);
                        a.setTitle("Error");
                        a.setHeaderText("Error " + res.getStatus());
                        a.setContentText(res.getError());
                        a.showAndWait();
                    }
                    
                }

            }
        });

        primaryStage.setTitle("close account");
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

        VBox vx = new VBox(clock);
        vx.setLayoutX(90);
        vx.setLayoutY(50);
        root.getChildren().add(vx);
    }
}
