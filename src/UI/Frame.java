package UI;

import javax.swing.*;
import java.awt.*;

public abstract class Frame extends JFrame {
    public Frame() {
        this("Email Spammer");
    }

    public Frame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(350, 300));
    }

    @Override
    public void pack() {
        super.pack();
        setLocation(500,200);
        setVisible(true);
    }

    public abstract void draw();
}
