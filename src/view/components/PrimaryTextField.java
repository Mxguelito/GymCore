package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.theme.Colors;
import core.theme.Fonts;

public class PrimaryTextField extends JTextField {

    public PrimaryTextField() {

        configurar();

    }

    private void configurar() {

    	setFont(Fonts.TEXT);

    	setForeground(Colors.TEXT_PRIMARY);

    	setBackground(Colors.SURFACE);

        setBorder(new EmptyBorder(10, 10, 10, 10));

        setPreferredSize(new Dimension(250, 45));

    }

}