package s21380.panels.games.score;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private int hight;
    private int weight;

    private final int rowsNumber =3;
    private final int columnNumbers =2;
    private final int border=3;
    private final int rowNumbers=2;

    private JLabel tempScore ;
    private JLabel bestScore;
    private JLabel tempScoreNumb;
    private JLabel bestScoreNumb;
    private JLabel levelLabel;
    private JLabel levelNumb;

    public ScorePanel( int weight, int hight){
        this.hight = hight;
        this.weight = weight;
        this.setPreferredSize(new Dimension(weight, hight));

        this.setLayout(new GridLayout(rowsNumber, columnNumbers, border, border));
        this.setBorder(BorderFactory.createEmptyBorder(border, border, border, border));

            tempScore = new JLabel("Twój Wynik");
            bestScoreNumb = new JLabel("0");
            bestScore =new JLabel("Najlepszy Wynik");
            tempScoreNumb =new JLabel("0");
            levelLabel =new JLabel("Poziom");
            levelNumb =new JLabel("1");

        this.add(bestScore);
        this.add(bestScoreNumb);
        this.add(tempScore);
        this.add(tempScoreNumb);
        this.add(levelLabel);
        this.add(levelNumb);
    }

    public void setValueOfBestScore(int value){
        bestScoreNumb.setText(Integer.toString(value));
    }
    public void setValueOfBestScore(String value){
        bestScoreNumb.setText(value);
    }
    public void setValueOfLevel(String value){
        levelNumb.setText(value);
    }

    public void setValueOfTempScore(int value){
        tempScoreNumb.setText(Integer.toString(value));
    }
}
