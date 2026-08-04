package view.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import core.theme.Colors;

public class InfoPanel extends JPanel {

    private JPanel panelContenido;

    public InfoPanel(String titulo) {

        setLayout(null);

        setOpaque(false);

        setSize(420, 300);

        JPanel lineaSuperior = new JPanel();

        lineaSuperior.setBackground(Colors.PRIMARY);

        lineaSuperior.setBounds(0, 0, 1000, 6);

        add(lineaSuperior);

        SectionTitle lblTitulo = new SectionTitle(titulo);

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        lblTitulo.setBounds(25, 22, 300, 35);

        add(lblTitulo);

        panelContenido = new JPanel();

        panelContenido.setOpaque(false);

        panelContenido.setLayout(null);

        panelContenido.setBounds(
                20,
                70,
                380,
                210
        );

        add(panelContenido);

    }

    public JPanel getContentPanel() {

        return panelContenido;

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // sombra

        g2.setColor(new Color(0, 0, 0, 18));

        g2.fillRoundRect(
                6,
                8,
                getWidth() - 12,
                getHeight() - 12,
                22,
                22
        );

        // fondo

        g2.setColor(Color.WHITE);

        g2.fillRoundRect(
                0,
                0,
                getWidth() - 12,
                getHeight() - 12,
                22,
                22
        );

        // borde

        g2.setColor(Colors.BORDER);

        g2.setStroke(new BasicStroke(1f));

        g2.drawRoundRect(
                0,
                0,
                getWidth() - 12,
                getHeight() - 12,
                22,
                22
        );

        g2.dispose();

        super.paintComponent(g);

    }

}