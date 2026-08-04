package view;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import core.theme.Colors;
import view.components.PrimaryButton;
import view.components.SectionTitle;

import javax.swing.SwingUtilities;

import view.DashboardFrame;

public class ConfiguracionPanel extends JPanel {

    public ConfiguracionPanel() {

        setLayout(null);
        setBackground(Colors.BACKGROUND);

        SectionTitle titulo = new SectionTitle("Configuración");

        titulo.setBounds(40, 30, 300, 40);

        add(titulo);

        PrimaryButton btnObjetivos = new PrimaryButton("Objetivos");
        btnObjetivos.setBounds(40, 100, 220, 50);
        add(btnObjetivos);
        
        btnObjetivos.addActionListener(e -> {

            DashboardFrame dashboard =
                    (DashboardFrame) SwingUtilities.getWindowAncestor(this);

            dashboard.navegar("OBJETIVOS");

        });

        PrimaryButton btnNiveles = new PrimaryButton("Niveles");
        btnNiveles.setBounds(40, 170, 220, 50);
        add(btnNiveles);

        btnNiveles.addActionListener(e -> {

            DashboardFrame dashboard =
                    (DashboardFrame) SwingUtilities.getWindowAncestor(this);

            dashboard.navegar("NIVELES");

        });

        PrimaryButton btnGrupos = new PrimaryButton("Grupos Musculares");
        btnGrupos.setBounds(40, 240, 220, 50);
        add(btnGrupos);

        btnGrupos.addActionListener(e -> {

            DashboardFrame dashboard =
                    (DashboardFrame) SwingUtilities.getWindowAncestor(this);

            dashboard.navegar("GRUPOS_MUSCULARES");

        });

    }

}