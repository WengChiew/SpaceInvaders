import Abstract.AbstractFactory;
import Java2D.Java2DFact;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        AbstractFactory f = new Java2DFact("src/resources/graphics_config.properties");
        Game g = new Game(f);
        g.run("src/resources/game_config.properties");

    }
}