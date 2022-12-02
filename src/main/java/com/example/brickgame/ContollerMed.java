package com.example.brickgame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/*
1.add score functionality
2.add level wise skills
3.Maintain highscores
4.Serialization (save game functionality)
*/

public class ContollerMed implements Initializable{

    @FXML
    private AnchorPane scene;

    @FXML
    private Circle circle;

    ArrayList <Rectangle> allBricks = new ArrayList<>();

    double DeltaX =0.5;
    double DeltaY =0.5;

    private Rectangle slider;
    private Button right,left;

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(6), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            //Todo - move the ball and create collision feature
            circle.setLayoutX(circle.getLayoutX()-DeltaX);
            circle.setLayoutY(circle.getLayoutY()-DeltaY);

            checkSceneCollision();

            checkSliderCollision();

            checkBrickCollision();

            //check if any bricks are available
            if (allBricks.isEmpty()) {
                System.exit(10);
            }
        }
    }));

    public void checkSliderCollision(){
        if(circle.getBoundsInParent().intersects(slider.getBoundsInParent())){
            DeltaY = DeltaY * (-1);
        }
    }
    public void checkBrickCollision(){
        if (!allBricks.isEmpty()){
            allBricks.removeIf(component -> checkCollision(component));
        }
        else System.exit(1);
    }

    public boolean checkCollision(Rectangle currentBrick){
        if (circle.getBoundsInParent().intersects(currentBrick.getBoundsInParent())){
            double leftborder = currentBrick.getLayoutX();
            double rightborder = currentBrick.getLayoutX()+currentBrick.getWidth();
            double centre = circle.getLayoutX();
            boolean rightside =false;
            boolean leftside = false;
            boolean upside = false;
            boolean downside = false;

            if((centre<=rightborder && centre>=leftborder)) {

                rightside = circle.getLayoutX() >= ((currentBrick.getLayoutX() + currentBrick.getWidth()) + circle.getRadius());
                leftside = circle.getLayoutX() <= (currentBrick.getLayoutX() - circle.getRadius());

            } else {
                upside = circle.getLayoutY() <= (currentBrick.getLayoutY() - circle.getRadius());
                downside = circle.getLayoutY() >= (currentBrick.getLayoutY() + currentBrick.getHeight() - circle.getRadius());
            }
            if (rightside || leftside)  {DeltaX *= -1;}
            else if (upside || downside) {DeltaY *= -1;}

            scene.getChildren().remove(currentBrick);
            return true;
        }
        else {return false;}
    }

    public void checkSceneCollision(){
        Bounds bounds = scene.getBoundsInLocal();
        boolean rightside = circle.getLayoutX() >= (bounds.getMaxX()- circle.getRadius());
        boolean leftside = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
        boolean upside = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());
        boolean downside = circle.getLayoutY() >= (bounds.getMaxY()- circle.getRadius());


        if (rightside || leftside)  DeltaX *= -1;
        else if (upside) DeltaY *= -1;
        if (downside) {
            //gameover
            System.exit(1);
            allBricks.clear();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //we initialize all bricks here
        timeline.setCycleCount(Animation.INDEFINITE);
        createBricks();
        add_slider();
        add_buttons();
        timeline.play();

    }
    public void createBricks(){
        int flag = 1;
        for (int i=500;i>0;i-= 50){
            for (int j= 300;j>0;j-=30){
                if ( flag%2 == 0){
                    Rectangle rectangle = new Rectangle(i,j,40,25);
                    if (flag%3 ==0){
                        rectangle.setFill(Color.GREEN);
                    }
                    else if (flag%3==1)
                    {
                        rectangle.setFill(Color.BLUE);
                    }
                    else {
                        rectangle.setFill(Color.RED);
                    }
                    scene.getChildren().add(rectangle);
                    allBricks.add(rectangle);
                }
                flag++;
            }
            flag++;
        }

    }
    public void add_slider(){
        slider = new Rectangle(300,480,300,15);
        slider.setFill(Color.BLACK);
        scene.getChildren().add(slider);

    }
    public void add_buttons(){
        right = new Button("RIGHT");
        right.setLayoutX(450);
        right.setLayoutY(450);

        left = new Button("LEFT");
        left.setLayoutX(20);
        left.setLayoutY(450);

        right.setOnAction(moveright);
        left.setOnAction(moveleft);

        scene.getChildren().add(left);
        scene.getChildren().add(right);
    }

    EventHandler<ActionEvent> moveright = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            slider.setLayoutX(slider.getLayoutX()+50);
        }
    };
    EventHandler<ActionEvent> moveleft = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            slider.setLayoutX(slider.getLayoutX()-50);
        }
    };

}
