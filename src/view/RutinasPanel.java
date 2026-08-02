package view;

import controller.RutinaController;

import model.Rutina;

import view.components.BasePanel;
import view.components.CrudButtons;
import view.components.PrimaryTable;
import view.components.SearchPanel;
import view.components.SectionTitle;

import view.dialogs.RutinaDialog;

import view.tablemodels.RutinaTableModel;

import core.constants.LayoutConstants;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

public class RutinasPanel extends BasePanel {

    private SearchPanel searchPanel;

    private CrudButtons crudButtons;

    private PrimaryTable tablaRutinas;

    private RutinaController controller;

    private RutinaTableModel modelo;

    private TableRowSorter<RutinaTableModel> sorter;

    public RutinasPanel() {

        controller = new RutinaController();

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Rutinas"));

        crearSearchPanel();

        crearCrudButtons();

        crearTabla();

        configurarBuscador();

        configurarEventos();

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

        tablaRutinas = new PrimaryTable();

        modelo = new RutinaTableModel();

        modelo.cargarRutinas(
                controller.listar()
        );

        tablaRutinas.getTable().setModel(modelo);

        sorter = new TableRowSorter<>(modelo);

        tablaRutinas.getTable().setRowSorter(sorter);

        tablaRutinas.setBounds(

                LayoutConstants.PADDING,

                LayoutConstants.TABLE_Y + 60,

                LayoutConstants.TABLE_WIDTH,

                LayoutConstants.TABLE_HEIGHT

        );

        add(tablaRutinas);

    }

    private void configurarBuscador() {

        searchPanel.getTxtBuscar()
                .getDocument()
                .addDocumentListener(new DocumentListener() {

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

                        String texto =
                                searchPanel.getTxtBuscar().getText();

                        if (texto.isBlank()) {

                            sorter.setRowFilter(null);

                        } else {

                            sorter.setRowFilter(
                                    RowFilter.regexFilter(
                                            "(?i)" + texto
                                    )
                            );

                        }

                    }

                });

    }

    private void configurarEventos() {

    	crudButtons.getBtnNuevo().addActionListener(e -> {

    	    RutinaDialog dialog =
    	            new RutinaDialog();

    	    dialog.setVisible(true);

    	    Rutina rutina =
    	            dialog.getRutina();

    	    System.out.println("Rutina devuelta: " + rutina);

    	    if (rutina != null) {

    	        System.out.println("Voy a guardar");

    	        controller.guardar(rutina);

    	        System.out.println("Guardó correctamente");

    	        actualizarTabla();

    	    }

    	});

        crudButtons.getBtnEditar().addActionListener(e -> {

            editarRutina();

        });

        crudButtons.getBtnEliminar().addActionListener(e -> {

            eliminarRutina();

        });

    }

    private void actualizarTabla() {

        modelo.cargarRutinas(

                controller.listar()

        );

    }

    private void editarRutina() {

        int filaVista = tablaRutinas.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int filaModelo =
                tablaRutinas.getTable()
                        .convertRowIndexToModel(filaVista);

     
        
        Rutina rutina =
                controller.buscarPorId(
                        modelo.getRutina(filaModelo)
                                .getIdRutina()
                );

        RutinaDialog dialog = new RutinaDialog();

        dialog.editar(rutina);

        dialog.setVisible(true);
        System.out.println(dialog.getRutina());

        Rutina rutinaActualizada = dialog.getRutina();

        if (rutinaActualizada != null) {

            controller.actualizar(rutinaActualizada);

            actualizarTabla();

        }

    }

    private void eliminarRutina() {

        int filaVista = tablaRutinas.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(

                this,

                "¿Desea eliminar esta rutina?",

                "Confirmar",

                JOptionPane.YES_NO_OPTION

        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        int filaModelo =
                tablaRutinas.getTable()
                        .convertRowIndexToModel(filaVista);

        Rutina rutina =
                modelo.getRutina(filaModelo);

        controller.eliminar(

                rutina.getIdRutina()

        );

        actualizarTabla();

    }

}