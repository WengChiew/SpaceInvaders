package Derp;

/**
 * This class will be the movementcomponent for all entities.
 * With this class every entity can move and have coordinates.
 * @author Weng Chiew Ma
 */
public class MovementComponent {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;


    public MovementComponent(){
        this.x = 0;
        this.y = 0;
        this.dx = 0;
        this.dy = 0;
        this.width = 0;
        this.height = 0;
    }

    /**
     * return x value
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * give x a new value
     * @param newX new x value
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * return y value
     * @return y new y value
     */
    public int getY() {
        return y;
    }

    /**
     * give y a new value
     * @param newY new y value
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * return horizontal direction
     * @return dx
     */
    public int getDx() {
        return dx;
    }

    /**
     * change horizontal direction
     * @param newDx new dx value
     */
    public void setDx(int newDx) {
        this.dx = newDx;
    }

    /**
     * return vertical direction
     * @return dy
     */
    public int getDy() {
        return dy;
    }
    /**
     * change vertical direction
     * @param newDy new y value
     */
    public void setDy(int newDy) {
        this.dy = newDy;
    }

    /**
     * return width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * change width
     * @param width new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * return height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * return height
     * @param height new height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}

