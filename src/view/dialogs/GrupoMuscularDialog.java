package view.dialogs;

import javax.swing.JDialog;

import controller.GrupoMuscularController;

import model.GrupoMuscular;

import view.components.DialogButtons;
import view.components.FormField;
import view.components.SectionTitle;

public class GrupoMuscularDialog extends JDialog {

    private FormField txtNombre;

    private FormField txtDescripcion;

    private DialogButtons botones;

    private GrupoMuscularController controller;

    private GrupoMuscular grupo;

    private boolean modoEdicion = false;

    public GrupoMuscularDialog() {

        controller = new GrupoMuscularController();

        configurarVentana();

        inicializarComponentes();

        configurarEventos();

    }

    public GrupoMuscularDialog(GrupoMuscular grupo) {

        this();

        this.grupo = grupo;

        modoEdicion = true;

        cargarDatos();

    }

    private void configurarVentana() {

        setTitle("Grupo Muscular");

        setSize(500, 380);

        setLocationRelativeTo(null);

        setModal(true);

        setResizable(false);

        setLayout(null);

    }

    private void inicializarComponentes() {

        SectionTitle titulo =
                new SectionTitle("Grupo Muscular");

        titulo.setBounds(30,20,250,40);

        add(titulo);

        txtNombre =
                crearCampo("Nombre",30,80,400);

        txtDescripcion =
                crearCampo("Descripción",30,170,400);

        botones = new DialogButtons();

        botones.setLocation(30,290);

        add(botones);

    }

    private FormField crearCampo(String titulo,int x,int y,int ancho){

        FormField campo =
                new FormField(titulo,ancho);

        campo.setLocation(x,y);

        add(campo);

        return campo;

    }

    private void cargarDatos(){

        setTitle("Editar Grupo Muscular");

        txtNombre.setText(grupo.getNombre());

        txtDescripcion.setText(grupo.getDescripcion());

    }

    private void configurarEventos(){

        botones.getBtnCancelar().addActionListener(e -> dispose());

        botones.getBtnGuardar().addActionListener(e -> guardar());

    }

    private boolean validarFormulario(){

        if(txtNombre.getText().isBlank()){

            txtNombre.requestFocusField();

            return false;

        }

        return true;

    }

    private void guardar(){

        if(!validarFormulario()){

            return;

        }

        if(!modoEdicion){

            grupo = new GrupoMuscular();

            grupo.setActivo(true);

        }

        grupo.setNombre(
                txtNombre.getText()
        );

        grupo.setDescripcion(
                txtDescripcion.getText()
        );

        if(modoEdicion){

            controller.actualizar(grupo);

        }else{

            controller.guardar(grupo);

        }

        dispose();

    }

}