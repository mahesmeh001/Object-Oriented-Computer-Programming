// --== CS400 File Header Information ==--
// Name: Mehul Maheshwari
// Email: mmaheshwari2@wisc.edu
// Team: RED
// Group: KC
// TA: Keren Chen
// Lecturer:Gary Dahl
// Notes to Grader: N/A

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;

import java.awt.event.MouseEvent;
import java.util.Random;

public class DessertGame extends Application {
    private int score = 0;


    private void randomizeButtonPositions(Random r, Button[] buttons){
        for (Button button : buttons) {
            button.setLayoutX(r.nextInt(600));
            button.setLayoutY(r.nextInt(400));
        }
    }

    private void correctClicked(Random r, Button[] buttons, Label scoreLabel, Button exit){
        score++;
        scoreLabel.setText("Score: "+score);
        randomizeButtonPositions(r, buttons);
        exit.requestFocus();
    }

    private void incorrectClicked(Random r, Button[] buttons, Label scoreLabel, Button exit){
        score--;
        scoreLabel.setText("Score: "+score);
        randomizeButtonPositions(r, buttons);
        exit.requestFocus();
    }

    @Override
    public void start(final Stage stage) {
        // Step 1 & 2
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");

        // Step 3
        Label scoreLabel = new Label("Score: "+score);
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

        // Step 4
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        // TODO: Step 5-8
        //Step 5

        Button[] buttonArray=  new Button[8];
        Random r = new Random();


        //randomize when clicked
        @SuppressWarnings("unchecked")
        EventHandler<ActionEvent>[] events = (EventHandler<ActionEvent>[]) new EventHandler[] {
                event -> {correctClicked(r, buttonArray, scoreLabel, exitButton);},
                event -> {incorrectClicked(r, buttonArray, scoreLabel, exitButton);},
                event -> {incorrectClicked(r, buttonArray, scoreLabel, exitButton);},
                event -> {incorrectClicked(r, buttonArray, scoreLabel, exitButton);},
                event -> {incorrectClicked(r, buttonArray, scoreLabel, exitButton);},
                event -> {incorrectClicked(r, buttonArray, scoreLabel, exitButton);},
                event -> {incorrectClicked(r, buttonArray, scoreLabel, exitButton);},
                event -> {incorrectClicked(r, buttonArray, scoreLabel, exitButton);},
        };


        String[] labels = new String[] {"Dessert","Desert","Desert","Desert","Desert","Desert","Desert","Desert"};
        for (int i = 0; i< buttonArray.length;i++){
            Button button = new Button(labels[i]);
            button.setOnAction(events[i]);
            pane.getChildren().add(button);
            buttonArray[i] = button;
        }


        //randomize on start
        randomizeButtonPositions(r, buttonArray);


        //request focus
        exitButton.requestFocus();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}