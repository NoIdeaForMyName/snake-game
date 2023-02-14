package User_interface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyListener extends KeyAdapter {

    private char pressed_key = 'a';

    public char getPressedKey() {
        return pressed_key;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            pressed_key = 'l';
        }

        else if (key == KeyEvent.VK_RIGHT)
            pressed_key = 'r';

        else if (key == KeyEvent.VK_UP)
            pressed_key = 'u';

        else if (key == KeyEvent.VK_DOWN)
            pressed_key = 'd';

    }
}
