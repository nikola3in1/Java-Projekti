/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author User
 */
public class FlappyBird implements ActionListener, KeyListener {

    public static final int FPS = 60, WIDTH = 640, HEIGHT = 480;

    private Bird bird;
    private JFrame frame;

    private JPanel panel;
    private ArrayList<Rectangle> rects;
    private int time, scroll;
    private Timer t;
    private static int speed = 3;
    private static int gap = 90;

    private boolean paused;


    public void go() {
        frame = new JFrame("Flappy Bird");
        bird = new Bird();
        rects = new ArrayList<Rectangle>();
        panel = new GamePanel(this, bird, rects);

        frame.add(panel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
        
        paused = true;
        
        t = new Timer(1000/FPS, this);
        t.start();
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
        if(!paused) {
            bird.physics();
            if(scroll % gap == 0) {
                Rectangle r = new Rectangle(WIDTH, 0, GamePanel.PIPE_W, (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT));
                int h2 = (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT);
                Rectangle r2 = new Rectangle(WIDTH, HEIGHT - h2, GamePanel.PIPE_W, h2);
                rects.add(r);
                rects.add(r2);
            }
            ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();
            boolean game = true;
            for(Rectangle r : rects) {
                r.x-=speed;
                if(r.x + r.width <= 0) {
                    toRemove.add(r);
                }
                if(r.contains(bird.x, bird.y-60)||r.contains(bird.x, bird.y+10)) {
                    JOptionPane.showMessageDialog(frame, "You lose!\n"+"Your score was: "+time+".");
                    FileWriter fw;
                    try {
                        fw = new FileWriter("Score.txt",true);
                        fw.write(time+"\n");
                        fw.close();
                        getHighScore();


                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    game = false;
                }
            }
            rects.removeAll(toRemove);
            time++;
            scroll++;

            if(bird.y > HEIGHT || bird.y+bird.RAD < 0) {
                game = false;
            }

            if(!game) {
                rects.clear();
                bird.reset();
                time = 0;
                scroll = 0;
                paused = true;

            }
        }
        else {


        }
    }
    
    public int getScore() {
        return time;
    }

    public int getHighScore(){

        String scores;
        Integer temp = 0;
        Integer score;
        Integer highscore=0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Score.txt"));
            while ((scores = bufferedReader.readLine()) != null)
            {

                score = temp.parseInt(scores);
               if(score>highscore){
                   highscore=score;
               }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highscore;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP && paused == false) {
            bird.jump();
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            paused = false;
        }else if(e.getKeyCode()==KeyEvent.VK_M && paused == true){
            frame.setVisible(false);
            frame.dispose();///BRISE STARI WINDOW
            new ModePanel().modes();
        }
    }
    public void keyReleased(KeyEvent e) {
        
    }
    public void keyTyped(KeyEvent e) {
        
    }
    
    public boolean paused() {
        return paused;
    }
}
