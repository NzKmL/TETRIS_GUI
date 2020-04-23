package s21380;

import s21380.frames.MyFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame().initMyFrame();
            }
        });
    }
}
