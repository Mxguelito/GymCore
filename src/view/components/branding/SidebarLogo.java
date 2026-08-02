package view.components.branding;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;

import core.theme.Colors;
import core.theme.Fonts;
import view.components.BasePanel;
import view.components.PrimaryLabel;

public class SidebarLogo extends BasePanel {
	
	private final boolean loginMode;

	public SidebarLogo() {

	    this(false);

	}

	public SidebarLogo(boolean loginMode) {

	    this.loginMode = loginMode;

	    setOpaque(false);

	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	    setAlignmentX(Component.CENTER_ALIGNMENT);

	    crearLogo();

	}

    private void crearLogo() {

        LogoBadge badge = new LogoBadge();

        badge.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(8));

        add(badge);

        add(Box.createVerticalStrut(8));

        PrimaryLabel titulo = new PrimaryLabel("GYMCORE");

        titulo.setFont(Fonts.TITLE);

        if (loginMode) {
            titulo.setForeground(Colors.TEXT_PRIMARY);
        } else {
            titulo.setForeground(Colors.SURFACE);
        }

        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titulo);

        add(Box.createVerticalStrut(4));

        PrimaryLabel subtitulo = new PrimaryLabel("Fitness Management");

        if (loginMode) {
            subtitulo.setForeground(Colors.TEXT_SECONDARY);
        } else {
            subtitulo.setForeground(Colors.SURFACE);
        }

        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(subtitulo);

        add(Box.createVerticalStrut(8));

    }

}