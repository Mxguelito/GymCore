package view.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

import core.theme.Colors;
import utils.UIConstants;

public class MenuButton extends JButton {

    private boolean seleccionado = false;

    public MenuButton(String texto) {

        super(texto);

        configurarBoton();

    }

    private void configurarBoton() {

        setFont(UIConstants.NORMAL_FONT);

        setFocusPainted(false);

        setContentAreaFilled(false);

        setBorderPainted(false);

        setOpaque(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(260, 44));

        setMaximumSize(new Dimension(260, 44));

        setForeground(Color.BLACK);

    }

    public void setSeleccionado(boolean seleccionado) {

        this.seleccionado = seleccionado;

        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (seleccionado) {

            g2.setColor(new Color(60, 60, 200));

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    18,
                    18
            );

            g2.setColor(Color.WHITE);

            g2.fillRoundRect(
                    0,
                    0,
                    6,
                    getHeight(),
                    18,
                    18
            );

            setForeground(Color.WHITE);

        } else {

            g2.setColor(Color.WHITE);

            g2.setColor(Color.WHITE);

            g2.fillRoundRect(
                    4,
                    6,
                    6,
                    getHeight() - 12,
                    10,
                    10
            );

            g2.setColor(new Color(220,220,220));

            g2.setStroke(new BasicStroke(1));

            g2.drawRoundRect(
                    0,
                    0,
                    getWidth()-1,
                    getHeight()-1,
                    18,
                    18
            );

            setForeground(Color.BLACK);

        }

        FontMetrics fm = g2.getFontMetrics();

        int x = (getWidth()-fm.stringWidth(getText()))/2;

        int y = (getHeight()+fm.getAscent()-fm.getDescent())/2;

        g2.setColor(getForeground());

        g2.drawString(getText(), x, y);

        g2.dispose();

    }

}