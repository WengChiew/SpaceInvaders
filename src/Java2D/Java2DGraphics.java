package Java2D;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class will make our window of the game and
 * with this class we can visualise our entities.
 * @author Weng Chiew Ma
 */
public class Java2DGraphics {
    private JFrame frame;
    private JPanel panel;
    private BufferedImage g2dimage;     // used for drawing
    private Graphics2D g2d;             // always draw in this one
    public BufferedImage PlayerShipImg;
    public BufferedImage EnemyShip1Img;
    public BufferedImage EnemyShip2Img;
    public BufferedImage EnemyShip3Img;
    public BufferedImage BossImg;
    public BufferedImage BulletImg1;
    public BufferedImage BulletImg2;
    public BufferedImage BonusImg;
    public BufferedImage backgroundImg;
    public BufferedImage ExplosionImg;
    public double size;                   // cel size
    private int width;
    private int height;
    private Font standardFont;
    private Font bigFont;
    private Font textFont;
    private Font statusFont;

    /**
     * return graphics g2d
     * @return g2d
     */
    public Graphics2D getG2d() {
        return g2d;
    }

    /**
     * return frame
     * @return  frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * This method is necessary for resizing our pngs.
     * @param originalImage the original png for resizing.
     * @param targetWidth the width that you want your png to have
     * @param targetHeight the height that you want your png to have.
     * @return the new resized png.
     */
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public Java2DGraphics(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
        frame = new JFrame();
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        frame.setFocusable(true);
        frame.add(panel);
        frame.setTitle("Space Invaders");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * This method will load all the different pngs.
     */
    public void loadImages() {
        try{
            backgroundImg = null;

            backgroundImg = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Background.png"));

            PlayerShipImg = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Player.png"));

            EnemyShip1Img = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Enemy1.png"));

            EnemyShip2Img = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Enemy2.png"));

            EnemyShip3Img = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Enemy3.png"));

            BulletImg1 = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Bullet1.png"));

            BulletImg2 = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Bullet2.png"));

            BonusImg = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Bonus.png"));

            ExplosionImg = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/explosion.png"));

            BossImg = ImageIO.read(new File("src/be/uantwerpen/fti/ei/Icons/Boss.png"));

        }
        catch(IOException e){
            System.out.println("Error");
        }
    }

    /**
     * This methpd will draw our background and will start
     * if we call repaint();
     * @param g The graphics to draw something.
     */
    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0, 0, null);   // copy buffered image
        graph2d.dispose();
        if (g2d != null)
            g2d.drawImage(backgroundImg,0, 0, null);
    }

    /**
     * This method will let us render our background. With this method
     * we can initialize the method doDrawing.
     */
    public void render() {
        panel.repaint();
    }

    /**
     * This method will calculate the factor difference between the game and the graphics.
     * This factor will be used a lot for the visualisation. The background will also be set and the fonts will be
     * loaded.
     * @param GameWidth The GameWidth to calculate the factor
     * @param GameHeight The Gameheigth to calculate the factor
     */
    public void setGameDimensions(int GameWidth, int GameHeight) {
        size = Math.min((double)width/(double)GameWidth, height/(double)GameHeight);
        System.out.println("size: "+size);
        loadImages();
        try {
            backgroundImg = resizeImage(backgroundImg, frame.getWidth(), frame.getHeight());
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
        g2d.drawImage(backgroundImg,0, 0, null);
        loadFonts();
    }


    /**
     * This method will visualise the score and
     * the lives of the player on the screen.
     * @param score The current score of the player.
     * @param lives The current amount of lives of the player.
     */
    public void update(int score, int lives){
        g2d.setFont(statusFont);
        g2d.drawString("S C O R E  : " + Integer.toString(score), 50, 750);
        g2d.drawString("L I V E S  : " + Integer.toString(lives), width/2, 750);
    }

    /**
     * This method will visualise the screen if you pause the game
     */
    public void paused(){
        g2d.setFont(textFont);
        g2d.drawString("PRESS ESCAPE TO CONTINUE", this.width / 4, this.height/3);
        g2d.drawString("PRESS 'S' TO RESTART", this.width / 4, this. height/3 + 1*(this.height/10));
        g2d.drawString("PRESS 'Q' TO QUIT", this.width / 4, this. height/3 + 2*(this.height/10));
    }

    /**
     * This method will destroy the window that we have.
     * This will be done if you choose to quit the game.
     */
    public void quit(){
        frame.dispose();
    }

    /**
     * This method will visualise the screen if you lost the game
     * it will also show your final score.
     * @param score The final score that you got after losing the game.
     */
    public void gameOver(int score){
        g2d.setFont(bigFont);
        g2d.drawString("G  A  M  E  O  V  E  R", this.width/10, this.height/4);
        g2d.setFont(textFont);
        g2d.drawString("PRESS 'S' TO PLAY AGAIN", this.width / 4, this. height/3 + 1*(this.height/10));
        g2d.drawString("PRESS 'Q' TO QUIT", this.width / 4, this. height/3 + 2*(this.height/10));
        g2d.drawString("FINAL SCORE: " + score, this.width / 4, this. height/3 + 3*(this.height/10));
    }

    /**
     * This method will visualise our title screen of the game.
     */
    public void titleScreen(){
        g2d.setFont(bigFont);
        g2d.drawString("SPACE INVADERS", this.width/8, this.height/4);
        g2d.setFont(textFont);
        g2d.drawString("PRESS 'S' TO PLAY", this.width / 3, this. height/3 + 1*(this.height/10));
        g2d.drawString("PRESS 'Q' TO QUIT", this.width / 3, this. height/3 + 2*(this.height/10));
    }

    /**
     * This method will load all the different fonts based of the standard Font.
     */
    public void loadFonts(){
        standardFont = g2d.getFont();
        statusFont = standardFont.deriveFont(standardFont.getSize() * 2.0F);
        bigFont = standardFont.deriveFont(standardFont.getSize() * 7.0F);
        textFont = standardFont.deriveFont(standardFont.getSize() * 3.0F);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

