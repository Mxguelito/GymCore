package view;

import controller.GrupoMuscularController;
import model.GrupoMuscular;
import view.components.BasePanel;
import view.components.CrudButtons;
import view.components.PrimaryTable;
import view.components.SearchPanel;
import view.dialogs.GrupoMuscularDialog;
import view.tablemodels.GrupoMuscularTableModel;

import javax.swing.*;
import java.awt.*;

public class GruposMuscularesPanel extends BasePanel {

    private final GrupoMuscularController controller;

    private final GrupoMuscularTableModel tableModel;

    private final PrimaryTable tabla;

    private final CrudButtons crudButtons;

    private final SearchPanel searchPanel;

    public GruposMuscularesPanel() {

        controller = new GrupoMuscularController();

        tableModel = new GrupoMuscularTableModel();

        tabla = new PrimaryTable();

        tabla.getTable().setModel(tableModel);

        crudButtons = new CrudButtons();

        searchPanel = new SearchPanel();

        inicializar();

        cargarDatos();

    }

    private void inicializar() {

        setLayout(new BorderLayout(15,15));

        add(searchPanel, BorderLayout.NORTH);

        add(tabla, BorderLayout.CENTER);

        add(crudButtons, BorderLayout.SOUTH);

        crudButtons.getBtnNuevo().addActionListener(e -> nuevo());

        crudButtons.getBtnEditar().addActionListener(e -> editar());

        crudButtons.getBtnEliminar().addActionListener(e -> eliminar());

    }

    private void cargarDatos() {

        tableModel.cargarGruposMusculares(
                controller.listar()
        );

    }

    private void nuevo() {

        GrupoMuscularDialog dialog =
                new GrupoMuscularDialog(
                        SwingUtilities.getWindowAncestor(this)
                );

        dialog.setVisible(true);

        if(dialog.fueGuardado()){

            cargarDatos();

        }

    }

    private void editar() {

        int fila = tabla.getSelectedRow();

        if(fila == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un grupo muscular."
            );

            return;

        }

        GrupoMuscular grupo =
                tableModel.getGrupoMuscular(fila);

        GrupoMuscularDialog dialog =
                new GrupoMuscularDialog(
                        SwingUtilities.getWindowAncestor(this)
                );

        dialog.editar(grupo);

        dialog.setVisible(true);

        if(dialog.fueGuardado()){

            cargarDatos();

        }

    }

    private void eliminar() {

        int fila = tabla.getSelectedRow();

        if(fila == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un grupo muscular."
            );

            return;

        }

        GrupoMuscular grupo =
                tableModel.getGrupoMuscular(fila);

        int opcion = JOptionPane.showConfirmDialog(

                this,

                "¿Eliminar el grupo muscular?",

                "Confirmación",

                JOptionPane.YES_NO_OPTION

        );

        if(opcion == JOptionPane.YES_OPTION){

            controller.eliminar(
                    grupo.getIdGrupoMuscular()
            );

            cargarDatos();

        }

    }

}