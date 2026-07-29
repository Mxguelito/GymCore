package view;


import view.components.BasePanel;

import view.components.SectionTitle;

public class DashboardPanel extends BasePanel {

    public DashboardPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Bienvenido a GymCore"));

    }

}