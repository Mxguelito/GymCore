package view.dialogs;

import controller.GrupoMuscularController;
import view.components.DialogButtons;
import view.components.FormField;
import model.GrupoMuscular;

import javax.swing.*;
import java.awt.*;

public class GrupoMuscularDialog extends JDialog {

    private final GrupoMuscularController controller;

    private GrupoMuscular grupoMuscular;

    private final FormField txtNombre;
    private final FormField txtDescripcion;

    private boolean guardado = false;

    public GrupoMuscularDialog(Window owner) {

        super(owner, "Grupo Muscular", ModalityType.APPLICATION_MODAL);

        controller = new GrupoMuscularController();

        txtNombre = new FormField("Nombre", 380);
        txtDescripcion = new FormField("Descripción", 380);

        inicializar();

    }

    private void inicializar() {

        setSize(450, 280);

        setLocationRelativeTo(getOwner());

        setResizable(false);

        JPanel formulario = new JPanel();

        formulario.setLayout(new GridLayout(2, 1, 0, 15));

        formulario.add(txtNombre);

        formulario.add(txtDescripcion);

        DialogButtons botones = new DialogButtons();

        botones.getBtnGuardar().addActionListener(e -> guardar());

        botones.getBtnCancelar().addActionListener(e -> dispose());

        setLayout(new BorderLayout(15, 15));

        add(formulario, BorderLayout.CENTER);

        add(botones, BorderLayout.SOUTH);

    }

    private void guardar() {

        if (txtNombre.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un nombre."
            );

            return;

        }

        if (grupoMuscular == null) {

            grupoMuscular = new GrupoMuscular();

            grupoMuscular.setActivo(true);

        }

        grupoMuscular.setNombre(txtNombre.getText().trim());

        grupoMuscular.setDescripcion(
                txtDescripcion.getText().trim()
        );

        if (grupoMuscular.getIdGrupoMuscular() == null) {

            controller.guardar(grupoMuscular);

        } else {

            controller.actualizar(grupoMuscular);

        }

        guardado = true;

        dispose();

    }

    public void editar(GrupoMuscular grupo) {

        this.grupoMuscular = grupo;

        txtNombre.setText(grupo.getNombre());

        txtDescripcion.setText(grupo.getDescripcion());

    }

    public boolean fueGuardado() {

        return guardado;

    }

    public GrupoMuscular getGrupoMuscular() {

        return grupoMuscular;

    }

}