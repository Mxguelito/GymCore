package view;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import controller.GrupoMuscularController;

import core.constants.LayoutConstants;

import model.GrupoMuscular;

import view.components.BasePanel;
import view.components.CrudButtons;
import view.components.PrimaryTable;
import view.components.SearchPanel;
import view.components.SectionTitle;

import view.dialogs.GrupoMuscularDialog;

import view.tablemodels.GrupoMuscularTableModel;

import view.components.PageHeader;
import javax.swing.SwingUtilities;

public class GruposMuscularesPanel extends BasePanel {

    private SearchPanel searchPanel;

    private CrudButtons crudButtons;

    private PrimaryTable tablaGrupos;

    private GrupoMuscularTableModel modelo;

    private GrupoMuscularController controller;

    private TableRowSorter<GrupoMuscularTableModel> sorter;

    public GruposMuscularesPanel() {

        controller = new GrupoMuscularController();

        inicializarComponentes();

    }

    private void inicializarComponentes() {

    	PageHeader header = new PageHeader("Gestión de Grupos Musculares");

    	header.setBounds(40, 30, 1000, 50);

    	header.getBtnVolver().addActionListener(e -> {

    	    DashboardFrame dashboard =
    	            (DashboardFrame) SwingUtilities.getWindowAncestor(this);

    	    dashboard.navegar("CONFIGURACION");

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

        searchPanel.setBounds(40, 140, 500, 100);

        add(searchPanel);

    }

    private void crearCrudButtons() {

        crudButtons = new CrudButtons();

        crudButtons.setBounds(40, 250, 430, 45);

        add(crudButtons);

    }

    private void crearTabla() {

        tablaGrupos = new PrimaryTable();

        modelo = new GrupoMuscularTableModel();

        modelo.cargarGruposMusculares(
                controller.listar()
        );

        tablaGrupos.getTable().setModel(modelo);

        sorter = new TableRowSorter<>(modelo);

        tablaGrupos.getTable().setRowSorter(sorter);

        tablaGrupos.setBounds(

                LayoutConstants.PADDING,

                LayoutConstants.TABLE_Y + 100,

                LayoutConstants.TABLE_WIDTH,

                LayoutConstants.TABLE_HEIGHT

        );

        add(tablaGrupos);

    }

    private void actualizarTabla() {

        modelo.cargarGruposMusculares(

                controller.listar()

        );

    }

    private void configurarEventos() {

        crudButtons.getBtnNuevo().addActionListener(e -> {

            GrupoMuscularDialog dialog =
                    new GrupoMuscularDialog();

            dialog.setVisible(true);

            actualizarTabla();

        });

        crudButtons.getBtnEditar().addActionListener(e -> {

            editarGrupo();

        });

        crudButtons.getBtnEliminar().addActionListener(e -> {

            eliminarGrupo();

        });

    }

    private void editarGrupo() {

        int filaVista =
                tablaGrupos.getTable().getSelectedRow();

        if (filaVista == -1) {

            return;

        }

        int filaModelo =
                tablaGrupos.getTable()
                           .convertRowIndexToModel(filaVista);

        GrupoMuscular grupo =
                modelo.getGrupoMuscular(filaModelo);

        GrupoMuscularDialog dialog =
                new GrupoMuscularDialog(grupo);

        dialog.setVisible(true);

        actualizarTabla();

    }

    private void eliminarGrupo() {

        int filaVista =
                tablaGrupos.getTable().getSelectedRow();

        if (filaVista == -1) {

            return;

        }

        int opcion = JOptionPane.showConfirmDialog(

                this,

                "¿Desea desactivar este grupo muscular?",

                "Confirmar",

                JOptionPane.YES_NO_OPTION

        );

        if (opcion != JOptionPane.YES_OPTION) {

            return;

        }

        int filaModelo =
                tablaGrupos.getTable()
                           .convertRowIndexToModel(filaVista);

        GrupoMuscular grupo =
                modelo.getGrupoMuscular(filaModelo);

        controller.eliminar(

                grupo.getIdGrupoMuscular()

        );

        actualizarTabla();

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

}