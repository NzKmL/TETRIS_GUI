package s21380.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoPanel extends JPanel {
    JLabel picLabel;
    BufferedImage image;
    public LogoPanel(int width, int hight) {
        this.setPreferredSize(new Dimension(width, hight));
            initLogoPanel();
        this.add(picLabel);

    }

    private void initLogoPanel() {
        try {
            image = ImageIO.read(new File("resources/LOGO.png"));
        } catch (IOException e) {
            System.err.println("Load LogoPanel image error");
            e.printStackTrace();
        }

        picLabel = new JLabel(new ImageIcon(image));
    }
}
