package Abstract;


import Derp.MovementComponent;

/**
 * This is the abstract class of bullet
 * With this entities can shoot down other entities.
 * @author Weng Chiew
 */
public abstract class Bullet {
    private MovementComponent movecomp;
    private boolean hit = false;
    private int type;

    public Bullet() {
        movecomp = new MovementComponent();
    }

    public abstract void visualise(int width, int height, int type);

    public int getX() {
        return this.movecomp.getX();
    }

    public int getY() {
        return this.movecomp.getY();
    }

    public void setX(int x) {
        this.movecomp.setX(x);
    }

    public void setY(int y) {
        this.movecomp.setY(y);
    }

    public void setCo(int x, int y) {
        this.movecomp.setX(x);
        this.movecomp.setY(y);
    }

    public int getDx() {
        return this.movecomp.getDx();
    }

    public int getDy() {
        return this.movecomp.getDy();
    }

    public void setDx(int dx) {
        this.movecomp.setDx(dx);
    }

    public void setDy(int dy) {
        this.movecomp.setDy(dy);
    }

    public int getWidth() {
        return this.movecomp.getWidth();
    }

    public void setWidth(int width) {
        this.movecomp.setWidth(width);
    }

    public int getHeight() {
        return this.movecomp.getHeight();
    }

    public void setHeight(int height) {
        this.movecomp.setHeight(height);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MovementComponent getMovecomp() {
        return movecomp;
    }

    /**
     * This method will let the bullet move in a direction
     * based on the incoming parameter.
     * @param direction 1 then the bullet is down. -1 then the bullet is going down.
     */
    public void fire(int direction) {
        switch(direction){
            case 1:
                setY(this.getY() - 12);
                break;
            case 2:
                setY(this.getY() + 8);
                break;
            default:
                break;

        }

    }

    /**
     * This method is the collisionDetection. if there is any collision
     * a boolean true will be returned.
     * @param m the paramter to see if it collided with a bullet.
     * @return boolean true if collision detected else false.
     */
    public boolean collision(MovementComponent m) {
        if (hitEnemyY(m) || hitPlayerY(m)) {
            if (hitX(m)) {
                hit = true;
            }
        } else {
            hit = false;
        }
        return hit;
    }

    /**
     * This method will see if an enemy and the playerBullet
     * cross each other based on the y coordinate. The method will return the boolean.
     * @param m to compare the Y coordinates with bullet.
     * @return hit true if even a part overlaps of the y coordinates else false.
     */
    public boolean hitEnemyY(MovementComponent m) {
        boolean hit;
        if (((this.getY() <= m.getY() + m.getHeight()) && (this.getY() >= m.getY()))) {
            hit = true;
        } else{hit = false;}
        return hit;
    }

    /**
     * This method will see if the player and the enemyBullet
     * cross each other based on the y coordinate. The method will return the boolean.
     * @param m to compare the Y coordinates with bullet.
     * @return hit true if even a part overlaps of the y coordinates else false.
     */
    public boolean hitPlayerY(MovementComponent m) {
        boolean hit;
        if (((this.getY() + this.getHeight() >= m.getY()) && (this.getY() + this.getHeight() <= m.getY() + m.getHeight()))) {
            hit = true;
        } else{hit = false;}
        return hit;
    }

    /**
     * This method will see if the parameter and the bullet
     * cross each other based on the x coordinate. The method will return the boolean.
     * @param m to compare the x coordinates with bullet.
     * @return hit true if even a part overlaps of the x coordinates else false.
     */
    public boolean hitX(MovementComponent m) {
        boolean hit;
        if ((this.getX() >= m.getX()) && (this.getX() <= m.getX() + m.getWidth())) {
            hit = true;
        } else{hit = false;}
        return hit;
    }


}