package view.components;

import core.theme.Colors;
import core.theme.Fonts;

public class FooterPanel extends BasePanel {

    public FooterPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        setBackground(Colors.PRIMARY);

        PrimaryLabel lblEstado =
                new PrimaryLabel("GymCore v1.0");

        lblEstado.setFont(Fonts.TEXT);

        lblEstado.setBounds(20, 10, 200, 20);

        add(lblEstado);

    }

}