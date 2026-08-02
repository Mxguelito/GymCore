package view.components;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import core.theme.Colors;

public class LinkLabel extends JLabel {

    public LinkLabel(String texto) {

        super(texto);

        setForeground(Colors.PRIMARY);

        setFont(new Font("Segoe UI", Font.BOLD, 15));

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                setForeground(Colors.PRIMARY_DARK);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                setForeground(Colors.PRIMARY);

            }

        });

    }

}