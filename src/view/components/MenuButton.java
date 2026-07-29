package view.components;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

import utils.UIConstants;

public class MenuButton extends JButton {

    public MenuButton(String texto) {

        super(texto);

        configurarBoton();

    }

    private void configurarBoton() {

        setFont(UIConstants.NORMAL_FONT);

        setFocusPainted(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(220, 45));

        setMaximumSize(new Dimension(220, 45));

    }

}