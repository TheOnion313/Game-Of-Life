import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell {
    public static final int OFFSET = 1;
    public static final int SIZE = 10;
    private boolean alive;
    private int x, y;

    public Cell(boolean alive, int x, int y) {
        this.alive = alive;
        this.x = x;
        this.y = y;
    }

    public Cell(Cell c) {
        this.alive = c.isAlive();
        this.x = c.getX();
        this.y = c.getY();
    }

    public boolean isAlive() {
        return alive;
    }

    private void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void kill() {
        setAlive(false);
    }

    public void resurrect() {
        setAlive(true);
    }

    public void show(VBox board) {
        Color c;
        if(isAlive()) {
            c = Color.BLACK;
        } else {
            c = Color.WHITE;
        }
        board.getChildren().add(new Rectangle(SIZE, SIZE, c));
    }

    public void update(Cell[][] grid) {
        int sum = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    int locX = (i + getX() + grid.length) % grid.length;
                    int locY = (j + getY() + grid[locX].length) % grid[locX].length;
                    sum += grid[locX][locY].isAlive() ? 1 : 0;
                }
            }
        }

        if (isAlive()) {
            if (sum > 3 || sum < 2) {
                kill();
            }
        } else if (sum == 3) {
            resurrect();
        }
    }


}
