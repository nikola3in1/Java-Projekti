/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author User
 */
public class Bird {
    public float x, y, vx, vy;
    public static final int RAD = 25;
    private Image img;


    public Bird() {
        x = FlappyBird.WIDTH / 2;
        y = FlappyBird.HEIGHT / 2;
        img = SelectBird.getSelectedBird();

    }

    public void physics() {
        x += vx;
        y += vy;
        vy += 0.5f;
    }

    public void update(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(img, Math.round(x - RAD), Math.round(y - RAD), 2 * RAD, 2 * RAD, null);
    }

    public void jump() {
        vy = -8;

        try{
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(new File("jump.wav"));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

        }catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        x = 640 / 2;
        y = 640 / 2;
        vx = vy = 0;
    }
}
