package gui;

import javax.swing.*;
import java.awt.*;

public class GameCell extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillRect(0, 0, 50, 50);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}
