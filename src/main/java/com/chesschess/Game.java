package com.chesschess;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class Game extends Application {

    private Board board;

    /*
     * Initializes the game
     */
    @Override
    public void start(Stage stage) throws Exception {
        board = new Board();

        Scene scene = new Scene(board.createContent());
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseListener(board));
        stage.setTitle("CHeSs GamE!!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();

    }

    /*
     * Launches the game.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
