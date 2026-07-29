package view;


import view.components.BasePanel;
import view.components.SectionTitle;
import view.components.SearchPanel;
import core.constants.LayoutConstants;
import view.components.PrimaryTable;
import view.tablemodels.ClienteTableModel;

import view.dialogs.ClienteDialog;


public class ClientesPanel extends BasePanel {
	
	private SearchPanel searchPanel;
	
	private PrimaryTable tablaClientes;

    public ClientesPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Clientes"));

        crearSearchPanel();

        crearTabla();
       

        configurarEventos();

    }
    
    private void crearSearchPanel() {

        searchPanel = new SearchPanel();

        searchPanel.setBounds(40, 100, 500, 100);

        add(searchPanel);

    }
    
    private void crearTabla() {

        tablaClientes = new PrimaryTable();
        
        tablaClientes.getTable().setModel(
                new ClienteTableModel()
        );

        tablaClientes.setBounds(

        	    LayoutConstants.PADDING,

        	    LayoutConstants.TABLE_Y,

        	    LayoutConstants.TABLE_WIDTH,

        	    LayoutConstants.TABLE_HEIGHT

        	);

        add(tablaClientes);

    }

    
    private void configurarEventos() {

    	searchPanel.getBtnNuevo().addActionListener(e -> {

    	    ClienteDialog dialog = new ClienteDialog();

    	    dialog.setVisible(true);

    	});

    }

}