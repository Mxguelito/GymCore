package view.components;

import java.awt.Dimension;

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

        setBorder(new EmptyBorder(10, 10, 10, 10));

        setPreferredSize(new Dimension(250, 45));

    }

}