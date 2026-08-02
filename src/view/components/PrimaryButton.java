package view.components;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

import core.theme.Colors;
import core.theme.Fonts;

public class PrimaryButton extends JButton {

    public PrimaryButton(String texto) {

        super(texto);

        configurar();

    }

    private void configurar() {

        setFocusPainted(false);

        setContentAreaFilled(false);

        setBorderPainted(false);

        setOpaque(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setFont(Fonts.BUTTON);

        setForeground(Colors.SURFACE);

        setPreferredSize(new Dimension(180, 48));

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(Colors.PRIMARY);

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                18,
                18
        );

        super.paintComponent(g);

        g2.dispose();

    }

}