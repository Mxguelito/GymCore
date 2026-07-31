package view;

import controller.ObjetivoController;
import model.Objetivo;
import view.components.BasePanel;
import view.components.CrudButtons;
import view.components.PrimaryTable;
import view.components.SearchPanel;
import view.components.SectionTitle;
import view.dialogs.ObjetivoDialog;
import view.tablemodels.ObjetivoTableModel;

import core.constants.LayoutConstants;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class ObjetivosPanel extends BasePanel {

    private SearchPanel searchPanel;

    private CrudButtons crudButtons;

    private PrimaryTable tablaObjetivos;

    private ObjetivoTableModel modelo;

    private ObjetivoController objetivoController;

    private TableRowSorter<ObjetivoTableModel> sorter;

    public ObjetivosPanel() {

        objetivoController = new ObjetivoController();

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Gestión de Objetivos"));

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

        tablaObjetivos = new PrimaryTable();

        modelo = new ObjetivoTableModel();

        modelo.cargarObjetivos(
                objetivoController.listar()
        );

        tablaObjetivos.getTable().setModel(modelo);

        sorter = new TableRowSorter<>(modelo);

        tablaObjetivos.getTable().setRowSorter(sorter);

        tablaObjetivos.setBounds(

                LayoutConstants.PADDING,

                LayoutConstants.TABLE_Y + 60,

                LayoutConstants.TABLE_WIDTH,

                LayoutConstants.TABLE_HEIGHT

        );

        add(tablaObjetivos);

    }

    private void actualizarTabla() {

        modelo.cargarObjetivos(
                objetivoController.listar()
        );

    }

    private void configurarEventos() {

        crudButtons.getBtnNuevo().addActionListener(e -> {

            ObjetivoDialog dialog = new ObjetivoDialog();

            dialog.setVisible(true);

            actualizarTabla();

        });

        crudButtons.getBtnEditar().addActionListener(e -> editarObjetivo());

        crudButtons.getBtnEliminar().addActionListener(e -> eliminarObjetivo());

    }

    private void editarObjetivo() {

        int filaVista = tablaObjetivos.getSelectedRow();

        if(filaVista == -1){

            return;

        }

        int filaModelo =
                tablaObjetivos.getTable().convertRowIndexToModel(filaVista);

        Objetivo objetivo =
                modelo.getObjetivo(filaModelo);

        ObjetivoDialog dialog =
                new ObjetivoDialog(objetivo);

        dialog.setVisible(true);

        actualizarTabla();

    }

    private void eliminarObjetivo() {

        int filaVista = tablaObjetivos.getSelectedRow();

        if(filaVista == -1){

            return;

        }

        int opcion = JOptionPane.showConfirmDialog(

                this,

                "¿Desea desactivar este objetivo?",

                "Confirmar",

                JOptionPane.YES_NO_OPTION

        );

        if(opcion != JOptionPane.YES_OPTION){

            return;

        }

        int filaModelo =
                tablaObjetivos.getTable().convertRowIndexToModel(filaVista);

        Objetivo objetivo =
                modelo.getObjetivo(filaModelo);

        objetivoController.eliminar(
                objetivo.getIdObjetivo()
        );

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

                        String texto =
                                searchPanel.getTxtBuscar().getText();

                        if(texto.isBlank()){

                            sorter.setRowFilter(null);

                        }else{

                            sorter.setRowFilter(
                                    RowFilter.regexFilter("(?i)" + texto)
                            );

                        }

                    }

                }

        );

    }

}