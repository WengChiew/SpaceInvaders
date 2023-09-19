package Abstract;

import java.util.LinkedList;

/**
 * The abstract class of Input
 * This class will make it possible to press keys on
 * our keyboard and make it do something in our game.
 * leftarrow = player goes left, rightarrow = player goes right
 * space=  player shoot bullet, escape= pause, S = play game and play again
 * Q = quit game.
 * @author Weng Chiew Ma
 */
public abstract class Input {
    public enum Inputs {LEFT, RIGHT, SPACE, ESCAPE, Q, S};
    public LinkedList<Inputs> keyInputs;

    public abstract boolean inputAvailable();
    public abstract Inputs getInput();

}
