package GUI;

import User_interface.GameFrame;
import Game.*;

public class Game {

    public static void main(String[] args) {

        new GameFrame(new Board(10, 10));

    }
}
