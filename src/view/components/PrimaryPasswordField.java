package view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import core.theme.Colors;
import core.theme.Fonts;

public class PrimaryPasswordField extends JPasswordField {

    public PrimaryPasswordField() {

        configurar();

    }

    private void configurar() {

        setFont(Fonts.TEXT);

        setForeground(Colors.TEXT_PRIMARY);

        setBackground(Colors.SURFACE);

        setCaretColor(Colors.PRIMARY);

        setOpaque(false);

        setBorder(new EmptyBorder(0, 16, 0, 16));

        setPreferredSize(new Dimension(250, 48));

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(Colors.SURFACE);

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                16,
                16
        );

        g2.setColor(Colors.BORDER);

        g2.drawRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                16,
                16
        );

        g2.dispose();

        super.paintComponent(g);

    }

    @Override
    public Insets getInsets() {

        return new Insets(0, 16, 0, 16);

    }

}