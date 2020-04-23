package s21380.panels.games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public abstract class GraphicGamePanel extends JPanel implements KeyListener {
    protected int hight;
    protected int width;
    protected int gameScore;
    protected int level;

    public GraphicGamePanel(int width, int hight) {
        this.hight = hight;
        this.width = width;

    }

    public  void paintComponent(Graphics g){
        super.paintComponent(g);
    };

    public int getGameScore() {
        return gameScore;
    }

    public abstract void startGame();

    public abstract void gameOver();

    public String getLevel() {
            return String.valueOf(level);
        }
};


