package Derp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is for reading configuration files.
 * @author Weng Chiew Ma
 */
public class ReadProperties {

    public InputStream fis  = null;
    public int width;
    public int height;
    Properties prop = new Properties();

    /**
     * This method will read the incoming configuration file
     * and put the values in the variables.
     * @param file configuration file with a width and height.
     * @throws IOException throw Exception if there is no configuration file
     */
    public ReadProperties(String file) throws IOException {
        try {
            fis = new FileInputStream(file);
        } catch  (FileNotFoundException ex) {
            System.out.println("nice");
        }
        try {
            prop.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Exception");
        }
        width = Integer.parseInt(prop.getProperty("Width"));
        height = Integer.parseInt(prop.getProperty("Height"));
    }

    /**
     * This method returns the width.
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method returns the height.
     * @return height
     */
    public int getHeight() {
        return height;
    }
}
