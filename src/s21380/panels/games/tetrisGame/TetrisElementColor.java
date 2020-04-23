package s21380.panels.games.tetrisGame;


import java.awt.*;
import java.util.Random;

public enum TetrisElementColor {

    RED(new Color(180,0,0)),
    BLUE(new Color(0,0,180)),
    GREEN(new Color(0,180,0)),
    YELLOW(new Color(180,180,0)),
    BLACK(new Color(0,0,0)),
    ORANGE(new Color(240,94,32));

    Color color;// = new Color(0);

    public static TetrisElementColor generateTetrisElementColor(){
        return values()[new Random().nextInt(values().length)];
    }

    TetrisElementColor(Color color){
        this.color =color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TetrisElementColor{" +
                "color=" + color +
                '}';
    }
}
