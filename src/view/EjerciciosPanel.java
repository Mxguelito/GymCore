package view;

import view.components.BasePanel;
import view.components.SectionTitle;
import view.components.SearchPanel;
import view.components.PrimaryTable;
import view.components.CrudButtons;

import core.constants.LayoutConstants;

import controller.EjercicioController;

import model.Ejercicio;

import view.dialogs.EjercicioDialog;
import view.tablemodels.EjercicioTableModel;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class EjerciciosPanel extends BasePanel {

    private SearchPanel searchPanel;

    private PrimaryTable tablaEjercicios;

    private EjercicioController ejercicioController;

    private EjercicioTableModel modelo;

    private TableRowSorter<EjercicioTableModel> sorter;

    private CrudButtons crudButtons;

    public EjerciciosPanel() {

        ejercicioController = new EjercicioController();

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Ejercicios"));

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

        tablaEjercicios = new PrimaryTable();

        modelo = new EjercicioTableModel();

        modelo.cargarEjercicios(
                ejercicioController.listar()
        );

        tablaEjercicios.getTable().setModel(modelo);

        sorter = new TableRowSorter<>(modelo);

        tablaEjercicios.getTable().setRowSorter(sorter);

        tablaEjercicios.setBounds(

                LayoutConstants.PADDING,

                LayoutConstants.TABLE_Y + 60,

                LayoutConstants.TABLE_WIDTH,

                LayoutConstants.TABLE_HEIGHT

        );

        add(tablaEjercicios);

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

        modelo.cargarEjercicios(
                ejercicioController.listar()
        );

    }

    private void editarEjercicio() {

        int filaVista = tablaEjercicios.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int filaModelo = tablaEjercicios.getTable().convertRowIndexToModel(filaVista);

        Ejercicio ejercicio = modelo.getEjercicio(filaModelo);

        EjercicioDialog dialog =
                new EjercicioDialog(ejercicio);

        dialog.setVisible(true);

        actualizarTabla();
    }

    private void eliminarEjercicio() {

        int filaVista = tablaEjercicios.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(

                this,

                "¿Desea desactivar este ejercicio?",

                "Confirmar",

                JOptionPane.YES_NO_OPTION

        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        int filaModelo = tablaEjercicios.getTable().convertRowIndexToModel(filaVista);

        Ejercicio ejercicio = modelo.getEjercicio(filaModelo);

        ejercicioController.eliminar(ejercicio.getIdEjercicio());

        actualizarTabla();

    }

    private void configurarEventos() {

        crudButtons.getBtnNuevo().addActionListener(e -> {

            EjercicioDialog dialog = new EjercicioDialog();

            dialog.setVisible(true);

            actualizarTabla();

        });

        crudButtons.getBtnEditar().addActionListener(e -> {

            editarEjercicio();

        });

        crudButtons.getBtnEliminar().addActionListener(e -> {

            eliminarEjercicio();

        });

    }

}