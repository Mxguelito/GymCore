package view;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import controller.NivelController;
import core.constants.LayoutConstants;
import model.Nivel;
import view.components.BasePanel;
import view.components.CrudButtons;
import view.components.PrimaryTable;
import view.components.SearchPanel;
import view.components.SectionTitle;
import view.dialogs.NivelDialog;
import view.tablemodels.NivelTableModel;

import view.components.PageHeader;

import javax.swing.SwingUtilities;
import view.DashboardFrame;

public class NivelesPanel extends BasePanel {

    private SearchPanel searchPanel;

    private CrudButtons crudButtons;

    private PrimaryTable tablaNiveles;

    private NivelTableModel modelo;

    private NivelController nivelController;

    private TableRowSorter<NivelTableModel> sorter;

    public NivelesPanel() {

        nivelController = new NivelController();

        inicializarComponentes();

    }

    private void inicializarComponentes() {

    	PageHeader header = new PageHeader("Gestión de Niveles");

    	header.setBounds(40, 30, 920, 40);
    	
    	header.setAccionVolver(() -> {

    	    DashboardFrame dashboard =
    	            (DashboardFrame) SwingUtilities.getWindowAncestor(this);

    	    dashboard.navegar("CONFIGURACION");

    	});
    	
    	header.getBtnVolver().addActionListener(e -> {

    	

    	});

    	add(header);

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

        tablaNiveles = new PrimaryTable();

        modelo = new NivelTableModel();

        modelo.cargarNiveles(
                nivelController.listar()
        );

        tablaNiveles.getTable().setModel(modelo);

        sorter = new TableRowSorter<>(modelo);

        tablaNiveles.getTable().setRowSorter(sorter);

        tablaNiveles.setBounds(

                LayoutConstants.PADDING,

                LayoutConstants.TABLE_Y + 60,

                LayoutConstants.TABLE_WIDTH,

                LayoutConstants.TABLE_HEIGHT

        );

        add(tablaNiveles);

    }

    private void actualizarTabla() {

        modelo.cargarNiveles(
                nivelController.listar()
        );

    }

    private void configurarEventos() {

        crudButtons.getBtnNuevo().addActionListener(e -> {

            NivelDialog dialog = new NivelDialog();

            dialog.setVisible(true);

            actualizarTabla();

        });

        crudButtons.getBtnEditar().addActionListener(e -> editarNivel());

        crudButtons.getBtnEliminar().addActionListener(e -> eliminarNivel());

    }

    private void editarNivel() {

        int filaVista = tablaNiveles.getSelectedRow();

        if (filaVista == -1) {

            return;

        }

        int filaModelo = tablaNiveles
                .getTable()
                .convertRowIndexToModel(filaVista);

        Nivel nivel = modelo.getNivel(filaModelo);

        NivelDialog dialog = new NivelDialog(nivel);

        dialog.setVisible(true);

        actualizarTabla();

    }

    private void eliminarNivel() {

        int filaVista = tablaNiveles.getSelectedRow();

        if (filaVista == -1) {

            return;

        }

        int opcion = JOptionPane.showConfirmDialog(

                this,

                "¿Desea desactivar este nivel?",

                "Confirmar",

                JOptionPane.YES_NO_OPTION

        );

        if (opcion != JOptionPane.YES_OPTION) {

            return;

        }

        int filaModelo = tablaNiveles
                .getTable()
                .convertRowIndexToModel(filaVista);

        Nivel nivel = modelo.getNivel(filaModelo);

        nivelController.eliminar(nivel.getIdNivel());

        actualizarTabla();

    }

    private void configurarBuscador() {

        searchPanel.getTxtBuscar().getDocument().addDocumentListener(

                new DocumentListener() {

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

                }

        );

    }

}