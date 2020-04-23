package s21380.panels.panelsElements;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {
    public MenuButton(String text, Integer weight, Integer hight) {
        super(text);
        super.setPreferredSize(new Dimension(weight, hight));


    }
}
