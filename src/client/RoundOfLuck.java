package client;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class RoundOfLuck {
    Group root = new Group();
    int i=1;
  RoundOfLuck(Stage primaryStage) throws FileNotFoundException {




      InputStream input = new FileInputStream("./pics/background7.png");
      Image background = new Image(input);
      ImageView backgroundView = new ImageView(background);
      backgroundView.setLayoutY(0);
      backgroundView.setLayoutX(3);
      backgroundView.setFitHeight(500);
      backgroundView.setFitWidth(300);
      root.getChildren().add(backgroundView);



      Text txt = new Text("LUCKY WHEEL");
      txt.setLayoutX(100);
      txt.setLayoutY(40);
      txt.setFont(Font.font("T", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 18));
      txt.setFill(Color.WHITE);
      root.getChildren().add(txt);




      Circle circle1 = new Circle();
      circle1.setCenterX(155);
      circle1.setCenterY(200);
      circle1.setFill(Color.WHITE);
      circle1.setRadius(100);
      root.getChildren().add(circle1);

      Circle circle2 = new Circle();
     // circle2.setCenterX(155);
    //  circle2.setCenterY(200);
      circle2.setFill(Color.SKYBLUE);
      circle2.setRadius(60);


      Circle circle3 = new Circle();
      circle3.setFill(Color.DARKBLUE);
      circle3.setRadius(5);
      //circle3.setCenterX(180);
      //circle3.setCenterY(220);




      InputStream input2 = new FileInputStream("./icons/9.png");
      Image background2 = new Image(input2);
      ImageView backgroundView2 = new ImageView(background2);
      backgroundView2.setLayoutY(100);
      backgroundView2.setLayoutX(80);
      root.getChildren().add(backgroundView2);

      ImageView backgroundView3 = new ImageView(background2);
      backgroundView3.setLayoutY(90);
      backgroundView3.setLayoutX(130);
      root.getChildren().add(backgroundView3);

      ImageView backgroundView4 = new ImageView(background2);
      backgroundView4.setLayoutY(100);
      backgroundView4.setLayoutX(185);
      root.getChildren().add(backgroundView4);

      ImageView backgroundView5 = new ImageView(background2);
      backgroundView5.setLayoutY(140);
      backgroundView5.setLayoutX(220);
      root.getChildren().add(backgroundView5);

      ImageView backgroundView6 = new ImageView(background2);
      backgroundView6.setLayoutY(140);
      backgroundView6.setLayoutX(45);
      root.getChildren().add(backgroundView6);

      ImageView backgroundView7 = new ImageView(background2);
      backgroundView7.setLayoutY(190);
      backgroundView7.setLayoutX(40);
      root.getChildren().add(backgroundView7);

      ImageView backgroundView8 = new ImageView(background2);
      backgroundView8.setLayoutY(190);
      backgroundView8.setLayoutX(230);
      root.getChildren().add(backgroundView8);

      ImageView backgroundView9 = new ImageView(background2);
      backgroundView9.setLayoutY(240);
      backgroundView9.setLayoutX(215);
      root.getChildren().add(backgroundView9);

      ImageView backgroundView10 = new ImageView(background2);
      backgroundView10.setLayoutY(240);
      backgroundView10.setLayoutX(55);
      root.getChildren().add(backgroundView10);

      ImageView backgroundView11 = new ImageView(background2);
      backgroundView11.setLayoutY(270);
      backgroundView11.setLayoutX(105);
      root.getChildren().add(backgroundView11);

      ImageView backgroundView12 = new ImageView(background2);
      backgroundView12.setLayoutY(270);
      backgroundView12.setLayoutX(165);
      root.getChildren().add(backgroundView12);


      HBox hb = new HBox(circle2,circle3);
      hb.setLayoutX(95);
      hb.setLayoutY(142);
      hb.setSpacing(-10);
      root.getChildren().add(hb);


      Button ok = new Button();
      InputStream input3 = new FileInputStream("./icons/ok.png");
      Image background3 = new Image(input3);
      ok.setBackground(new Background(new BackgroundImage(background3 , BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
      ok.setPadding(new Insets(10,30,10,40));
      ok.setTranslateY(hb.getLayoutY()+200);
      ok.setLayoutX(hb.getLayoutX()+30);
      root.getChildren().add(ok);



      Timeline t = new Timeline(new KeyFrame(new Duration(50),
              new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event)
                  {
                      i+=5;
                      hb.setRotate(5*i);
                  }
              }));
      t.setCycleCount(Timeline.INDEFINITE);
      t.play();



      ok.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              t.stop();
              Random rand = new Random();
              Text tt = new Text();
              int randInt = rand.nextInt(5)+1;
              switch (randInt)
              {
                  case 1 : {
                      tt.setText("empty");
                      break;
                  }
                  case 2 :
                  {
                      tt.setText("5 $");
                      break;
                  }
                  case 3 :
                  {
                      tt.setText("10 $");
                      break;
                  }
                  case 4 :
                  {
                      tt.setText("100 $");
                      break;
                  }
                  case 5 :
                  {
                      tt.setText("empty");
                      break;
                  }


              }
              tt.setLayoutY(205);
              tt.setLayoutX(130);
              tt.setFont(Font.font("T", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 18));
              root.getChildren().add(tt);

          }
      });
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
      Scene sc = new Scene(root);
      primaryStage.setScene(sc);
      primaryStage.setTitle("Lucky Wheel");
      primaryStage.setHeight(480);
      primaryStage.setWidth(320);
      primaryStage.show();

      Text clock = new Text();
      DateFormat format = DateFormat.getInstance();
      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event)
          {
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
