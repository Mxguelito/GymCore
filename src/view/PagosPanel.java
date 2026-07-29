package view;


import view.components.BasePanel;
import view.components.SectionTitle;


public class PagosPanel extends BasePanel {

    public PagosPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Pagos"));

    }

}