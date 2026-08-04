package view.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import core.theme.Colors;
import core.theme.Fonts;
import model.Usuario;
import utils.UIConstants;

public class HeaderPanel extends BasePanel {

    private final Usuario usuario;

    public HeaderPanel(Usuario usuario) {

        this.usuario = usuario;

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        configurarHeader();

        crearTitulo();

       // crearUsuario();

    }

    private void configurarHeader() {

        setLayout(new BorderLayout());

        setBounds(
                0,
                0,
                UIConstants.WINDOW_WIDTH,
                UIConstants.HEADER_HEIGHT
        );

        setBackground(Colors.PRIMARY);

    }
    
    private void crearTitulo() {

    }

    private void crearUsuario() {

        JPanel panelUsuario = new JPanel();

        panelUsuario.setOpaque(false);

        panelUsuario.setLayout(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        20,
                        20
                )
        );

        PrimaryLabel lblUsuario =
                new PrimaryLabel(usuario.getUsername());

        lblUsuario.setFont(Fonts.TEXT);

        panelUsuario.add(lblUsuario);

        add(panelUsuario, BorderLayout.EAST);

    }

}