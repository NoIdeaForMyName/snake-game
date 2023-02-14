package Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Snake {

    private final Board board;
    private int pos_x;
    private int pos_y;
    private int temp_x = 0;
    private int temp_y = 0;
    private char last_move;
    protected final ArrayList<int[]> body = new ArrayList<>();
    private int score = 0;

    public Snake(Board board) {

        this.board = board;
        pos_x = board.dim_x/2;
        pos_y = board.dim_y/2;
        body.add(new int[]{pos_x, pos_y});
    }

    public void move(char dir) {

        if (dir == 'r' && last_move != 'l') {

            last_move = 'r';
            temp_x = 1;
            temp_y = 0;
        }
        else if (dir == 'l' && last_move != 'r') {

            last_move = 'l';
            temp_x = -1;
            temp_y = 0;
        }


        if (dir == 'u' && last_move != 'd') {

            last_move = 'u';
            temp_y = -1;
            temp_x = 0;
        }
        else if (dir == 'd' && last_move != 'u'){

            last_move = 'd';
            temp_y = 1;
            temp_x = 0;
        }

        pos_x += temp_x;
        pos_y += temp_y;

        if (pos_x > board.dim_x)
            pos_x = 0;

        else if (pos_x < 0)
            pos_x = board.dim_x;

        else if (pos_y < 0)
            pos_y = board.dim_y;

        else if (pos_y > board.dim_y)
            pos_y = 0;
    }

    public boolean collision() {

        int[] head = body.get(0);

        for (int i = 1; i < body.size(); i++) {

            if (Arrays.equals(body.get(i), head))
                return true;
        }

        return false;
    }

    public void grow() {
        body.add(body.get(body.size()-1));
        score++;
    }

    public boolean ateApple() {
        return pos_x == board.apple.x && pos_y == board.apple.y;
    }

    public void refresh() {

        for (int i = body.size()-1; i > 0; i--) {

           body.set(i, body.get(i-1));
        }
        body.set(0, new int[]{pos_x, pos_y});
    }

    public ArrayList<int[]> getBody() {
        return body;
    }

    public int getScore() {
        return score;
    }

}
