package view.components;

public class SearchPanel extends BasePanel {
	
	private PrimaryTextField txtBuscar;

	

    public SearchPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        crearTitulo();

        crearCampoBusqueda();
        
       

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
    
    
    
    public PrimaryTextField getTxtBuscar() {

        return txtBuscar;

    }

    
}