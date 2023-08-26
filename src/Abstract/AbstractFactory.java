package Abstract;
/**
 * This is our abstract factory with all the abstract methods
 * for creating the other entities. It also has other methods like setting
 * the dimensions and changing the status of the game.
 */
public abstract class AbstractFactory {
    public AbstractFactory(){
    }

    public abstract PlayerShip createPlayerShip();

    public abstract EnemyShip createEnemyShip();

    public abstract Bullet createBullet();

    public abstract Input createInput();

    public abstract Bonus createBonus();

    public abstract Explosion createExplosion();

    public abstract Sound createSound();

    public abstract void render();

    public abstract void setDimensions(int gamewidth, int gameheight);

    public abstract void update(int score, int lives);

    public abstract void paused();

    public abstract void quit();

    public abstract void gameOver(int score);

    public abstract void titleScreen();
}

