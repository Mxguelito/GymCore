package view.components.branding;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class LogoBadge extends JPanel {

    public LogoBadge() {

        setOpaque(false);

        setPreferredSize(new Dimension(90, 70));

        setMaximumSize(new Dimension(90, 70));

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(Color.WHITE);

        g2.fillRoundRect(5, 5, 80, 55, 18, 18);

        g2.setColor(new Color(230,230,230));

        g2.drawRoundRect(5,5,80,55,18,18);

        g2.setColor(new Color(90,90,220));

        g2.setFont(new Font("Segoe UI", Font.BOLD, 26));

        g2.drawString("GC",23,41);

    }

}