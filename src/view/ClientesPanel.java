package view;


import view.components.BasePanel;
import view.components.SectionTitle;
import view.components.SearchPanel;
import core.constants.LayoutConstants;
import view.components.PrimaryTable;
import view.tablemodels.ClienteTableModel;

import view.dialogs.ClienteDialog;
import controller.ClienteController;

import view.components.CrudButtons;

import model.Cliente;

import javax.swing.JOptionPane;

import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;



public class ClientesPanel extends BasePanel {
	
	private SearchPanel searchPanel;
	
	private PrimaryTable tablaClientes;
	
	private ClienteController clienteController;

	private ClienteTableModel modelo;
	
	private TableRowSorter<ClienteTableModel> sorter;
	
	private CrudButtons crudButtons;
	

    public ClientesPanel() {

    	
    	clienteController = new ClienteController();
        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Clientes"));

        crearSearchPanel();

        crearCrudButtons();

        crearTabla();
        configurarBuscador();

        configurarEventos();
    }
    
    private void crearSearchPanel() {

        searchPanel = new SearchPanel();

        searchPanel.setBounds(40, 100, 500, 100);

        add(searchPanel);

    }
    private void crearCrudButtons() {

        crudButtons = new CrudButtons();

        crudButtons.setBounds(40, 210, 430, 45);

        add(crudButtons);

    }
    
    private void crearTabla() {

        tablaClientes = new PrimaryTable();

        modelo = new ClienteTableModel();

        modelo.cargarClientes(
                clienteController.listar()
        );

        tablaClientes.getTable().setModel(modelo);
        sorter = new TableRowSorter<>(modelo);

        tablaClientes.getTable().setRowSorter(sorter);

        tablaClientes.setBounds(

                LayoutConstants.PADDING,

                LayoutConstants.TABLE_Y + 60,

                LayoutConstants.TABLE_WIDTH,

                LayoutConstants.TABLE_HEIGHT

        );

        add(tablaClientes);

    }
    private void configurarBuscador() {

        searchPanel.getTxtBuscar().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }

            private void filtrar() {

                String texto = searchPanel.getTxtBuscar().getText();

                if (texto.isBlank()) {

                    sorter.setRowFilter(null);

                } else {

                    sorter.setRowFilter(
                            RowFilter.regexFilter("(?i)" + texto)
                    );

                }

            }

        });

    }
    
    private void actualizarTabla() {

        modelo.cargarClientes(
                clienteController.listar()
        );

    }
    
    private void editarCliente() {

    	int filaVista = tablaClientes.getSelectedRow();

    	if (filaVista == -1) {
    	    return;
    	}

    	int filaModelo = tablaClientes.getTable().convertRowIndexToModel(filaVista);

    	Cliente cliente = modelo.getCliente(filaModelo);

        ClienteDialog dialog = new ClienteDialog(cliente);

        dialog.setVisible(true);

        actualizarTabla();

    }
    
    private void eliminarCliente() {

        int fila = tablaClientes.getSelectedRow();

        if (fila == -1) {
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(

                this,

                "¿Desea desactivar este cliente?",

                "Confirmar",

                JOptionPane.YES_NO_OPTION

        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        int filaVista = tablaClientes.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int filaModelo = tablaClientes.getTable().convertRowIndexToModel(filaVista);

        Cliente cliente = modelo.getCliente(filaModelo);

        clienteController.eliminar(cliente.getIdCliente());

        actualizarTabla();

    }
    
    private void configurarEventos() {

        crudButtons.getBtnNuevo().addActionListener(e -> {

            ClienteDialog dialog = new ClienteDialog();

            dialog.setVisible(true);

            actualizarTabla();

        });

        crudButtons.getBtnEditar().addActionListener(e -> {

            editarCliente();

        });

        crudButtons.getBtnEliminar().addActionListener(e -> {

            eliminarCliente();

        });

    }

}