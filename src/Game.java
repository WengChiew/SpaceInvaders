import Abstract.*;
import Derp.ReadProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Game is the class where our game will be running.
 * @author Weng Chiew Ma
 */
public class Game {
    //game
    private AbstractFactory f;
    private int Gamewidth;
    private int Gameheight;
    private boolean isRunning = true;
    private final int gameWall = 50;
    private int score = 0;
    private int lives = 3;
    private boolean GameOver = false;
    private boolean isPause = false;
    private boolean restart= false;
    private boolean nextLevel = false;
    private int nextLevelCounter = 0;
    private boolean titleScreen = true;
    private int level = 1;


    //player
    private int step;
    private PlayerShip p;
    private int playerWidth;
    private int playerHeight;
    boolean isFired = false;
    private boolean playerDown = false;


    //enemies
    private ArrayList<EnemyShip> enemyList;
    private int enemyWidth;
    private int enemyHeight;
    private int enemyDirection = 1;
    private int enemyCounter = 0;
    private int enemySpeed; //55
    private EnemyShip hurtenemy;
    private EnemyShip shooter;
    private boolean isEnd = false;
    private boolean enemyDown = false;
    private boolean enemyAttacking = true;
    private boolean enemyFired = false;
    private EnemyShip boss;
    private boolean bossBattle;
    private int bossWidth;
    private int bossHeight;
    private boolean bossDown =false;
    private final int bossSpeed = 18;

    //bullet
    private int bulletWidth;
    private int bulletHeight;
    private ArrayList<Bullet> playerBullet;
    private ArrayList<Bullet> enemyBullet;

    //bonus
    private int bonusHeight;
    private int bonusWidth;
    private ArrayList<Bonus> bonusList;
    private boolean isBonus = false;
    private boolean bonusDown = false;
    private int bonusCounter = 0;
    private boolean bonusOver = false;

    //timer
    private GameTimer timer;

    //explosion
    private Explosion explosion;
    private int explosionWidth;
    private int explosionHeight;
    private boolean makeExplosion = false;
    private int explosionCounter = 0;

    //sound
    private Sound sound;

    //input
    private Input input;


    public Game(AbstractFactory f) {
        this.f = f;
    }

    /**
     * This method will be called at the beginning of the game.
     * all entities will be made and values will be given.
     */
    public void initGame() {
        f.setDimensions(getGameWidth(), getGameHeight());

        p = f.createPlayerShip();
        playerWidth = getGameWidth() / 18;
        playerHeight = getGameHeight() / 22;
        p.setWidth(playerWidth);
        p.setHeight(playerHeight);
        p.setCo(getGameWidth() / 2, getGameHeight() - 2*gameWall);
        step = 15;

        input = f.createInput();

        enemyWidth = getGameWidth() / 17;
        enemyHeight = getGameHeight() / 21;
        enemySpeed = 55;
        enemyList = new ArrayList<EnemyShip>();
        makeEnemies();
        bossWidth = getGameWidth() / 8;
        bossHeight = getGameHeight() / 9;
        boss = f.createEnemyShip();
        bulletWidth = getGameWidth() / 95;
        bulletHeight = getGameHeight() / 40;
        playerBullet = new ArrayList<Bullet>();
        enemyBullet = new ArrayList<Bullet>();


        timer = new GameTimer();

        bonusHeight = getGameHeight() / 22;
        bonusWidth = getGameWidth() / 18;
        bonusList = new ArrayList<Bonus>();

        explosion = f.createExplosion();
        explosionWidth = getGameWidth() / 18;
        explosionHeight = getGameHeight() /22;
        explosion.setWidth(explosionWidth);
        explosion.setHeight(explosionHeight);

        sound = f.createSound();
        sound.load();

    }

