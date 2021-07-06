package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.HBox;
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

public class ViewAccountInformation {

    public ViewAccountInformation(Stage primaryStage) throws FileNotFoundException {
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

        Text txt = new Text("ACCOUNT INFORMATION");
        txt.setLayoutX(50);
        txt.setLayoutY(40);
        txt.setFont(Font.font("T", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15));
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

        /*
         * 
         * Text type = new Text("account type"); Text inventory = new Text("Inventory");
         * Text transaction = new Text("transactions");
         * 
         * type.setLayoutY(120); inventory.setLayoutY(120); transaction.setLayoutY(120);
         * type.setLayoutX(40); inventory.setLayoutX(130); transaction.setLayoutX(200);
         * 
         * root.getChildren().addAll(type,inventory,transaction);
         * 
         * 
         * Rectangle Trect = new Rectangle(80,250); Trect.setTranslateX(35);
         * Trect.setLayoutY(140); Trect.setFill(Color.GRAY);
         * Trect.setStroke(Color.LIGHTBLUE); root.getChildren().add(Trect);
         * 
         * 
         * Rectangle Irect = new Rectangle(80,250); Irect.setTranslateX(115);
         * Irect.setLayoutY(140); Irect.setFill(Color.GRAY);
         * Irect.setStroke(Color.LIGHTBLUE); root.getChildren().add(Irect);
         * 
         * 
         * Rectangle TRrect = new Rectangle(80,250); TRrect.setTranslateX(195);
         * TRrect.setLayoutY(140); TRrect.setFill(Color.GRAY);
         * TRrect.setStroke(Color.LIGHTBLUE); root.getChildren().add(TRrect);
         * 
         */

        Text selectCard = new Text("account:");
        selectCard.setLayoutX(80);
        selectCard.setLayoutY(130);
        root.getChildren().add(selectCard);

        ChoiceBox chb = new ChoiceBox();
        DataDealer d = new DataDealer(2);
        Client.ch.send(d);
        d = Client.ch.recieve();
        if (d.getStatus() == 202) {
            String id = d.getData("0");
            for (int i = 1; id != null; i++) {
                chb.getItems().add(id);
                id = d.getData(String.valueOf(i));
            }
        } else {
            Alert a = new Alert(AlertType.WARNING);
            a.setTitle("Warning");
            a.setContentText(d.getError());
        }
        chb.setLayoutX(150);
        chb.setLayoutY(115);

        root.getChildren().add(chb);

        Rectangle ScRect = new Rectangle();
        // ScRect.setStroke(Color.BLACK);
        ScRect.setFill(Color.LAVENDERBLUSH);
        ScRect.setLayoutY(150);
        ScRect.setLayoutX(47);
        ScRect.setWidth(220);
        ScRect.setHeight(230);
        root.getChildren().add(ScRect);

        Text alias = new Text("alias : ");
        Text aliasAmount = new Text("     ");
        HBox hb1 = new HBox(alias,aliasAmount);

        Text inventory = new Text("inventory : ");
        Text inventoryAmount = new Text("     ");
        HBox hb2 = new HBox(inventory,inventoryAmount);

        VBox vb = new VBox(hb1 , hb2);
        vb.setTranslateX(80);
        vb.setLayoutY(170);
        vb.setSpacing(20);

        root.getChildren().add(vb);


        Button show = new Button("show transaction");
        show.setTranslateY(250);
        show.setLayoutX(100);
        root.getChildren().add(show);

        Button menu = new Button();
        InputStream input4 = new FileInputStream("./icons/mennu.png");
        Image background4 = new Image(input4);
        menu.setBackground(new Background(new BackgroundImage(background4, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        menu.setPadding(new Insets(15, 10, 0, 25));
        menu.setTranslateY(0);
        menu.setLayoutX(267);
        root.getChildren().add(menu);
        
        chb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number old, Number newVal) {
                String id = (String) chb.getItems().get((Integer) newVal);
                DataDealer req = new DataDealer(3);
                req.addData("id", id);
                Client.ch.send(req);
                DataDealer res = Client.ch.recieve();
                if (res.getStatus() == 203) {
                    aliasAmount.setText((res.getData("alias") != null) ? res.getData("alias") : "No Alias is set");
                    inventoryAmount.setText(res.getData("balance"));
                } else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Error " + res.getStatus());
                    a.setContentText(res.getError());
                    a.showAndWait();
                }
            }
        });

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
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    ShowTransaction st = new ShowTransaction(primaryStage , (String) chb.getValue());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        primaryStage.setTitle("account information");

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
