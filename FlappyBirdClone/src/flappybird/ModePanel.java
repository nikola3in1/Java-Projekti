package flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by nikola3in1 on 28.1.17..
 */
public class ModePanel {
    private JFrame frame1;
    private JButton btn = new JButton();
    private JButton btn1 = new JButton();
    private JButton btn2  = new JButton();
    private JLabel title = new JLabel();
    private JLabel img = new JLabel();
    private JLabel img1 = new JLabel();
    private JLabel img2 = new JLabel();
    private JLabel dif = new JLabel();
    private JSlider difficulty;
    private Font buttonFont;
    private Font titleFont;
    FlappyBird flappyBird;

    public void modes(){
        flappyBird = new FlappyBird();
        difficulty= new JSlider(0,10,2);
        frame1 = new JFrame("Flappy Bird");
        buttonFont = new Font("Arial", Font.BOLD, 11);
        titleFont = new Font("Arial", Font.BOLD, 19);

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(500,160);
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setFont(buttonFont);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("sticker,375x360.u2.png").getImage().getScaledInstance(55, 50, Image.SCALE_DEFAULT));
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("sticker,375x360.u2-blue.png").getImage().getScaledInstance(55, 50, Image.SCALE_DEFAULT));
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("sticker,375x360.u2-green.png").getImage().getScaledInstance(55, 50, Image.SCALE_DEFAULT));

        img.setIcon(imageIcon);
        img1.setIcon(imageIcon1);
        img2.setIcon(imageIcon2);

        title.setBounds(7,1,frame1.getWidth(),40);
        img.setBounds(frame1.getWidth()/20*1+10,frame1.getHeight()/11*3+10,80,50);
        img1.setBounds(frame1.getWidth()/20*5+5,frame1.getHeight()/11*3+10,80,50);
        img2.setBounds(frame1.getWidth()/20*8+25,frame1.getHeight()/11*3+10,80,50);
        btn.setBounds(frame1.getWidth()/20*1,frame1.getHeight()/11*8,80,30);
        btn1.setBounds(frame1.getWidth()/20*5-5,frame1.getHeight()/11*8,80,30);
        btn2.setBounds(frame1.getWidth()/20*8+15,frame1.getHeight()/11*8,80,30);
        dif.setBounds(frame1.getWidth()/20*14,frame1.getHeight()/11*3+10,120,50);
        difficulty.setBounds(frame1.getWidth()/20*14,frame1.getHeight()/11*5+22,110,30);

        difficulty.setMinorTickSpacing(1);
        difficulty.setMajorTickSpacing(5);
        difficulty.setPaintTicks(true);


        btn.setFont(buttonFont);
        btn1.setFont(buttonFont);
        btn2.setFont(buttonFont);
        title.setFont(titleFont);

        title.setText("Welcome to Flappy Bird mode select!");
        btn.setText("Basic");
        btn1.setText("Blue");
        btn2.setText("Green");
        dif.setText("Difficulty/Speed");
        btn.setVisible(true);
        btn1.setVisible(true);
        btn2.setVisible(true);
        img.setVisible(true);
        img1.setVisible(true);
        img2.setVisible(true);
        difficulty.setVisible(true);
        dif.setVisible(true);

        frame1.add(difficulty);
        frame1.add(title);
        frame1.add(btn);
        frame1.add(btn1);
        frame1.add(btn2);
        frame1.add(img);
        frame1.add(img1);
        frame1.add(img2);
        frame1.add(dif);

        frame1.setVisible(true);





        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    SelectBird.getBird(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame1.setVisible(false);
                getDifficulty();
                new FlappyBird().go();

            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    SelectBird.getBird(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame1.setVisible(false);
                getDifficulty();
                new FlappyBird().go();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    SelectBird.getBird(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame1.setVisible(false);
                getDifficulty();
                new FlappyBird().go();
            }
        });

    }
    public void getDifficulty(){
        int slider = difficulty.getValue();
        switch (slider){
            case 0:
                flappyBird.setSpeed(3);
                flappyBird.setGap(150);
                break;
            case 1:
                flappyBird.setSpeed(3);
                flappyBird.setGap(120);
                break;
            case 2:
                flappyBird.setSpeed(3);
                flappyBird.setGap(90);
                break;
            case 3:
                flappyBird.setSpeed(5);
                flappyBird.setGap(90);
                System.out.println("3");
                break;
            case 4:
                flappyBird.setSpeed(5);
                flappyBird.setGap(70);
                break;
            case 5:
                flappyBird.setSpeed(7);
                flappyBird.setGap(70);
                break;
            case 6:
                flappyBird.setSpeed(7);
                flappyBird.setGap(60);
                break;
            case 7:
                flappyBird.setSpeed(8);
                flappyBird.setGap(50);
                break;
            case 8:
                flappyBird.setSpeed(8);
                flappyBird.setGap(35);
                break;
            case 9:
                flappyBird.setSpeed(9);
                flappyBird.setGap(20);
                System.out.println("9");
                break;
            case 10:
                flappyBird.setSpeed(10);
                flappyBird.setGap(7);
                System.out.println("asdasdasdasd");
                break;
        }
    }

}
