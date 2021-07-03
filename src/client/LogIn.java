package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LogIn {


    LogIn(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();



        TextField usernamFl = new TextField();
        usernamFl.setPromptText("username");
        HBox usernameBx = new HBox( usernamFl);
        usernameBx.setPadding(new Insets(10,10,10,10));
        usernameBx.setAlignment(Pos.BOTTOM_RIGHT);


        PasswordField passFl = new PasswordField();

        passFl.setPromptText("password");
        HBox passBx = new HBox( passFl);
        passBx.setPadding(new Insets(10,10,10,10));
        passBx.setAlignment(Pos.CENTER);

        InputStream input = new FileInputStream("./icons/b2.png");
        Image background = new Image(input);

        Button bt = new Button();
        bt.setBackground(new Background(new BackgroundImage(background , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        bt.setPadding(new Insets(10,75,10,75));
        bt.setTranslateY(30);
        bt.setTranslateX(5);
        bt.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        bt.setTextFill(Color.KHAKI);


        InputStream input3 = new FileInputStream("./icons/button4.png");
        Image background3 = new Image(input3);
        Button bt2 = new Button();
        bt2.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        bt2.setPadding(new Insets(10,75,10,75));
        bt2.setTranslateY(30);
        bt2.setTranslateX(5);
        bt2.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        bt2.setTextFill(Color.KHAKI);

        VBox vb = new VBox(usernameBx , passBx , bt , bt2);
        vb.setLayoutX(80);
        vb.setLayoutY(204);
        vb.setSpacing(-3);

        InputStream input2 = new FileInputStream("./pics/background0.png");
        Image background2 = new Image(input2);
        ImageView backgroundView = new ImageView(background2);
        backgroundView.setLayoutX(-40);
        backgroundView.setLayoutY(20);
        backgroundView.setPreserveRatio(false);

        root.getChildren().add(backgroundView);

        root.getChildren().add(vb);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setHeight(480);
        primaryStage.setWidth(320);
        primaryStage.setResizable(false);
        primaryStage.show();


        bt2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.close();
                    SignUp sg = new SignUp(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        bt.setOnAction((ActionEvent e )-> {


            if  (usernamFl.getText().isEmpty() | passFl.getText().isEmpty() )
            {
                if (usernamFl.getText().isEmpty()) {

                    usernamFl.setStyle("-fx-border-color: #800000;" +
                            "    -fx-border-width: 1px;" +
                            "    -fx-border-style: solid;");
                }
                if(passFl.getText().isEmpty()) {

                    passFl.setStyle("-fx-border-color: #800000;" +
                            "    -fx-border-width: 1px;" +
                            "    -fx-border-style: solid;");
                }

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Required Fields Empty");
                alert.setContentText("The fields highlighted in red must be filled "
                        + "out.\nPlease try again.");
                alert.showAndWait();


                usernamFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");

                passFl.setStyle("-fx-border-color: #FFFAFA;" +
                        "    -fx-border-width: 0px;" +
                        "    -fx-border-style: solid;");
            }
            else
            {
                primaryStage.close();
                 try {
                  UserPanel up = new UserPanel(primaryStage);
                     } catch (FileNotFoundException problem) {
                         problem.printStackTrace();
                    }
            }
        });

    primaryStage.setTitle("login");

    }
}
