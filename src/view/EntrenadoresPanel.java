package view;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import controller.EntrenadorController;
import model.Entrenador;
import core.constants.LayoutConstants;

import view.components.BasePanel;
import view.components.CrudButtons;
import view.components.PrimaryTable;
import view.components.SearchPanel;
import view.components.SectionTitle;

import view.dialogs.EntrenadorDialog;
import view.tablemodels.EntrenadorTableModel;

public class EntrenadoresPanel extends BasePanel {

    private SearchPanel searchPanel;

    private PrimaryTable tablaEntrenadores;

    private EntrenadorController entrenadorController;

    private EntrenadorTableModel modelo;

    private TableRowSorter<EntrenadorTableModel> sorter;

    private CrudButtons crudButtons;

    public EntrenadoresPanel() {

        entrenadorController = new EntrenadorController();

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Entrenadores"));

        crearSearchPanel();

        crearCrudButtons();

        crearTabla();

        configurarBuscador();

        configurarEventos();

    }

    private void crearSearchPanel() {

        searchPanel = new SearchPanel();

        searchPanel.setBounds(40,100,500,100);

        add(searchPanel);

    }

    private void crearCrudButtons() {

        crudButtons = new CrudButtons();

        crudButtons.setBounds(40,210,430,45);

        add(crudButtons);

    }

    private void crearTabla() {

        tablaEntrenadores = new PrimaryTable();

        modelo = new EntrenadorTableModel();

        modelo.cargarEntrenadores(
                entrenadorController.listar()
        );

        tablaEntrenadores.getTable().setModel(modelo);

        sorter = new TableRowSorter<>(modelo);

        tablaEntrenadores.getTable().setRowSorter(sorter);

        tablaEntrenadores.setBounds(

                LayoutConstants.PADDING,

                LayoutConstants.TABLE_Y + 60,

                LayoutConstants.TABLE_WIDTH,

                LayoutConstants.TABLE_HEIGHT

        );

        add(tablaEntrenadores);

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

        modelo.cargarEntrenadores(
                entrenadorController.listar()
        );

    }

    private void editarEntrenador() {

        int filaVista = tablaEntrenadores.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int filaModelo = tablaEntrenadores.getTable().convertRowIndexToModel(filaVista);

        Entrenador entrenador = modelo.getEntrenador(filaModelo);

        EntrenadorDialog dialog = new EntrenadorDialog();

        dialog.setVisible(true);

        actualizarTabla();

    }

    private void eliminarEntrenador() {

        int filaVista = tablaEntrenadores.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(

                this,

                "¿Desea desactivar este entrenador?",

                "Confirmar",

                JOptionPane.YES_NO_OPTION

        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        int filaModelo = tablaEntrenadores.getTable().convertRowIndexToModel(filaVista);

        Entrenador entrenador = modelo.getEntrenador(filaModelo);

        entrenadorController.eliminar(

                entrenador.getIdEntrenador()

        );

        actualizarTabla();

    }

    private void configurarEventos() {

        crudButtons.getBtnNuevo().addActionListener(e -> {

            EntrenadorDialog dialog = new EntrenadorDialog();

            dialog.setVisible(true);

            actualizarTabla();

        });

        crudButtons.getBtnEditar().addActionListener(e -> {

            editarEntrenador();

        });

        crudButtons.getBtnEliminar().addActionListener(e -> {

            eliminarEntrenador();

        });

    }

}