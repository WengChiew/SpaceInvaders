package Java2D;

import Abstract.Sound;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * This class will let us have sound effects in our game
 * This class is the Java2d version of the abstract class sound.
 * @author Weng Chiew Ma
 */
public class Java2DSound extends Sound {
    private Clip clip;
    private File shoot;
    private File playerdied;
    private File enemydied;
    private File move;
    private File pauseMusic;
    public Java2DSound(){}

    /**
     * This method will play a clip based on the incoming parameter
     * @param type this will decide which clip will start playing.
     */
    public void play(int type){
        final float value =  -20f;
        try{
            clip = AudioSystem.getClip();
            switch(type){
                case 1:
                    clip.open(AudioSystem.getAudioInputStream(shoot));
                    break;
                case 2:
                    clip.open(AudioSystem.getAudioInputStream(playerdied));
                    break;
                case 3:
                    clip.open(AudioSystem.getAudioInputStream(enemydied));
                    break;
                case 4:
                    clip.open(AudioSystem.getAudioInputStream(move));
                    break;
                case 5:
                    clip.open(AudioSystem.getAudioInputStream(pauseMusic));
                    break;
                default:
                    break;
            }
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(value);
            clip.start();
        }
        catch(Exception e){
            System.out.println("Exception");
        }
    }

    /**
     * This method will load all the wav files that we need.
     */
    public void load(){
        shoot = new File("src/resources/shoot.wav");
        playerdied = new File("src/resources/explosion.wav");
        enemydied = new File("src/resources/invaderkilled.wav");
        move = new File("src/resources/fastinvader1.wav");
        pauseMusic = new File("src/resources/spaceinvaders1.wav");
    }

    /**
     * This method will stop the running clip from playing.
     */
    public void stop(){
        clip.stop();
    }

    /**
     * This method will let the clip play in a loop.
     */
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    /**
     * return true if the clip is running
     * and false if it is not.
     * @return boolean
     */
    public boolean clipRunning(){
        return clip.isActive();
    }


}
