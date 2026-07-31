package view.dialogs;

import javax.swing.JDialog;

import controller.NivelController;
import model.Nivel;
import view.components.DialogButtons;
import view.components.FormField;
import view.components.SectionTitle;

public class NivelDialog extends JDialog {

    private FormField txtNombre;
    private FormField txtDescripcion;

    private DialogButtons botones;

    private NivelController nivelController;

    private Nivel nivel;

    private boolean modoEdicion = false;

    public NivelDialog() {

        nivelController = new NivelController();

        configurarVentana();

        inicializarComponentes();

        configurarEventos();

    }

    public NivelDialog(Nivel nivel) {

        this();

        this.nivel = nivel;

        this.modoEdicion = true;

        cargarDatos();

    }

    private void configurarVentana() {

        setTitle("Nuevo Nivel");

        setSize(500, 380);

        setLocationRelativeTo(null);

        setModal(true);

        setResizable(false);

        setLayout(null);

    }

    private void inicializarComponentes() {

        SectionTitle titulo = new SectionTitle("Nuevo Nivel");

        titulo.setBounds(30, 20, 250, 40);

        add(titulo);

        txtNombre = crearCampo("Nombre", 30, 80, 400);

        txtDescripcion = crearCampo("Descripción", 30, 170, 400);

        botones = new DialogButtons();

        botones.setLocation(30, 290);

        add(botones);

    }

    private FormField crearCampo(String titulo, int x, int y, int ancho) {

        FormField campo = new FormField(titulo, ancho);

        campo.setLocation(x, y);

        add(campo);

        return campo;

    }

    private void cargarDatos() {

        setTitle("Editar Nivel");

        txtNombre.setText(nivel.getNombre());

        txtDescripcion.setText(nivel.getDescripcion());

    }

    private void configurarEventos() {

        botones.getBtnCancelar().addActionListener(e -> dispose());

        botones.getBtnGuardar().addActionListener(e -> guardar());

    }

    private boolean validarFormulario() {

        if (txtNombre.getText().isBlank()) {

            txtNombre.requestFocusField();

            return false;

        }

        return true;

    }

    private void guardar() {

        if (!validarFormulario()) {

            return;

        }

        if (!modoEdicion) {

            nivel = new Nivel();

            nivel.setActivo(true);

        }

        nivel.setNombre(txtNombre.getText());

        nivel.setDescripcion(txtDescripcion.getText());

        if (modoEdicion) {

            nivelController.actualizar(nivel);

        } else {

            nivelController.guardar(nivel);

        }

        dispose();

    }

}