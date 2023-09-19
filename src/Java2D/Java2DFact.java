package Java2D;


import Abstract.*;
import Derp.ReadProperties;

import java.io.IOException;

/**
 * This class is the java2d version of our abstract class AbstractFactory.
 * This class will be able to create the other entities. The abstract methods
 * will be carried out here.
 * @author Weng Chiew Ma
 */
public class Java2DFact extends AbstractFactory {
    private Java2DGraphics g;
    private final int width;
    private final int height;
    public Java2DFact(String configfile) throws IOException {
        ReadProperties r = new ReadProperties(configfile);
        g = new Java2DGraphics(r.getWidth(), r.getHeight());
        width = r.getWidth();
        height = r.getHeight();
    }

    /**
     * create entity Java2DPlayerShip.
     * @return Java2DPlayerShip
     */
    public PlayerShip createPlayerShip() {
        return new Java2DPlayerShip(g);
    }

    /**
     * create entity Java2DEnemyShip
     * @return Java2DEnemyShip
     */
    public EnemyShip createEnemyShip() {
        return new Java2DEnemyShip(g);
    }

    /**
     * create entity Java2DInput
     * @return Java2DInput
     */
    public Input createInput() {return  new Java2DInput(g);}

    /**
     * create entity Java2DBullet
     * @return Java2DBullet
     */
    public Bullet createBullet() {return new Java2DBullet(g);}

    /**
     * create entity Java2DBonus
     * @return Java2DBonus
     */
    public Bonus createBonus() {return new Java2DBonus(g);}

    /**
     * create entity Java2DExplosion
     * @return Java2DExplosion
     */
    public Explosion createExplosion() {return  new Java2DExplosion(g);}

    /**
     * create entity Java2DSound.
     * @return Java2DSound
     */
    public Sound createSound() {return new Java2DSound();}

    /**
     * This method will let us render our background. With this method
     * we can initialize the method doDrawing.
     */
    public void render(){
        g.render();
    }

    /**
     * This method will calculate the factor difference between the game and the graphics.
     * This factor will be used a lot for the visualisation. The background will also be set and the fonts will be
     * loaded.
     * @param gamewidth The GameWidth to calculate the factor
     * @param gameheight The Gameheigth to calculate the factor
     */
    public void setDimensions(int gamewidth, int gameheight) {
        g.setGameDimensions(gameheight, gameheight);
    }

    /**
     * This method will visualise the score and
     * the lives of the player on the screen.
     * @param score The current score of the player.
     * @param lives The current amount of lives of the player.
     */
    public void update(int score, int lives){
        g.update(score, lives);
    }

    /**
     * This method will visualise the screen if you pause the game
     */
    public void paused(){g.paused();}

    /**
     * This method will destroy the window that we have.
     * This will be done if you choose to quit the game.
     */
    public void quit(){g.quit();}

    /**
     * This method will visualise the screen if you lost the game
     * it will also show your final score.
     * @param score The final score that you got after losing the game.
     */
    public void gameOver(int score){g.gameOver(score);}

    /**
     * This method will visualise our title screen of the game.
     */
    public void titleScreen(){g.titleScreen();}
}

