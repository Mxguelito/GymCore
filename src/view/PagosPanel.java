package view;

import controller.PagoController;

import view.components.BasePanel;
import view.components.CrudButtons;
import view.components.PrimaryTable;
import view.components.SearchPanel;
import view.components.SectionTitle;

import view.tablemodels.PagoTableModel;

import core.constants.LayoutConstants;

import javax.swing.table.TableRowSorter;

import model.Pago;

import view.dialogs.PagoDialog;

public class PagosPanel extends BasePanel {

    private SearchPanel searchPanel;

    private CrudButtons crudButtons;

    private PrimaryTable tablaPagos;

    private PagoController controller;

    private PagoTableModel modelo;

    private TableRowSorter<PagoTableModel> sorter;

    public PagosPanel() {

        controller = new PagoController();

        inicializarComponentes();

        configurarBuscador();

        configurarEventos();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Pagos"));

        crearSearchPanel();

        crearCrudButtons();

        crearTabla();

    }
    
    private void crearSearchPanel() {

        searchPanel = new SearchPanel();

        searchPanel.setBounds(
                40,
                100,
                500,
                100
        );

        add(searchPanel);

    }
    
    
    private void crearCrudButtons() {

        crudButtons = new CrudButtons();

        crudButtons.setBounds(
                40,
                210,
                430,
                45
        );

        add(crudButtons);

    }
    
    private void crearTabla() {

        tablaPagos = new PrimaryTable();

        modelo = new PagoTableModel();

        modelo.cargarPagos(
                controller.listar()
        );

        tablaPagos.getTable().setModel(modelo);

        sorter = new TableRowSorter<>(modelo);

        tablaPagos.getTable().setRowSorter(sorter);

        tablaPagos.setBounds(

                LayoutConstants.PADDING,

                LayoutConstants.TABLE_Y + 60,

                LayoutConstants.TABLE_WIDTH,

                LayoutConstants.TABLE_HEIGHT

        );

        add(tablaPagos);

    }
    
    private void configurarBuscador() {

        searchPanel.getTxtBuscar()
                .getDocument()
                .addDocumentListener(new javax.swing.event.DocumentListener() {

                    @Override
                    public void insertUpdate(javax.swing.event.DocumentEvent e) {

                        filtrar();

                    }

                    @Override
                    public void removeUpdate(javax.swing.event.DocumentEvent e) {

                        filtrar();

                    }

                    @Override
                    public void changedUpdate(javax.swing.event.DocumentEvent e) {

                        filtrar();

                    }

                    private void filtrar() {

                        String texto =
                                searchPanel.getTxtBuscar().getText();

                        if (texto.isBlank()) {

                            sorter.setRowFilter(null);

                        } else {

                            sorter.setRowFilter(

                                    javax.swing.RowFilter.regexFilter(

                                            "(?i)" + texto

                                    )

                            );

                        }

                    }

                });

    }
    
    private void actualizarTabla() {

        modelo.cargarPagos(

                controller.listar()

        );

    }
    
    private void configurarEventos() {

    	crudButtons.getBtnNuevo().addActionListener(e -> {

    	    PagoDialog dialog =
    	            new PagoDialog();

    	    dialog.setVisible(true);

    	    Pago pago =
    	            dialog.getPago();

    	    if (pago != null) {

    	        try {

    	            controller.guardar(pago);

    	            javax.swing.JOptionPane.showMessageDialog(
    	                    null,
    	                    "Guardó correctamente"
    	            );

    	            actualizarTabla();

    	        } catch (Exception ex) {

    	            ex.printStackTrace();

    	            javax.swing.JOptionPane.showMessageDialog(
    	                    null,
    	                    ex.getMessage()
    	            );

    	        }

    	    }

    	});

    	crudButtons.getBtnEditar().addActionListener(e -> {

    	    Pago pago = obtenerPagoSeleccionado();

    	    if (pago == null) {
    	        return;
    	    }

    	    PagoDialog dialog = new PagoDialog(pago);

    	    dialog.setVisible(true);

    	    Pago actualizado = dialog.getPago();

    	    if (actualizado != null) {

    	        controller.actualizar(actualizado);

    	        actualizarTabla();

    	    }

    	});
    	crudButtons.getBtnEliminar().addActionListener(e -> {

    	    Pago pago = obtenerPagoSeleccionado();

    	    if (pago == null) {

    	        return;

    	    }

    	    int opcion = javax.swing.JOptionPane.showConfirmDialog(

    	            this,

    	            "¿Desea eliminar este pago?",

    	            "Confirmar",

    	            javax.swing.JOptionPane.YES_NO_OPTION

    	    );

    	    if (opcion == javax.swing.JOptionPane.YES_OPTION) {

    	        controller.eliminar(

    	                pago.getIdPago()

    	        );

    	        actualizarTabla();

    	    }

    	});

    }
    
    private Pago obtenerPagoSeleccionado() {

        int fila = tablaPagos.getTable().getSelectedRow();

        if (fila == -1) {

            return null;

        }

        fila = tablaPagos.getTable().convertRowIndexToModel(fila);

        Integer id = modelo.getPago(fila).getIdPago();

        return controller.buscarPorId(id);

    }
    
    
    

}