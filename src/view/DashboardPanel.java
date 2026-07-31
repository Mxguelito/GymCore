package view;

import view.components.BasePanel;
import view.components.SectionTitle;
import view.components.DashboardCard;

public class DashboardPanel extends BasePanel {

    private DashboardCard cardClientes;
    private DashboardCard cardEntrenadores;
    private DashboardCard cardPagos;

    public DashboardPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Bienvenido a GymCore"));

        cardClientes =
                new DashboardCard("Clientes", "3");

        cardClientes.setLocation(40,90);

        add(cardClientes);

        cardEntrenadores =
                new DashboardCard("Entrenadores", "1");

        cardEntrenadores.setLocation(290,90);

        add(cardEntrenadores);

        cardPagos =
                new DashboardCard("Pagos", "$0");

        cardPagos.setLocation(540,90);

        add(cardPagos);

    }

}