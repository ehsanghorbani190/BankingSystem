package client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ViewAccountInformation
{

    public ViewAccountInformation(Stage primaryStage)throws FileNotFoundException
    {
        Group root = new Group();
        Scene scene = new Scene(root);

        InputStream input = new FileInputStream("src\\main\\java\\client\\background7.jpg");
        Image background = new Image(input);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setLayoutY(0);
        backgroundView.setLayoutX(3);
        backgroundView.setFitHeight(500);
        backgroundView.setFitWidth(300);
        root.getChildren().add(backgroundView);

        Text txt = new Text("ACCOUNT INFORMATION");
        txt.setLayoutX(60);
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


        TableView table = new TableView();

        TableColumn type = new TableColumn("Account type");
        TableColumn inventory = new TableColumn("Inventory");
        TableColumn lot = new TableColumn("transactions");

        table.getColumns().addAll(type, inventory, lot);
        VBox vb = new VBox(table);
        vb.setPadding(new Insets(140, 0, 30, 30));
        vb.setPrefHeight(200);
        root.getChildren().add(vb);


        Button menu = new Button();
        InputStream input4 = new FileInputStream("src\\main\\java\\client\\Mennu.png");
        Image background4 = new Image(input4);
        menu.setBackground(new Background(new BackgroundImage(background4 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        menu.setPadding(new Insets(25,15,10,40));
        menu.setTranslateY(10);
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
