package User_interface;

import Game.Apple;
import Game.Board;
import Game.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    private final int WIDTH = 700;
    private final int HEIGHT = WIDTH;
    private final int UNIT = WIDTH/10;
    private final int GAP = 5;
    private final int DELAY = 250;
    private Snake snake;
    private Apple apple;
    private char direction = 'a';
    private boolean running = true;
    Timer timer;
    MyListener listener = new MyListener();
    Label score = new Label("Score: 0");

    public GamePanel(Board board) {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(listener);

        snake = board.getSnake();
        apple = board.getApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void drawAll(Graphics g) {

        g.setColor(Color.green);
        for (int i = 1; i < snake.getBody().size(); i++) {

            g.fillRect(snake.getBody().get(i)[0] * UNIT + GAP, snake.getBody().get(i)[1] * UNIT + GAP, UNIT - (2 * GAP), UNIT - (2 * GAP));
            if (snake.getBody().get(i)[0] != snake.getBody().get(i-1)[0] | snake.getBody().get(i)[1] != snake.getBody().get(i-1)[1]) { // exclusion of the newly attached segment

                if (snake.getBody().get(i)[0] == snake.getBody().get(i - 1)[0]) // same x pos
                    if (Math.abs(snake.getBody().get(i)[1] - snake.getBody().get(i - 1)[1]) == 1) {
                        if (snake.getBody().get(i)[1] > snake.getBody().get(i - 1)[1]) // snake goes up
                            g.fillRect(snake.getBody().get(i)[0] * UNIT + GAP, snake.getBody().get(i)[1] * UNIT - GAP, UNIT - (2 * GAP), 2 * GAP);
                        else // snake goes down
                            g.fillRect(snake.getBody().get(i)[0] * UNIT + GAP, snake.getBody().get(i - 1)[1] * UNIT - GAP, UNIT - (2 * GAP), 2 * GAP);
                    }
                    else {// snake goes through the wall (down or up)
                        g.fillRect(snake.getBody().get(i)[0] * UNIT + GAP, 0, UNIT - (2 * GAP), GAP);
                        g.fillRect(snake.getBody().get(i)[0] * UNIT + GAP, HEIGHT - GAP, UNIT - (2 * GAP), GAP);
                    }

                else // same y pos
                    if (Math.abs(snake.getBody().get(i)[0] - snake.getBody().get(i - 1)[0]) == 1) {
                        if (snake.getBody().get(i)[0] < snake.getBody().get(i - 1)[0]) // snake goes right
                            g.fillRect(snake.getBody().get(i - 1)[0] * UNIT - GAP, snake.getBody().get(i)[1] * UNIT + GAP, 2 * GAP, UNIT - (2 * GAP));
                        else // snake goes left
                            g.fillRect(snake.getBody().get(i)[0] * UNIT - GAP, snake.getBody().get(i)[1] * UNIT + GAP, 2 * GAP, UNIT - (2 * GAP));
                    }
                    else { // snake goes through the wall (left or right)
                        g.fillRect(WIDTH - GAP, snake.getBody().get(i)[1] * UNIT + GAP, GAP, UNIT - (2 * GAP));
                        g.fillRect(0, snake.getBody().get(i)[1] * UNIT + GAP, GAP, UNIT - (2 * GAP));
                    }
            }

        }
        g.fillRect(snake.getBody().get(0)[0] * UNIT + GAP, snake.getBody().get(0)[1] * UNIT + GAP, UNIT - (2 * GAP), UNIT - (2 * GAP));

        g.setColor(Color.red);
        g.fillOval(apple.getCoordinates()[0]*UNIT, apple.getCoordinates()[1]*UNIT, UNIT, UNIT);
    }

    public void displayScore(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        FontMetrics metrics = getFontMetrics(g.getFont());
        String score_text = "Score: " + snake.getScore();
        g.drawString(score_text, (WIDTH - metrics.stringWidth(score_text))/2, g.getFont().getSize());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAll(g);
        displayScore(g);
    }

    public void gameOver() {
        System.out.println("LOLZ QUIT");
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        snake.move(listener.getPressedKey());
        snake.refresh();

        if (snake.collision())
            running = false;

        if (snake.ateApple()) {
            snake.grow();
            apple.change_coordinates();
        }

        if (!running)
            gameOver();

        repaint();

        System.out.println(snake.getBody().get(0)[0] + " " + snake.getBody().get(0)[1]);


    }

}
