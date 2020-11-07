
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class Main extends Application {

    Button button;
    static Stage window;
    public static final int WIDTH = 20,
            HEIGHT = 20;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Hello World!");

        HBox board = new HBox();

        Board b = new Board(WIDTH, HEIGHT);

        b.show(board);

        int width = Cell.SIZE * WIDTH;
        int height = Cell.SIZE * HEIGHT;
        Scene scene = new Scene(board, width, height);

        window.setScene(scene);
        window.show();


    }


}