    /**
     * This method will be the game that keeps running in a loop.
     * Here we will use all the logic the let the game run efficiently.
     * The input parameter is a String of a configfile. The file will give us the
     * width and height of our game.
     * @param configfile configuration file with the gameheight and gamewidth.
     * @throws IOException throw Exception if there is no configuration file
     */
    public void run(String configfile) throws IOException {
        ReadProperties r = new ReadProperties(configfile);
        Gameheight = r.getHeight();
        Gamewidth = r.getWidth();
        initGame();
        System.out.println("running game");
        sound.play(5);
        sound.loop();
        timer.tick();
        while (isRunning) {
            if (input.inputAvailable()) {
                switch (input.getInput()) {
                    case LEFT:
                        if (!isPause  && !titleScreen) {
                            if (p.getX() - gameWall > 0) {
                                p.setX(p.getX() - step);
                            }
                        }
                        break;
                    case RIGHT:
                        if (!isPause && !titleScreen) {
                            if (p.getX() + playerWidth < getGameWidth() - gameWall) {
                                p.setX(p.getX() + step);
                            }
                        }
                        break;
                    case SPACE:
                        if (!isFired && !isPause && !titleScreen) {
                            playerBullet.clear();
                            Bullet b = f.createBullet();
                            b.setCo(p.getX() + playerWidth / 3 + 3, p.getY() - bulletHeight);
                            b.setType(1);
                            b.setWidth(bulletWidth);
                            b.setHeight(bulletHeight);
                            playerBullet.add(b);
                            isFired = true;
                            enemyDown = false;
                            sound.play(1);
                        }
                        break;
                    case ESCAPE:
                        if (!isPause  && !titleScreen) {
                            isPause = true;
                            sound.play(5);
                            sound.loop();
                        } else{
                            if(!titleScreen) {
                                isPause = false;
                                sound.stop();
                            }
                        }
                        break;
                    case Q:
                        if(isPause || GameOver || titleScreen){
                            f.quit();
                            isRunning = false;
                        }
                        break;
                    case S:
                        if(isPause || GameOver || titleScreen){
                            restart = true;
                            sound.stop();
                        }
                        break;
                    default:
                        break;
                }
            }


            if (!GameOver && !isPause && !titleScreen) {
                visualiseEntities();
                if(bossBattle){
                    if (enemyCounter >= bossSpeed) {
                        checkEnd();
                        changeDirection();
                        enemyListMove();
                        isEnd = false;
                        enemyCounter = 0;
                        sound.play(4);
                    }
                } else {
                    if (enemyCounter >= enemySpeed) {
                        checkEnd();
                        changeDirection();
                        enemyListMove();
                        isEnd = false;
                        enemyCounter = 0;
                        sound.play(4);
                    }
                }
                enemyCounter++;

                keepAttacking();

                bulletsAreFired();

                checkAllCollisions();

                startBonus();

                entitiesDown();

                checkGameOVer();

                checkLevelCleared();

            }

            if(isPause){
                f.paused();
            }

            if(titleScreen && !GameOver && !isPause){
                f.titleScreen();
            }

            if(restart){
                reset();
            }

            if(GameOver){
                f.gameOver(score);
                if(!sound.clipRunning()){
                    sound.play(5);
                    sound.loop();
                }
            }

            if(nextLevel){
                goNextLevel();
            }
            if(!titleScreen && !GameOver) {
                f.update(score, lives);
            }
            f.render();
            timer.tock();
            timer.sleep();
        }
    }

    /**
     *This method wil return the width of the game.
     * @return Gamewidth
     */
    public int getGameWidth() {
        return Gamewidth;
    }

    /**
     * This method will return the height of the game.
     * @return Gameheight
     */
    public int getGameHeight() {
        return Gameheight;
    }

