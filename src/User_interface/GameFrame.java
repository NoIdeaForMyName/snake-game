package User_interface;

import Game.Board;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(Board board) {
        add(new GamePanel(board));
        setTitle("Snake Game by Michal Maksanty");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
