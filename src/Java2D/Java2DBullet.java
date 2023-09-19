package Java2D;

import Abstract.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class will us shoot at the enemies and the enemies can
 * shoot at the player.
 * This class is the Java2d version of the abstract class Bullet.
 * @author Weng Chiew Ma
 */
public class Java2DBullet extends Bullet {
    private Java2DGraphics gr;
    private BufferedImage image;
    public Java2DBullet(Java2DGraphics g){
        this.gr = g;
    }

    /**
     * This method will be used to visualise our entity
     * @param width the width of the game to be changed
     * @param height the height of the game to be changed.
     * @param type To see what kind of bullet that we need to visualise.
     */
    public void visualise(int width , int height, int type){
        changeSize(width, height, type);
        Graphics2D g2d = gr.getG2d();
        g2d.drawImage(image, (int)((double)getX()*gr.size), (int)((double)getY()*gr.size), null);
    }
    /**
     * This method will change the size of the incoming parameters.
     * The changed sizes will then be used to visualise our entity.
     * This is necessary because the game size is different than the graphics size.
     * @param width the width of the game to be changed
     * @param height the height of the game to be changed.
     * @param type To see what kind of bullet that we need to visualise.
     */
    public void changeSize(int width, int height, int type) {
        switch(type){
            case 1:
                image = gr.resizeImage(gr.BulletImg1, (int)((double)width*gr.size), (int)((double)height*gr.size));
                break;
            case 2:
                image = gr.resizeImage(gr.BulletImg2, (int)((double)width*gr.size), (int)((double)height*gr.size));
                break;
            default:
                break;
        }

    }
}
