package s21380.panels;

import s21380.panels.panelsElements.MenuButton;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel  {

    private int hight;
    private int weight;
    private final int COLUMNNUMBER =2;
    private final int BORDER =10;
    private final int ROWNUMBERS =1;
    JButton startButton;

    JButton exitButton;
    public MenuPanel( int weight, int hight){

        this.hight = hight;
        this.weight = weight;
        this.setPreferredSize(new Dimension(weight, hight));

        this.setLayout(new GridLayout(1, COLUMNNUMBER, BORDER, BORDER));
        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

         startButton = new MenuButton("Start",weight/ COLUMNNUMBER - BORDER *(COLUMNNUMBER -1),
                hight/ ROWNUMBERS - BORDER *(ROWNUMBERS -1));

         exitButton =  new MenuButton("Wyjście",weight/ COLUMNNUMBER - BORDER *(COLUMNNUMBER -1),
                hight/ ROWNUMBERS - BORDER *(ROWNUMBERS -1));


         this.add(startButton);
        this.add(exitButton);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }



}
