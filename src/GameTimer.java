/**
 * This class will let us run the game on a constant fps.
 * @author Weng Chiew Ma
 */
public class GameTimer {
    private long startTime;
    private long endTime;
    public GameTimer(){
        startTime =0;
        endTime =0;
    }

    /**
     * This method gives startTime the
     * time value the moment it get called
     */
    public void tick(){
        this.startTime = System.nanoTime();
    }
    /**
     * This method gives endTime the
     * time value the moment it get called
     */
    public void tock(){
        this.endTime = System.nanoTime();
    }

    /**
     * This method will cause us to sleep until we reached the desired time.
     * This way the game keeps running on 30fps.So we sleep the remaining time
     * that is over.
     */
    public void sleep(){
        try{
            Thread.sleep(16 - (long)(Math.pow(10, -6))*(this.endTime-this.startTime));
        } catch(InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }

}
