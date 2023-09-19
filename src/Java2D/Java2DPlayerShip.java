package Java2D;

import Abstract.PlayerShip;

import java.awt.*;

/**
 * This class is the player of our game. You will use this entity
 * to shoot down enemies and try to get a high score.
 * This class is the Java2d version of the abstract class PlayerShip.
 * @author Weng Chiew Ma
 */
public class Java2DPlayerShip extends PlayerShip
{

    private Java2DGraphics gr;

    public Java2DPlayerShip(Java2DGraphics g){
        this.gr = g;
    }

    /**
     * This method will be used to visualise our entity
     * @param width the width of the game to be changed
     * @param height the height of the game to be changed.
     */
    public void visualise(int width, int height){
        changeSize(width, height);
        Graphics2D g2d = gr.getG2d();
        g2d.drawImage(gr.PlayerShipImg, (int)((double)getX()*gr.size), (int)((double)getY()*gr.size), null);
    }

    /**
     * This method will change the size of the incoming parameters.
     * The changed sizes will then be used to visualise our entity.
     * This is necessary because the game size is different than the graphics size.
     * @param width the width of the game to be changed
     * @param height the height of the game to be changed.
     */
    public void changeSize(int width, int height) {
        gr.PlayerShipImg = gr.resizeImage(gr.PlayerShipImg, (int)((double)width*gr.size), (int)((double)height*gr.size));
    }
}


