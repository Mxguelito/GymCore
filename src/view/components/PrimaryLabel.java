package view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import core.theme.Colors;
import core.theme.Fonts;

public class PrimaryLabel extends JLabel {

    public PrimaryLabel(String texto) {

        super(texto);

        configurar();

    }

    private void configurar() {

    	setFont(Fonts.TEXT);

    	setForeground(Colors.TEXT_PRIMARY);

    }

}