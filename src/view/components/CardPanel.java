package view.components;

import javax.swing.BorderFactory;

import core.theme.Colors;
import core.theme.Fonts;

public class CardPanel extends BasePanel {

    private final PrimaryLabel lblTitulo;

    public CardPanel(String titulo) {

        configurarCard();

        lblTitulo = new PrimaryLabel(titulo);

        configurarTitulo();

    }

    private void configurarCard() {

        setBackground(Colors.SURFACE);

        setBorder(
                BorderFactory.createLineBorder(
                        Colors.BORDER,
                        1
                )
        );

    }

    private void configurarTitulo() {

        lblTitulo.setFont(Fonts.TITLE);

        lblTitulo.setBounds(20, 20, 300, 30);

        add(lblTitulo);

    }

}