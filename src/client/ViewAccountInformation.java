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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import javafx.util.Callback;

public class ViewAccountInformation
{

    public ViewAccountInformation(Stage primaryStage)throws FileNotFoundException
    {
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
        rect.setFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#FFFFFF")),     //colors
                new Stop(1, Color.web("#F5F5F5")))//  #ADD8E6
        );
        root.getChildren().add(rect);

/*
        TableView table = new TableView();

        TableColumn type = new TableColumn("Account type");
        TableColumn inventory = new TableColumn("Inventory");
        TableColumn lot = new TableColumn("transactions");

        Button bt = new Button("show");


        table.getColumns().addAll(type, inventory, lot);
        VBox vb = new VBox(table);
        vb.setPadding(new Insets(140, 0, 30, 30));
        vb.setPrefHeight(200);
        root.getChildren().add(vb);

 */


        Text type = new Text("account type");
        Text inventory = new Text("Inventory");
        Text transaction = new Text("transactions");

        type.setLayoutY(120);
        inventory.setLayoutY(120);
        transaction.setLayoutY(120);
        type.setLayoutX(40);
        inventory.setLayoutX(130);
        transaction.setLayoutX(200);

        root.getChildren().addAll(type,inventory,transaction);


        Rectangle Trect = new Rectangle(80,250);
        Trect.setTranslateX(35);
        Trect.setLayoutY(140);
        Trect.setFill(Color.GRAY);
        Trect.setStroke(Color.LIGHTBLUE);
        root.getChildren().add(Trect);


        Rectangle Irect = new Rectangle(80,250);
        Irect.setTranslateX(115);
        Irect.setLayoutY(140);
        Irect.setFill(Color.GRAY);
        Irect.setStroke(Color.LIGHTBLUE);
        root.getChildren().add(Irect);


        Rectangle TRrect = new Rectangle(80,250);
        TRrect.setTranslateX(195);
        TRrect.setLayoutY(140);
        TRrect.setFill(Color.GRAY);
        TRrect.setStroke(Color.LIGHTBLUE);
        root.getChildren().add(TRrect);


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


        primaryStage.setTitle("account information");


        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.show();
    }
}
