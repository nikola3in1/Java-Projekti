package flappybird;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by nikola3in1 on 28.1.17..
 */
public class SelectBird {
    private static Image selectedBird;
    public SelectBird(){
    }

    public static Image getSelectedBird() {
        return selectedBird;
    }

    public static void getBird (int birdID) throws IOException {
        Image img = null;
        switch (birdID){
        case 1:
            img = ImageIO.read(new File("sticker,375x360.u2.png"));
            break;
        case 2:
            img = ImageIO.read(new File("sticker,375x360.u2-blue.png"));
            break;
        case 3:
            img = ImageIO.read(new File("sticker,375x360.u2-green.png"));
            break;
    }
        selectedBird = img;
    }
}
