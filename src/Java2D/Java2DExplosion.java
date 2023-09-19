package Java2D;

import Abstract.Explosion;

import java.awt.*;


/**
 * This class will let us make explosions. When an enemy dies or
 * a bonus gets hit, there will be an explosion.
 * This class is the Java2d version of the abstract class explosion.
 * @author Weng Chiew Ma
 */
public class Java2DExplosion extends Explosion {
    private Java2DGraphics gr;

    public Java2DExplosion(Java2DGraphics g){
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
        g2d.drawImage(gr.ExplosionImg, (int)((double)getX()*gr.size), (int)((double)getY()*gr.size), null);
    }
    /**
     * This method will change the size of the incoming parameters.
     * The changed sizes will then be used to visualise our entity.
     * This is necessary because the game size is different than the graphics size.
     * @param width the width of the game to be changed
     * @param height the height of the game to be changed.
     */
    public void changeSize(int width, int height) {
        gr.ExplosionImg = gr.resizeImage(gr.ExplosionImg, (int)((double)width*gr.size), (int)((double)height*gr.size));
    }

}
