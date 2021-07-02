package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
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

public class ApplyForALoan
{
    ApplyForALoan(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root);

        InputStream input = new FileInputStream("./pics/background7.jpg");
        Image background = new Image(input);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setLayoutY(0);
        backgroundView.setLayoutX(3);
        backgroundView.setFitHeight(500);
        backgroundView.setFitWidth(300);
        root.getChildren().add(backgroundView);

        Text txt = new Text("APPLY FOR A LOAN");
        txt.setLayoutX(80);
        txt.setLayoutY(40);
        txt.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 18));
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


        Text select = new Text("LOAN AMOUNT : ");
        select.setLayoutY(165);
        select.setLayoutX(50);
        //root.getChildren().add(select);


        ChoiceBox cb = new ChoiceBox();
        cb.setLayoutX(170);
        cb.setLayoutY(150);
        cb.getItems().add("1000 $");
        cb.getItems().add("2000 $");
        cb.getItems().add("3000 $");
        cb.getItems().add("4000 $");
        cb.getItems().add("5000 $");

        //root.getChildren().add(cb);



        Text select2 = new Text("PAYMENT PERIOD : ");
        select2.setLayoutY(260);
        select2.setLayoutX(50);
        //root.getChildren().add(select2);


        ChoiceBox cb2 = new ChoiceBox();
        cb2.setLayoutX(170);
        cb2.setLayoutY(220);
        cb2.getItems().add("1 YEAR LATER");
        cb2.getItems().add("2 YEAR LATER");
        cb2.getItems().add("3 YEAR LATER");
        cb2.getItems().add("4 YEAR LATER");
        cb2.getItems().add("5 YEAR LATER");

        // root.getChildren().add(cb2);
        // HBox  hb = new HBox(select,cb , select2, cb2);

        VBox vb = new VBox(select,cb , select2, cb2);
        vb.setLayoutX(100);
        vb.setLayoutY(200);


        Button ok = new Button();
        InputStream input3 = new FileInputStream("./icons/okPadding.png");
        Image background3 = new Image(input3);
        ok.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ok.setPadding(new Insets(10,12,10,12));
        ok.setTranslateY(300);
        ok.setLayoutX(140);
        root.getChildren().add(ok);
        root.getChildren().add(vb);


        Button menu = new Button();
        InputStream input4 = new FileInputStream("./icons/mennu.png");
        Image background4 = new Image(input4);
        menu.setBackground(new Background(new BackgroundImage(background4 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        menu.setPadding(new Insets(25,15,10,40));
        menu.setTranslateY(20);
        menu.setLayoutX(0);
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


        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.show();
    }
}
