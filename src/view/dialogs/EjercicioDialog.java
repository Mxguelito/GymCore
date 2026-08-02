package view.dialogs;

import controller.EjercicioController;
import controller.GrupoMuscularController;

import view.components.ComboField;
import view.components.DialogButtons;
import view.components.FormField;

import model.Ejercicio;
import model.GrupoMuscular;

import javax.swing.*;
import java.awt.*;

public class EjercicioDialog extends JDialog {

    private final EjercicioController controller;
    private final GrupoMuscularController grupoController;

    private Ejercicio ejercicio;

    private final ComboField<GrupoMuscular> cmbGrupo;

    private final FormField txtNombre;

    private final FormField txtDescripcion;

    private final FormField txtVideo;

    private boolean guardado = false;

    public EjercicioDialog(Window owner) {

        super(owner, "Ejercicio", ModalityType.APPLICATION_MODAL);

        controller = new EjercicioController();

        grupoController = new GrupoMuscularController();

        cmbGrupo = new ComboField<>("Grupo Muscular", 380);

        txtNombre = new FormField("Nombre", 380);

        txtDescripcion = new FormField("Descripción", 380);

        txtVideo = new FormField("Video URL", 380);

        inicializar();

    }
    
    public EjercicioDialog() {

        this((Window) null);

    }
    
    public EjercicioDialog(Ejercicio ejercicio) {

        this();

        this.ejercicio = ejercicio;

        cargarDatos();

    }
    

    private void inicializar() {

        setSize(480, 420);

        setLocationRelativeTo(getOwner());

        setResizable(false);

        JPanel formulario = new JPanel();

        formulario.setLayout(new GridLayout(4,1,0,15));

        formulario.add(cmbGrupo);

        formulario.add(txtNombre);

        formulario.add(txtDescripcion);

        formulario.add(txtVideo);

        cargarGrupos();

        DialogButtons botones = new DialogButtons();

        botones.getBtnGuardar().addActionListener(e -> guardar());

        botones.getBtnCancelar().addActionListener(e -> dispose());

        setLayout(new BorderLayout(15,15));

        add(formulario, BorderLayout.CENTER);

        add(botones, BorderLayout.SOUTH);

    }

    private void cargarGrupos() {

        cmbGrupo.limpiar();

        for (GrupoMuscular grupo : grupoController.listar()) {

            cmbGrupo.agregarItem(grupo);

        }

    }

    private void guardar() {

        if (txtNombre.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un nombre."
            );

            return;

        }

        if (cmbGrupo.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un grupo muscular."
            );

            return;

        }

        if (ejercicio == null) {

            ejercicio = new Ejercicio();

            ejercicio.setActivo(true);

        }

        ejercicio.setGrupoMuscular(
                cmbGrupo.getSelectedItem()
        );

        ejercicio.setNombre(
                txtNombre.getText().trim()
        );

        ejercicio.setDescripcion(
                txtDescripcion.getText().trim()
        );

        ejercicio.setVideoUrl(
                txtVideo.getText().trim()
        );

        if (ejercicio.getIdEjercicio() == null) {

            controller.guardar(ejercicio);

        } else {

            controller.actualizar(ejercicio);

        }

        guardado = true;

        dispose();

    }

    private void cargarDatos() {

        if (ejercicio == null) {

            return;

        }

        setTitle("Editar Ejercicio");

        cmbGrupo.setSelectedItem(
                ejercicio.getGrupoMuscular()
        );

        txtNombre.setText(
                ejercicio.getNombre()
        );

        txtDescripcion.setText(
                ejercicio.getDescripcion()
        );

        txtVideo.setText(
                ejercicio.getVideoUrl()
        );

    }

    public boolean fueGuardado() {

        return guardado;

    }

    public Ejercicio getEjercicio() {

        return ejercicio;

    }

}