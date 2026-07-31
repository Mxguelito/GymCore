package view.dialogs;

import javax.swing.JDialog;

import controller.ObjetivoController;
import model.Objetivo;
import view.components.DialogButtons;
import view.components.FormField;
import view.components.SectionTitle;

public class ObjetivoDialog extends JDialog {

    private FormField txtNombre;
    private FormField txtDescripcion;

    private DialogButtons botones;

    private ObjetivoController objetivoController;

    private Objetivo objetivo;

    private boolean modoEdicion = false;

    public ObjetivoDialog() {

        objetivoController = new ObjetivoController();

        configurarVentana();

        inicializarComponentes();

        configurarEventos();

    }

    public ObjetivoDialog(Objetivo objetivo) {

        this();

        this.objetivo = objetivo;

        this.modoEdicion = true;

        cargarDatos();

    }

    private void configurarVentana() {

        setTitle("Nuevo Objetivo");

        setSize(500, 380);

        setLocationRelativeTo(null);

        setModal(true);

        setResizable(false);

        setLayout(null);

    }

    private void inicializarComponentes() {

        SectionTitle titulo =
                new SectionTitle("Nuevo Objetivo");

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

    private FormField crearCampo(String titulo,
                                 int x,
                                 int y,
                                 int ancho) {

        FormField campo = new FormField(titulo,ancho);

        campo.setLocation(x,y);

        add(campo);

        return campo;

    }

    private void cargarDatos() {

        setTitle("Editar Objetivo");

        txtNombre.setText(objetivo.getNombre());

        txtDescripcion.setText(objetivo.getDescripcion());

    }

    private void configurarEventos() {

        botones.getBtnCancelar().addActionListener(e -> dispose());

        botones.getBtnGuardar().addActionListener(e -> guardar());

    }

    private boolean validarFormulario() {

        if(txtNombre.getText().isBlank()){

            txtNombre.requestFocusField();

            return false;

        }

        return true;

    }

    private void guardar() {

        if(!validarFormulario()){

            return;

        }

        if(!modoEdicion){

            objetivo = new Objetivo();

            objetivo.setActivo(true);

        }

        objetivo.setNombre(txtNombre.getText());

        objetivo.setDescripcion(txtDescripcion.getText());

        if(modoEdicion){

            objetivoController.actualizar(objetivo);

        }else{

            objetivoController.guardar(objetivo);

        }

        dispose();

    }

}