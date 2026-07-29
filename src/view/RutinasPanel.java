package view;


import view.components.BasePanel;
import view.components.SectionTitle;


public class RutinasPanel extends BasePanel {

    public RutinasPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Rutinas"));

    }

}