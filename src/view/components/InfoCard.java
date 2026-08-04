package view.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import core.theme.Colors;

public class InfoCard extends JPanel {

    private PrimaryLabel lblTitulo;
    private PrimaryLabel lblValor;

    public InfoCard(String titulo, String valor) {

        setLayout(null);

        setOpaque(false);

        setSize(260, 100);

        lblTitulo = new PrimaryLabel(titulo);

        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        lblTitulo.setForeground(new Color(120,120,120));

        lblTitulo.setBounds(20,15,210,22);

        add(lblTitulo);

        lblValor = new PrimaryLabel(valor);

        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 24));

        lblValor.setForeground(new Color(35,35,35));

        lblValor.setBounds(20,45,220,35);

        add(lblValor);

    }

    public void setValor(String valor) {

        lblValor.setText(valor);

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // sombra

        g2.setColor(new Color(0,0,0,20));

        g2.fillRoundRect(
                6,
                8,
                getWidth()-10,
                getHeight()-10,
                20,
                20
        );

        // fondo

        g2.setColor(Color.WHITE);

        g2.fillRoundRect(
                0,
                0,
                getWidth()-10,
                getHeight()-10,
                20,
                20
        );

        // borde

        g2.setColor(new Color(230,230,235));

        g2.setStroke(new BasicStroke(1f));

        g2.drawRoundRect(
                0,
                0,
                getWidth()-10,
                getHeight()-10,
                20,
                20
        );

        // línea superior

        g2.setColor(Colors.PRIMARY);

        g2.fillRoundRect(
                0,
                0,
                getWidth()-10,
                6,
                20,
                20
        );

        g2.dispose();

        super.paintComponent(g);

    }

}