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
import javafx.scene.control.PasswordField;
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

public class OpenAccount
{

    OpenAccount(Stage primaryStage) throws FileNotFoundException {
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

        Text txt = new Text("OPEN ACCOUNT");
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

        Text text = new Text("Types of bank accounts:");
        text.setLayoutX(80);
        text.setLayoutY(140);
        text.setFill(Color.BLACK);
        root.getChildren().add(text);

        ChoiceBox chb = new ChoiceBox();
        chb.getItems().add("قرض الحسنه پس انداز");
        chb.getItems().add("قرض الحسنه جاری    ");
        chb.getItems().add("سپرده کوتاه مدت    ");
        chb.getItems().add("سپرده بلند مدت     ");
        chb.getItems().add("سپرده طرح دار      ");
        chb.setLayoutY(160);
        chb.setLayoutX(80);
        root.getChildren().add(chb);


        Text tx = new Text("Password : ");
        tx.setLayoutX(80);
        tx.setLayoutY(220);
        tx.setFill(Color.BLACK);
        root.getChildren().add(tx);




        PasswordField passFl = new PasswordField();
        passFl.setPromptText("password");
        passFl.setLayoutX(80);
        passFl.setLayoutY(230);
        root.getChildren().add(passFl);





        Text tx2 = new Text("Confirm password : ");
        tx2.setLayoutX(80);
        tx2.setLayoutY(290);
        tx2.setFill(Color.BLACK);
        root.getChildren().add(tx2);
        //passBx.


        PasswordField checkPassFl = new PasswordField();
        checkPassFl.setPromptText("confirm password");
        checkPassFl.setLayoutY(310);
        checkPassFl.setLayoutX(80);
        root.getChildren().add(checkPassFl);


        Button ok = new Button();
        InputStream input3 = new FileInputStream("./icons/okPadding.png");
        Image background3 = new Image(input3);
        ok.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ok.setPadding(new Insets(10,15,10,15));
        ok.setTranslateY(checkPassFl.getLayoutY()+50);
        ok.setLayoutX(checkPassFl.getLayoutX()+50);
        root.getChildren().add(ok);


        Button menu = new Button();
        InputStream input4 = new FileInputStream("./icons/mennu.png");
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

    }


}

