package view;


import view.components.BasePanel;
import view.components.SectionTitle;


public class EntrenadoresPanel extends BasePanel {

    public EntrenadoresPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Entrenadores"));

    }

}