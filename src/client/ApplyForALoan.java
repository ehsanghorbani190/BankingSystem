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

public class ApplyForALoan {
    ApplyForALoan(Stage primaryStage) throws FileNotFoundException {
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

        Text txt = new Text("APPLY FOR A LOAN");

        txt.setLayoutX(60);
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

        Text select = new Text("LOAN AMOUNT(In Tomans): ");
        select.setLayoutY(125);
        select.setLayoutX(50);
        // root.getChildren().add(select);

        ChoiceBox cb = new ChoiceBox();
        cb.setLayoutX(170);
        cb.setLayoutY(150);
        cb.getItems().add("100000");
        cb.getItems().add("200000");
        cb.getItems().add("300000");
        cb.getItems().add("400000");
        cb.getItems().add("500000");

        // root.getChildren().add(cb);

        Text select2 = new Text("PAYMENT PERIOD(In days) : ");
        select2.setLayoutY(260);
        select2.setLayoutX(50);
        // root.getChildren().add(select2);

        ChoiceBox cb2 = new ChoiceBox();
        cb2.setLayoutX(170);
        cb2.setLayoutY(220);
        for (int i = 1; i < 8; i++) {
            cb2.getItems().add(String.valueOf(i));
        }

        Text select3 = new Text("SOURCE CARD NUMBER : ");
        select3.setLayoutY(400);
        select3.setLayoutX(50);

        ChoiceBox cb3 = new ChoiceBox();
        cb2.setLayoutX(170);
        cb2.setLayoutY(290);
        DataDealer d = new DataDealer(2);
        Client.ch.send(d);
        d = Client.ch.recieve();
        if (d.getStatus() == 202) {
            String id = d.getData("0");
            for (int i = 1; id != null; i++) {
                cb3.getItems().add(id);
                id = d.getData(String.valueOf(i));
            }
        } else {
            Alert a = new Alert(AlertType.WARNING);
            a.setTitle("Warning");
            a.setContentText(d.getError());
        }

        // root.getChildren().add(cb2);
        // HBox hb = new HBox(select,cb , select2, cb2);

        VBox vb = new VBox(select, cb, select2, cb2, select3, cb3);
        vb.setLayoutX(100);
        vb.setLayoutY(170);
        vb.setSpacing(10);
        vb.setFillWidth(true);

        Button ok = new Button();
        ok.setTranslateX(245);
        ok.setTranslateY(230);

        InputStream input3 = new FileInputStream("./icons/next.png");
        Image background3 = new Image(input3);
        ok.setBackground(new Background(new BackgroundImage(background3, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ok.setPadding(new Insets(15, 0, 0, 30));

        root.getChildren().add(ok);
        root.getChildren().add(vb);

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
        ok.setOnAction((ActionEvent e) -> {
            DataDealer req = new DataDealer(10);
            req.addData("id", (String) cb3.getValue());
            req.addData("value", (String) cb.getValue());
            req.addData("period", (String) cb2.getValue());
            Client.ch.send(req);
            DataDealer res = Client.ch.recieve();
            if (res.getStatus() == 2010) {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Successful");
                a.setHeaderText("Loan payed Successfully");
                a.setContentText("Loan debts will be payed automatically");
                a.show();
            } else {
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error " + res.getStatus());
                a.setContentText(res.getError());
                a.showAndWait();
            }
        });
        primaryStage.setTitle("apply for a loan");
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