    /**
     * This method will make the enemies and they will be put in a list
     * Enemies exist of 5 rows and 12 columns.
     */
    public void makeEnemies() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 11; j++) {
                EnemyShip enemyShip = f.createEnemyShip();
                enemyShip.setCo(gameWall + j * enemyWidth + 30, i * enemyHeight + 45);
                enemyShip.setWidth(enemyWidth);
                enemyShip.setHeight(enemyHeight);
                enemyShip.setLife(100);
                enemyList.add(enemyShip);
            }
    }

    /**
     * this method will visualise the player, the enemies, the bonus
     * and the explosions
     */
    public void visualiseEntities(){
        int bonusTime = 250;
        int explosionTime = 10;
        p.visualise(playerWidth, playerHeight);
        for (EnemyShip en : enemyList) {
            if (bossBattle) {
                en.visualiseBoss(bossWidth, bossHeight);
            } else {
                en.visualise(enemyWidth, enemyHeight);
            }
        }
        if(isBonus){
            if(bonusCounter <= bonusTime && !bonusList.isEmpty()){
                bonusCounter++;
                bonusList.get(0).visualise(bonusWidth, bonusHeight);
            } else {
                if(!bonusList.isEmpty()) {
                    bonusList.remove(bonusList.get(0));
                }
                bonusCounter = 0;
                isBonus = false;
                bonusOver = true;
                bonusDown = false;
            }

        }

        if(makeExplosion && explosionCounter <= explosionTime){
            explosionCounter++;
            explosion.visualise(explosionWidth, explosionHeight);
        } else {
            explosionCounter = 0;
            makeExplosion = false;
        }
    }

    /**
     * This method changes the direction of the moving enemyships,
     * if they have reached the end of the Gamescreen.
     * 1 = right and -1 = left
     */
    public void changeDirection(){
        if (isEnd) {
            if (enemyDirection == 1) {
                enemyDirection = -1;
            } else if (enemyDirection == -1) {
                enemyDirection = 1;
            }
        }
    }
    /**
     * This method checks if even 1 enemy has reached the
     * end. If so then the boolean 'isEnd" will get the value 'true'.
     */
    public void checkEnd() {
        if (bossBattle) {
            for (EnemyShip enemyShip : enemyList) {
                if (enemyDirection == 1 && enemyShip.getX() + bossWidth > getGameWidth() - gameWall) {
                    isEnd = true;
                } else if (enemyDirection == -1 && enemyShip.getX() < gameWall) {
                    isEnd = true;
                }
            }
        } else {
            for (EnemyShip enemyShip : enemyList) {
                if (enemyDirection == 1 && enemyShip.getX() + enemyWidth > getGameWidth() - gameWall) {
                    isEnd = true;
                } else if (enemyDirection == -1 && enemyShip.getX() < gameWall) {
                    isEnd = true;
                }
            }
        }
    }

    /**
     * This method will let every enemyShip take the next step.
     */
    public void enemyListMove(){
        for (EnemyShip enemyShip : enemyList) {
            enemyShip.move(enemyDirection, isEnd, step);
        }
    }

    /**
     * This method will give a random enemy the right to shoot a bullet.
     * There will be constantly one shooter.
     */
    public  void keepAttacking(){
        if (enemyAttacking) {
            if (!enemyList.isEmpty()) {
                playerDown = false;
                Bullet b = f.createBullet();
                enemyFired = true;
                shooter = enemyList.get(((int) (Math.random() * enemyList.size())));
                b.setCo(shooter.getX() + enemyWidth / 3 + 3, shooter.getY() + enemyHeight);
                b.setType(2);
                enemyBullet.add(b);
            }
        }
    }

    /**
     * This method will viusualise the bullets and it will
     * make the bullets move(playerbullet and enemybullet).
     * If the bullet is out of the gamescreen the bullet will disappear.
     * only happens if a bullet is fired.
     */
    public void bulletsAreFired(){
        if (enemyFired) {
            Iterator<Bullet> itbullet = enemyBullet.iterator();
            while (itbullet.hasNext()) {
                Bullet b = itbullet.next();
                if (b.getY() < p.getY() + playerHeight) {
                    b.visualise(bulletWidth, bulletHeight, b.getType());
                    b.fire(2);
                    enemyAttacking = false;
                } else {
                    enemyFired = false;
                    itbullet.remove();
                    enemyAttacking = true;
                }
            }
        }

        if (isFired) {
            Iterator<Bullet> itb = playerBullet.iterator();
            while(itb.hasNext()) {
                Bullet b = itb.next();
                if (b.getY() > 0) {
                    b.fire(1);
                    b.visualise(bulletWidth, bulletHeight, b.getType());

                } else {
                    isFired = false;
                    itb.remove();
                }
            }


        }
    }

    /**
     * This method checks if there is any collision between
     * the bullets and other entities. If there is any collision
     * the bullets get cleared and some booleans will be flipped.
     * if enemies are hit their life points will get reduced by 100
     * normal enemies have 100 life points and the boss has 20000.
     */
    public void checkAllCollisions(){
        Iterator<Bullet> itbull = enemyBullet.iterator();
        while (itbull.hasNext()) {
            Bullet b = itbull.next();
            if (b.collision(p.getMovecomp())) {
                playerDown = true;
                itbull.remove();
                enemyAttacking = true;
                enemyFired = false;
            }
        }

        for (EnemyShip enemyShip : enemyList) {
            if (!enemyDown && playerBullet.size() > 0) {
                Iterator<Bullet> itBullet = playerBullet.iterator();
                while (itBullet.hasNext()) {
                    Bullet b =itBullet.next();
                    if (b.collision(enemyShip.getMovecomp())) {
                        if(enemyShip.getLife() > 100){
                            enemyShip.setLife(enemyShip.getLife() - 100);
                            sound.play(3);
                        } else {
                            if(bossBattle){
                                hurtenemy = enemyShip;
                                bossDown = true;
                            } else {
                                hurtenemy = enemyShip;
                                enemyDown = true;
                            }
                        }
                        isFired = false;
                        itBullet.remove();
                    }
                }
            }
        }

        if(isBonus) {
            if (!bonusList.isEmpty()) {
                for (Bonus bonus : bonusList) {
                    Iterator<Bullet> itB = playerBullet.iterator();
                    while (itB.hasNext()) {
                        Bullet b = itB.next();
                        if (b.collision(bonus.getMovecomp())) {
                            bonusDown = true;
                            isFired = false;
                            itB.remove();
                            isBonus = false;
                            bonusOver = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will make bonus when the enemies are
     * dropped to 15 enemies or less. The bonus will get random
     * coordinates and will stay until it got hit or the timer is over.
     */
    public void startBonus(){
        if(enemyList.size() <= 15 && !isBonus && !bonusOver) {
            Bonus b = f.createBonus();
            b.setCo((int)(Math.random() * (getGameWidth()- 2*gameWall +1) ) + gameWall - bonusWidth, 20); //(int)(Math.random() * (getGameWidth()- 2*gameWall +1) ) + gameWall
            b.setHeight(bonusHeight);
            b.setWidth(bonusWidth);
            isBonus = true;
            bonusList.add(b);
        }
    }

    /**
     * This method gives the consequences if entities got hit.
     * enemy down will remove the enemy and the score goes up by 100
     * it will also create an explosion
     * player down will reduce your lives with 1.
     * bonus down will give you 1000 extra score and one extra life.
     * this will also create an explosion.
     * boss down will give you 5000 score an dmmake an explosion.
     */
    public void entitiesDown(){
        if (enemyDown) {
            explosion.setCo(hurtenemy.getX(), hurtenemy.getY());
            enemyList.remove(hurtenemy);
            score += 100;
            enemyDown = false;
            makeExplosion = true;
            sound.play(3);
        }

        if (bonusDown) {
            Iterator<Bonus> itBonus = bonusList.iterator();
            while(itBonus.hasNext()) {
                Bonus b = itBonus.next();
                explosion.setCo(b.getX(), b.getY());
                itBonus.remove();
            }
            score += 1000;
            lives += 1;
            bonusOver = true;
            bonusDown = false;
            makeExplosion = true;
            sound.play(3);
        }

        if (playerDown) {
            lives -= 1;
            playerDown = false;
            sound.play(2);
        }

        if(bossDown){
            explosion.setCo(hurtenemy.getX(), hurtenemy.getY());
            enemyList.remove(hurtenemy);
            score += 5000;
            bossDown = false;
            makeExplosion = true;
            sound.play(3);
            bossBattle = false;
        }
    }

    /**
     * This method will check if we lost the game
     * the game is over if we have zero lives and
     * if they lowest enemy has reached the player.
     */
    public void checkGameOVer(){
        for(EnemyShip enemyShip: enemyList){
            if(enemyShip.getY() + enemyHeight >= p.getY()){
                GameOver = true;
                lives = 0;
            }
        }

        if(lives <= 0){
            GameOver = true;

        }
    }

    /**
     * This method wil reset the values to their initial values.
     * This way the game can restart.
     */
    public void reset(){
        score = 0;
        lives = 3;
        playerBullet.clear();
        enemyBullet.clear();
        enemyList.clear();
        bonusList.clear();
        isFired = false;
        GameOver = false;
        enemyDirection = 1;
        isEnd = false;
        enemyCounter = 0;
        enemyDown = false;
        enemyAttacking = true;
        playerDown = false;
        enemyFired = false;
        isPause = false;
        isBonus = false;
        bonusDown = false;
        bonusCounter = 0;
        bonusOver = false;
        restart= false;
        p.setCo(getGameWidth() / 2, 540);
        makeEnemies();
        enemySpeed = 55;
        titleScreen = false;
        sound.stop();
    }

    /**
     * this method checks if we can go to the next level
     *
     */
    public void checkLevelCleared(){
        if(enemyList.isEmpty() && lives > 0){
            nextLevel = true;
            enemyBullet.clear();
            playerBullet.clear();
            bonusList.clear();
            enemyList.clear();
        }
    }


    /**
     * This method will bring us to the next level.
     * there will be a boss stage every 4 levels.
     * The boss will be stronger and faster.
     * there will be a little delay before the next level starts.
     */
    public void goNextLevel(){
        int timer = 75;
        if(nextLevelCounter <= timer){
            nextLevelCounter++;
        } else {
            if(level % 4 == 0){
                bossBattle = true;
                boss.setWidth(bossWidth);
                boss.setHeight(bossHeight);
                boss.setCo(getGameWidth()/2, getGameHeight()/6);
                boss.setLife(3000);
                enemyList.add(boss);
            } else {
                makeEnemies();
                bonusOver = false;
                enemySpeed -= 5;
            }
            enemyCounter = 0;
            isBonus = false;
            bonusDown = false;
            level++;
            nextLevelCounter = 0;
            bonusCounter = 0;
            enemyDirection = 1;
            enemyAttacking = true;
            isFired = false;
            isEnd = false;
            enemyDown = false;
            playerDown = false;
            nextLevel = false;
        }
    }





}