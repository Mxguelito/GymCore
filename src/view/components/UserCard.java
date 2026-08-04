package view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import core.theme.Fonts;
import model.Usuario;

public class UserCard extends JPanel {

    public UserCard(Usuario usuario) {

        setOpaque(true);

        // Fondo integrado con el sidebar
        setBackground(new Color(72, 64, 170));

        setBorder(BorderFactory.createCompoundBorder(

                BorderFactory.createLineBorder(
                        new Color(130,120,255),
                        1,
                        true
                ),

                BorderFactory.createEmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        ));

        setMaximumSize(new Dimension(235,95));
        setPreferredSize(new Dimension(235,95));

        setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //-----------------------------------------
        // Línea gamer
        //-----------------------------------------

        JPanel linea = new JPanel();

        linea.setBackground(new Color(0,255,220));

        linea.setMaximumSize(new Dimension(210,3));

        linea.setPreferredSize(new Dimension(210,3));

        add(linea);

        add(Box.createVerticalStrut(10));

        //-----------------------------------------
        // Usuario
        //-----------------------------------------

        PrimaryLabel usuarioLbl =
                new PrimaryLabel("▶ " + usuario.getUsername().toUpperCase());

        usuarioLbl.setAlignmentX(CENTER_ALIGNMENT);

        usuarioLbl.setFont(Fonts.BUTTON);

        usuarioLbl.setForeground(new Color(0,255,220));

        add(usuarioLbl);

        add(Box.createVerticalStrut(6));

        //-----------------------------------------
        // Rol
        //-----------------------------------------

        PrimaryLabel rolLbl =
                new PrimaryLabel("● " + usuario.getRol().getNombre());

        rolLbl.setAlignmentX(CENTER_ALIGNMENT);

        rolLbl.setFont(Fonts.SMALL);

        rolLbl.setForeground(new Color(180,255,200));

        add(rolLbl);

    }

}