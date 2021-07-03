package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

        InputStream input = new FileInputStream("./pics/background7.png");
        Image background = new Image(input);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setLayoutY(0);
        backgroundView.setLayoutX(3);
        backgroundView.setFitHeight(500);
        backgroundView.setFitWidth(300);
        root.getChildren().add(backgroundView);

        Text txt = new Text("FREQUENTLY USED ACCOUNT");
        txt.setLayoutX(30);
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


        Text text = new Text("selected card number:");
        text.setLayoutX(80);
        text.setLayoutY(190);
        text.setFill(Color.GRAY);
        root.getChildren().add(text);


        TextField scCard = new TextField();
        scCard.setLayoutY(scCard.getLayoutY()+200);
        scCard.setLayoutX(80);
        scCard.setPadding(new Insets(3,10,10,3));
        root.getChildren().add(scCard);
        saveCard.add(scCard.getText());


        Text text2 = new Text("alias:");
        text2.setLayoutX(80);
        text2.setLayoutY(245);
        text2.setFill(Color.GRAY);
        root.getChildren().add(text2);

        TextField alias = new TextField();
        alias.setLayoutX(80);
        alias.setLayoutY(scCard.getLayoutY()+50);
        alias.setPadding(new Insets(3,10,10,3));
        root.getChildren().add(alias);
        saveAlias.add(alias.getText());


        Button nxtAlias = new Button();

        InputStream input3 = new FileInputStream("./icons/alias.png");
        Image background3 = new Image(input3);
        nxtAlias.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        nxtAlias.setPadding(new Insets(10 , 30 , 10 , 40));
        nxtAlias.setLayoutX(115);
        nxtAlias.setTranslateY(280);
        root.getChildren().add(nxtAlias);

        Button show = new Button();

        InputStream input4 = new FileInputStream("./icons/show.png");
        Image background4 = new Image(input4);
        show.setBackground(new Background(new BackgroundImage(background4 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        show.setPadding(new Insets(10 , 30 , 10 , 40));
        show.setLayoutX(115);
        show.setTranslateY(310);
        root.getChildren().add(show);



        Button menu = new Button();
        InputStream input5 = new FileInputStream("./icons/mennu.png");
        Image background5 = new Image(input5);
        menu.setBackground(new Background(new BackgroundImage(background5 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        menu.setPadding(new Insets(15,10,0,25));
        menu.setTranslateY(0);
        menu.setLayoutX(267);
        root.getChildren().add(menu);


        nxtAlias.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(alias.getText().isEmpty()|| scCard.getText().isEmpty())
                {


                    if(alias.getText().isEmpty())
                    {
                        alias.setStyle("-fx-border-color: #800000;" +
                                "    -fx-border-width: 1px;" +
                                "    -fx-border-style: solid;");
                    }


                    if(scCard.getText().isEmpty())
                    {
                        scCard.setStyle("-fx-border-color: #800000;" +
                                "    -fx-border-width: 1px;" +
                                "    -fx-border-style: solid;");
                    }


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Required Fields Empty");
                    alert.setContentText("The fields highlighted in red must be filled "
                            + "out.\nPlease try again.");
                    alert.showAndWait();

                    scCard.setStyle("-fx-border-color: #FFFAFA;" +
                            "    -fx-border-width: 0px;" +
                            "    -fx-border-style: solid;");

                    alias.setStyle("-fx-border-color: #FFFAFA;" +
                            "    -fx-border-width: 0px;" +
                            "    -fx-border-style: solid;");
                }
                else {


                    TextField alias = new TextField();
                    alias.setLayoutX(80);
                    alias.setLayoutY(scCard.getLayoutY() + 50);
                    alias.setPadding(new Insets(3, 10, 10, 3));
                    root.getChildren().add(alias);

                    TextField scCard = new TextField();
                    scCard.setLayoutY(scCard.getLayoutY() + 200);
                    scCard.setLayoutX(80);
                    scCard.setPadding(new Insets(3, 10, 10, 3));
                    root.getChildren().add(scCard);


                    saveAlias.add(alias.getText());
                    saveCard.add(scCard.getText());


                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Saved!");
                    alert2.setHeaderText("The desired card number was saved.");
                    alert2.showAndWait();
                }


            }
        });


        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                for (i=0 ; i<saveCard.size() ; i++)
                {
                    System.out.println(saveCard.get(i));
                    System.out.println(saveAlias.get(i));
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





        primaryStage.setTitle("frequntly used account");
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.show();
    }
}
