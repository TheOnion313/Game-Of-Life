import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Random;

public class Board {
    static Random r = new Random();
    private Cell[][] board;

    public Board(Cell[][] c) {
        this.board = new Cell[c.length][c[0].length];
        System.arraycopy(c, 0, this.board, 0, c.length);
    }

    public Board(int w, int h) {
        this.board = new Cell[w][h];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.board[i][j] = new Cell(r.nextBoolean(), i, j);
            }
        }
    }

    public void update() {
        for (Cell[] col : board) {
            for (Cell c : col) {
                c.update(board);
            }
        }
    }

    public void show(HBox h) {
        for(Cell[] col : board) {
            VBox v = new VBox();
            for(Cell c : col) {
                c.show(v);
            }
            h.getChildren().add(v);
        }
    }
}
