package client;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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

public class FrequentlyUsedAccount
{
    ArrayList<String> saveCard = new ArrayList<String>();
    ArrayList<String> saveAlias = new ArrayList<String>();
    int i =0 ;


    public FrequentlyUsedAccount(Stage primaryStage)throws FileNotFoundException
    {
        Group root = new Group();
        Scene scene = new Scene(root);

        InputStream input = getClass().getResourceAsStream("./pics/background7.jpg");
        Image background = new Image(input);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setLayoutY(0);
        backgroundView.setLayoutX(3);
        backgroundView.setFitHeight(500);
        backgroundView.setFitWidth(300);
        root.getChildren().add(backgroundView);

        Text txt = new Text("FREQUENTLY USED ACCOUNT");
        txt.setLayoutX(70);
        txt.setLayoutY(40);
        txt.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 15));
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


        Text text = new Text("selected card numbers:");
        text.setLayoutX(40);
        text.setLayoutY(140);
        text.setFill(Color.GRAY);
        root.getChildren().add(text);


        TextField scCard = new TextField();
        scCard.setLayoutY(scCard.getLayoutY()+150);
        scCard.setLayoutX(50);
        scCard.setPadding(new Insets(0,-10,0,-5));
        root.getChildren().add(scCard);
        saveCard.add(scCard.getText());


        Text text2 = new Text("alias:");
        text2.setLayoutX(200);
        text2.setLayoutY(140);
        text2.setFill(Color.GRAY);
        root.getChildren().add(text2);

        TextField alias = new TextField();
        alias.setLayoutX(180);
        alias.setLayoutY(scCard.getLayoutY());
        alias.setPadding(new Insets(0,-25,0,-20));
        root.getChildren().add(alias);
        saveAlias.add(alias.getText());




        Button nxt = new Button();
        InputStream input3 = getClass().getResourceAsStream("./icons/next.jpeg");
        Image background3 = new Image(input3);
        nxt.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        nxt.setPadding(new Insets(10,30,10,30));
        nxt.setTranslateY(scCard.getLayoutY()+250);
        nxt.setLayoutX(scCard.getLayoutX()+50);
        root.getChildren().add(nxt);

        nxt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(i<=4)
                {
                    i++;
                    TextField card2 = new TextField();
                    card2.setLayoutX(scCard.getLayoutX());
                    card2.setLayoutY(scCard.getLayoutY() + 30 * i);
                    card2.setPadding(new Insets(0,-10,0,-5));
                    root.getChildren().add(card2);
                    saveCard.add(card2.getText());


                    TextField alias2 = new TextField();
                    alias2.setLayoutX(180);
                    alias2.setLayoutY(alias.getLayoutY()+30*i);
                    alias2.setPadding(new Insets(0,-25,0,-20));
                    root.getChildren().add(alias2);
                    saveAlias.add(alias2.getText());

                }

            }
        });


        Button menu = new Button();
        InputStream input4 = getClass().getResourceAsStream("./icons/mennu.png");
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
