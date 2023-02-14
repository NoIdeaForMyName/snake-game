package Game;

import java.util.ArrayList;
import java.util.Random;

public class Apple {

    private final Board board;
    private final Random random = new Random();
    protected int x;
    protected int y;

    public Apple(Board board) {

        this.board = board;

        x = random.nextInt(board.dim_x);
        y = random.nextInt(board.dim_y);
        while (x == board.dim_x/2 && y == board.dim_y/2) {
            x = random.nextInt(board.dim_x);
            y = random.nextInt(board.dim_y);
        }
    }

    public void change_coordinates() {

        boolean change = false;
        while (!change) {

            x = random.nextInt(board.dim_x);
            y = random.nextInt(board.dim_y);

            change = true;
            for (int[] segment : board.snake.body)
                if (x == segment[0] && y == segment[1]) {
                    change = false;
                    break;
                }
        }
    }

    public int[] getCoordinates() {
        return new int[]{x, y};
    }
}
