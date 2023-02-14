package Game;

public class Board {

    protected Snake snake;
    protected Apple apple;
    protected int dim_x;
    protected int dim_y;

    public Board(int dim_x, int dim_y) {

        this.dim_x = dim_x-1; //counted from 0
        this.dim_y = dim_y-1; //counted from 0
        snake = new Snake(this);
        apple = new Apple(this);
    }

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }
}
