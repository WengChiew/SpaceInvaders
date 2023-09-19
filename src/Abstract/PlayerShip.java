package Abstract;


import Derp.MovementComponent;

/**
 * This is the abstract class of PlayerShip
 * This will be the class to control our player.
 * @author Weng Chiew Ma
 */
public abstract class PlayerShip {
    private MovementComponent movecomp;

    public PlayerShip(){
        movecomp = new MovementComponent();
    }
    public  abstract void visualise(int width, int height);

    public int getX(){
        return this.movecomp.getX();
    }

    public int getY(){
        return this.movecomp.getY();
    }

    public void setX(int x){
        this.movecomp.setX(x);
    }

    public void setY(int y){
        this.movecomp.setY(y);
    }

    public void setCo(int x, int y){
        this.movecomp.setX(x);
        this.movecomp.setY(y);
    }

    public int getDx(){
        return this.movecomp.getDx();
    }

    public void setDx(int dx){
        this.movecomp.setDx(dx);
    }

    public int getWidth(){
        return this.movecomp.getWidth();
    }

    public void setWidth(int width){
        this.movecomp.setWidth(width);
    }

    public int getHeight(){
        return this.movecomp.getHeight();
    }

    public void setHeight(int height){
        this.movecomp.setHeight(height);
    }


    public MovementComponent getMovecomp() {
        return movecomp;
    }
}
