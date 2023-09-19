package Java2D;

import Abstract.Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * This class will make it possible to press keys on
 * our keyboard and make it do something in our game.
 * @author Weng Chiew Ma
 */
public class Java2DInput extends Input {

    public Java2DInput(Java2DGraphics gr) {
        gr.getFrame().addKeyListener(new KeyInputAdapter());
        keyInputs = new LinkedList<Inputs>();
    }

    /**
     * This method checks if there is any keyInput pressed.
     * returns true if yes and false if not.
     * @return boolean true if there is an input and false if there is nothing.
     */
    public boolean inputAvailable() {
        return keyInputs.size() > 0;
    }

    /**
     * This method will return the current input key.
     * @return keyInputs.poll() This is a poll to the current keyInput.
     */
    public Inputs getInput() {
        return keyInputs.poll();
    }

    /**
     * This method will check if there is any key pressed and
     * if the specific key is pressed then this will be added.
     * leftarrow = player goes left, rightarrow = player goes right
     * space=  player shoot bullet, escape= pause, S = play game and play again
     * Q = quit game.
     */
    class KeyInputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    keyInputs.add(Inputs.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    keyInputs.add(Inputs.RIGHT);
                    break;
                case KeyEvent.VK_SPACE:
                    keyInputs.add(Inputs.SPACE);
                    break;
                case KeyEvent.VK_ESCAPE:
                    keyInputs.add(Inputs.ESCAPE);
                    break;
                case KeyEvent.VK_Q:
                    keyInputs.add(Inputs.Q);
                    break;
                case KeyEvent.VK_S:
                    keyInputs.add(Inputs.S);
                    break;
                default:
                    break;
            }
        }
    }
}
