package Abstract;

/**
 * The abstract class of sound
 * Sound will give our games sound effects
 * @author Weng Chiew Ma
 */
public abstract class Sound {
    public Sound(){}
    public abstract void play(int type);
    public abstract void load();
    public abstract void stop();
    public abstract void loop();
    public abstract boolean clipRunning();
}

