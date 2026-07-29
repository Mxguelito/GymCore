package view.components;

import core.theme.Fonts;

public class SectionTitle extends PrimaryLabel {

    public SectionTitle(String titulo) {

        super(titulo);

        setFont(Fonts.TITLE);

        setBounds(40, 40, 400, 40);

    }

}