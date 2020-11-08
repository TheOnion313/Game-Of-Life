
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    static Board b;
    public static HBox board;
    static Stage window;
    public static boolean pause = true;
    public static final int WIDTH = 20,
            HEIGHT = 20,
            MIN_PACE = 10,
            MAX_PACE = 1000,
            DEFAULT_PACE = MAX_PACE,
            CONTROL_SPACING = 20;
    public static int CYCLE = MAX_PACE - DEFAULT_PACE + MIN_PACE;
    public static long lastUpdate = System.currentTimeMillis() - CYCLE;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        window = primaryStage;
        window.setTitle("Conway's Game Of Life");

        b = new Board(WIDTH, HEIGHT);

        board = new HBox();
        b.show(board);

        Button cycle = new Button();
        cycle.setText("Resume");
        cycle.setOnAction(e -> {
            Main.pause = !Main.pause;
            cycle.setText(Main.pause ? "Resume" : "Pause");
            lastUpdate = System.currentTimeMillis() - CYCLE - 1;
        });

        Slider pace = new Slider(MIN_PACE, MAX_PACE, DEFAULT_PACE);
        pace.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                CYCLE = (int) (MAX_PACE - pace.getValue() + MIN_PACE);
            }
        });

        Button reset = new Button();
        reset.setText("Reset");
        reset.setOnAction(e -> {
            b = new Board(WIDTH, HEIGHT);
            b.show(board);
        });

        VBox main = new VBox();
        main.setSpacing(10);
        main.setAlignment(Pos.CENTER);

        HBox control = new HBox(new Rectangle(0, 0), cycle, pace, reset);
        control.setSpacing(CONTROL_SPACING);

        main.getChildren().addAll(control, board);

        int width = Board.SPACING + (Cell.SIZE + Board.SPACING) * WIDTH;
        int height = Cell.SIZE * HEIGHT + (int) (10 * main.getSpacing());
        Scene scene = new Scene(main, width, height);

        window.setScene(scene);

        Timeline timeline =
                new Timeline(new KeyFrame(Duration.millis(MIN_PACE), e -> {

                    if (!pause && System.currentTimeMillis() - lastUpdate - CYCLE >= 0) {
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
