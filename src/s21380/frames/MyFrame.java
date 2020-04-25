package s21380.frames;

import s21380.panels.GamePanel;
import s21380.panels.LogoPanel;
import s21380.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;


public class MyFrame extends JFrame{

    public final int WIDTH = 300;
    public final int HIGHT = 700;


    JPanel mainPanel;
    MenuPanel menuPanel;
    GamePanel gamePanel;
    JPanel tetrisImage;

    public MyFrame(){

        super("TETRIS GUI PROJECT");

        this.setSize(new Dimension(WIDTH,HIGHT));
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo( null );
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void initMyFrame() {
        mainPanel = new JPanel();

        tetrisImage = new LogoPanel(WIDTH, HIGHT/8);
        gamePanel = new GamePanel(WIDTH, HIGHT * 3/4);

        menuPanel = new MenuPanel((int) (WIDTH*(0.8)),HIGHT/16);

        menuPanel.getStartButton().addActionListener(actionEvent -> gamePanel.startGame());
        menuPanel.getExitButton().addActionListener(e->{
            gamePanel.gameOver();
            System.exit(0);
            });


        menuPanel.getStartButton().addKeyListener(gamePanel);

        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(tetrisImage);
        mainPanel.add(gamePanel);
        mainPanel.add(menuPanel);

        this.addKeyListener(gamePanel);
        this.add(mainPanel);
    }



}


