package s21380.panels;

import s21380.panels.games.GraphicGamePanel;
import s21380.panels.games.score.ScoreFileProcessor;
import s21380.panels.games.score.ScorePanel;
import s21380.panels.games.tetrisGame.TetrisGraphicGamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{
    private final int FPS = 60;

    private GraphicGamePanel graphicGamePanel;
    private ScorePanel scorePanel;
    private boolean refreshStarted=false;
    public GamePanel(int width, int hight) {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(width,hight));
            graphicGamePanel = new TetrisGraphicGamePanel(width, (int)(hight*10/12), 10, 20);
            scorePanel = new ScorePanel((int) (width*0.6), hight*1/12);
            scorePanel.setValueOfBestScore(ScoreFileProcessor.getInstance().getBestScore());
        this.add(graphicGamePanel);
        this.add(scorePanel);

   }

    private void refreshByFPS(int FPS) {

        scorePanel.setValueOfBestScore(ScoreFileProcessor.getInstance().getBestScore());
        if(!refreshStarted){
        long delay = 1000/FPS;

        Thread thread = new Thread(()->{
            while(true){
                this.repaint();
                    scorePanel.setValueOfTempScore(graphicGamePanel.getGameScore());
                    scorePanel.setValueOfLevel(graphicGamePanel.getLevel());
                try {
                        Thread.sleep(delay);
                } catch (InterruptedException e) {
                   System.err.println("FPSThread problem");
                } ;
            }


        });
        thread.start();
            refreshStarted=true;}
    }

    public void startGame(){
        graphicGamePanel.startGame();
        refreshByFPS(FPS);
    }

    public  void paintComponent(Graphics g){
        super.paintComponent(g);
        graphicGamePanel.paintComponent(g);
    };
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        graphicGamePanel.keyTyped(keyEvent);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        graphicGamePanel.keyPressed(keyEvent);

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        graphicGamePanel.keyReleased(keyEvent);
    }

    public void gameOver() {
        graphicGamePanel.gameOver();
    }
}

