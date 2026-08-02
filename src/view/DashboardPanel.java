package view;

import view.components.BasePanel;
import view.components.SectionTitle;
import view.components.DashboardCard;

import service.DashboardService;



public class DashboardPanel extends BasePanel {

	private DashboardService service;
	private DashboardCard cardClientes;
	private DashboardCard cardEntrenadores;
	private DashboardCard cardPagos;
    
    

	public DashboardPanel() {

	    service = new DashboardService();

	    inicializarComponentes();

	}

    private void inicializarComponentes() {

        add(new SectionTitle("Bienvenido a GymCore"));

        cardClientes =
                new DashboardCard(
                        "Clientes",
                        String.valueOf(service.obtenerCantidadClientes())
                );

        cardClientes.setLocation(40,90);

        add(cardClientes);

        cardEntrenadores =
                new DashboardCard(
                        "Entrenadores",
                        String.valueOf(service.obtenerCantidadEntrenadores())
                );

        cardEntrenadores.setLocation(290,90);

        add(cardEntrenadores);

        cardPagos =
        		new DashboardCard(
        			    "Pagos",
        			    String.valueOf(service.obtenerTotalPagos())
        			);

        cardPagos.setLocation(540,90);

        add(cardPagos);

    }

}