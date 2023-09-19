package Abstract;

import Derp.MovementComponent;
/**
 * This is the abstract class of bonus
 * bonus will give is more points and an extra life.
 * @author Weng Chiew Ma
 */
public abstract class Bonus {
    private MovementComponent movecomp;

    public Bonus(){
        movecomp = new MovementComponent();
    }

    public abstract void visualise(int width, int height);

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

    public MovementComponent getMovecomp() {
        return movecomp;
    }

}
