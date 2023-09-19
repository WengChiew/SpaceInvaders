package Abstract;


import Derp.MovementComponent;

/**
 * This class is the abstract class of EnemyShip
 * This will be our enemies that we shoot down to get a higher score.
 * @author Weng Chiew Ma
 */
public abstract class EnemyShip {
    private MovementComponent movecomp;
    private int life;

    public EnemyShip(){
        movecomp = new MovementComponent();
    }
    public abstract void visualise(int width , int height);
    public abstract void visualiseBoss(int width, int height);




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

    public int getDy(){
        return this.movecomp.getDy();
    }

    public void setDx(int dx){
        this.movecomp.setDx(dx);
    }

    public void setDy(int dy){
        this.movecomp.setDy(dy);
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    /**
     * This method will let the enemies move to the next position.
     * @param direction The direction that the enemies move to(1=right, -1=left)
     * @param end If they have reached the end of the gamescreen they will drop to under and change direction.
     * @param step To determine how much they move everytime.
     */
    public void move(int direction, boolean end, int step){
        if(direction==1){
            if(end){
                setY(getY()+ 2*step);
            } else{
                setX(getX()+ 2*step);
            }
        }
        else if(direction == -1){
            if(end){
                setY(getY()+ 2*step);
            } else{
                setX(getX() - 2*step);
            }
        }
    }

    public MovementComponent getMovecomp() {
        return movecomp;
    }
}