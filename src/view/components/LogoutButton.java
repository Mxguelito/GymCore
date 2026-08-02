package view.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class LogoutButton extends JButton {

    public LogoutButton() {

        super("⏻");

        configurar();

    }

    private void configurar() {

        setPreferredSize(new Dimension(52, 52));
        setMaximumSize(new Dimension(52, 52));

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setForeground(Color.WHITE);

        setFont(getFont().deriveFont(24f));

        setToolTipText("Cerrar sesión");

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        
        
        
        if (getModel().isRollover()) {

            g2.setColor(new Color(220, 50, 50));

            g2.fillOval(0, 0, getWidth(), getHeight());

        } else {

            g2.setColor(new Color(255,255,255,40));

            g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);

        }

        g2.setColor(Color.WHITE);

        g2.setStroke(new BasicStroke(2));

        g2.drawOval(2, 2, getWidth() - 4, getHeight() - 4);

        
        
        super.paintComponent(g);

        g2.dispose();

    }

}