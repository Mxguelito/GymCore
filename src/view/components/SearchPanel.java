package view.components;

public class SearchPanel extends BasePanel {
	
	private PrimaryTextField txtBuscar;

	private PrimaryButton btnNuevo;

    public SearchPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        crearTitulo();

        crearCampoBusqueda();
        
        crearBotonNuevo();

    }

    private void crearTitulo() {

        PrimaryLabel lblBuscar = new PrimaryLabel("Buscar");

        lblBuscar.setBounds(20, 15, 100, 30);

        add(lblBuscar);

    }
    
    private void crearCampoBusqueda() {

    	txtBuscar = new PrimaryTextField();

        txtBuscar.setBounds(20, 50, 300, 35);

        add(txtBuscar);

    }
    
    private void crearBotonNuevo() {

    	btnNuevo = new PrimaryButton("Nuevo");
    	
        btnNuevo.setBounds(340, 50, 120, 35);

        add(btnNuevo);

    }
    
    public PrimaryTextField getTxtBuscar() {

        return txtBuscar;

    }

    public PrimaryButton getBtnNuevo() {

        return btnNuevo;

    }

}