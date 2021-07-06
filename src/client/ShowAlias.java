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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.DataDealer;

public class ShowAlias {
    ShowAlias(Stage primaryStage) throws FileNotFoundException {

        Group root = new Group();
        InputStream input = new FileInputStream("./pics/background7.png");
        Image background = new Image(input);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setLayoutY(0);
        backgroundView.setLayoutX(3);
        backgroundView.setFitHeight(500);
        backgroundView.setFitWidth(300);
        root.getChildren().add(backgroundView);

        Text txt = new Text("SHOW ALIAS");
        txt.setLayoutX(70);
        txt.setLayoutY(40);
        txt.setFont(Font.font("T", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 18));
        txt.setFill(Color.WHITE);
        root.getChildren().add(txt);

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
        clock.setLayoutY(70);
        clock.setLayoutX(90);
        root.getChildren().add(clock);

        TableView table = new TableView();
        TableColumn number = new TableColumn("ID");
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn alias = new TableColumn("Alias");
        alias.setCellValueFactory(new PropertyValueFactory<>("alias"));
        TableColumn amount = new TableColumn("Balance");
        amount.setCellValueFactory(new PropertyValueFactory<>("balance"));
        DataDealer req = new DataDealer(4);
        Client.ch.send(req);
        DataDealer res = Client.ch.recieve();
        if (res.getStatus() == 204) {
            for (int i = 0; res.getData(i + "d") != null; i++) {
                table.getItems().add(new ac(res.getData(i + "d"), res.getData(i + "b"), res.getData(i + "a")));
            }
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error " + res.getStatus());
            a.setContentText(res.getError());
            a.showAndWait();
        }

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(number, alias, amount);
        VBox vb = new VBox(table);
        // vb.setPadding(new Insets(10, 30, 0, 0));
        vb.setTranslateX(30);
        vb.setTranslateY(20);
        root.getChildren().add(vb);

        Button exit = new Button();
        exit.setLayoutX(0);
        exit.setLayoutY(415);

        InputStream ex = new FileInputStream("./icons/back.png");
        Image backEx = new Image(ex);
        exit.setBackground(new Background(new BackgroundImage(backEx, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        exit.setPadding(new Insets(5, 0, 0, 25));
        exit.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 15));

        root.getChildren().add(exit);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    FrequentlyUsedAccount fu = new FrequentlyUsedAccount(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Show Alias");
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.show();

    }

    public class ac {
        private String id;
        private String balance;
        private String alias;

        /**
         * @param id
         * @param balance
         * @param alias
         */
        public ac(String id, String balance, String alias) {
            this.setId(id);
            this.setBalance(balance);
            this.setAlias(alias);
        }

        /**
         * @return the alias
         */
        public String getAlias() {
            return alias;
        }

        /**
         * @param alias the alias to set
         */
        public void setAlias(String alias) {
            this.alias = alias;
        }

        /**
         * @return the balance
         */
        public String getBalance() {
            return balance;
        }

        /**
         * @param balance the balance to set
         */
        public void setBalance(String balance) {
            this.balance = balance;
        }

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

    }
}
