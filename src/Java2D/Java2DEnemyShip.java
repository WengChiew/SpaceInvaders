package Java2D;

import Abstract.EnemyShip;

import java.awt.*;

/**
 * This class will make enemies for our game. You need to kill these
 * enemies to get a higher score.
 * This class is the Java2d version of the abstract class EnemyShip.
 * @author Weng Chiew Ma
 */
public class Java2DEnemyShip extends EnemyShip {

    private Java2DGraphics gr;

    public Java2DEnemyShip(Java2DGraphics g){
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
        g2d.drawImage(gr.EnemyShip1Img, (int)((double)getX()*gr.size), (int)((double)getY()*gr.size), null);
    }

    /**
     * This method will be used to visualise our boss and his life points
     * @param width the width of the game to be changed
     * @param height the height of the game to be changed.
     */
    public void visualiseBoss(int width, int height){
        changeBossSize(width, height);
        Graphics2D g2d = gr.getG2d();
        g2d.drawImage(gr.BossImg, (int)((double)getX()*gr.size), (int)((double)getY()*gr.size), null);
    }
    /**
     * This method will change the size of the incoming parameters.
     * The changed sizes will then be used to visualise our entity.
     * This is necessary because the game size is different than the graphics size.
     * @param width the width of the game to be changed
     * @param height the height of the game to be changed.
     */
    public void changeSize(int width, int height) {
        gr.EnemyShip1Img = gr.resizeImage(gr.EnemyShip1Img, (int) ((double) width * gr.size), (int) ((double) height * gr.size));
    }

    public void changeBossSize(int width, int height) {
        gr.BossImg = gr.resizeImage(gr.BossImg, (int) ((double) width * gr.size), (int) ((double) height * gr.size));
    }




}
