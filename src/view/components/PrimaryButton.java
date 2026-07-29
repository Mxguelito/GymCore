package view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import core.theme.Colors;
import core.theme.Fonts;

public class PrimaryButton extends JButton {

    public PrimaryButton(String texto) {

        super(texto);

        configurar();

    }

    private void configurar() {

    	setBackground(Colors.PRIMARY);

    	setForeground(Colors.SURFACE);

        setFocusPainted(false);

        setBorderPainted(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setFont(Fonts.BUTTON);

        setPreferredSize(new Dimension(180, 45));

    }

}