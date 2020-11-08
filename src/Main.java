
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    Button button;
    public static HBox board;
    static Stage window;
    public static boolean pause = true;
    public static final int WIDTH = 20,
            HEIGHT = 20,
            CYCLE = 100;
    public static long lastUpdate = System.currentTimeMillis() - CYCLE;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        window = primaryStage;
        window.setTitle("Conway's Game Of Life");

        Board b = new Board(WIDTH, HEIGHT);

        board = new HBox();
        b.show(board);

        Button cycle = new Button();
        cycle.setMinHeight(20);
        cycle.setMaxHeight(20);
        cycle.setText("Resume");
        cycle.setOnAction(e -> {
            Main.pause = !Main.pause;
            cycle.setText(Main.pause ? "Resume" : "Pause");
            lastUpdate = System.currentTimeMillis() - CYCLE - 1;
        });

        VBox main = new VBox();
        main.setSpacing(10);
        main.setAlignment(Pos.CENTER);

        main.getChildren().addAll(cycle, board);

        int width = Cell.SIZE * WIDTH;
        int height = Cell.SIZE * HEIGHT  + (int)(10 * main.getSpacing());
        Scene scene = new Scene(main, width, height);

        window.setScene(scene);

        Timeline timeline =
                new Timeline(new KeyFrame(Duration.millis(CYCLE + 10), e -> {
                    if (!pause && (System.currentTimeMillis() - lastUpdate) - CYCLE >= 0) {
                        b.update();
                        b.show(board);
                        lastUpdate = System.currentTimeMillis();
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);

        window.show();
        timeline.play();
    }

}
