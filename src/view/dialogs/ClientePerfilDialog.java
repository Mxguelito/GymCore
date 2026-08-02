package view.dialogs;

import javax.swing.JDialog;

import model.Usuario;
import model.Persona;

import service.UsuarioService;

import view.components.DialogButtons;
import view.components.FormField;
import view.components.SectionTitle;

public class ClientePerfilDialog extends JDialog {

    private Usuario usuario;

    private UsuarioService usuarioService;

    private FormField txtNombre;

    private FormField txtApellido;

    private FormField txtEmail;

    private DialogButtons botones;

    public ClientePerfilDialog(Usuario usuario) {

        this.usuario = usuario;

        usuarioService = new UsuarioService();

        configurarVentana();

        inicializarComponentes();

        cargarDatos();

        configurarEventos();

    }

    private void configurarVentana() {

        setTitle("Editar Perfil");

        setSize(500, 450);

        setLocationRelativeTo(null);

        setModal(true);

        setResizable(false);

        setLayout(null);

    }

    private void inicializarComponentes() {

        SectionTitle titulo =
                new SectionTitle("Editar Perfil");

        titulo.setLocation(30,20);

        add(titulo);

        txtNombre = crearCampo(
                "Nombre",
                30,
                80,
                300
        );

        txtApellido = crearCampo(
                "Apellido",
                30,
                170,
                300
        );

        txtEmail = crearCampo(
                "Email",
                30,
                260,
                420
        );

        botones = new DialogButtons();

        botones.setLocation(30,360);

        add(botones);

    }

    private FormField crearCampo(
            String titulo,
            int x,
            int y,
            int ancho
    ) {

        FormField campo =
                new FormField(titulo, ancho);

        campo.setLocation(x,y);

        add(campo);

        return campo;

    }

    private void cargarDatos() {

        Persona persona = usuario.getPersona();

        txtNombre.setText(persona.getNombre());

        txtApellido.setText(persona.getApellido());

        txtEmail.setText(persona.getEmail());

    }

    private void configurarEventos() {

        botones.getBtnCancelar().addActionListener(e -> dispose());

        botones.getBtnGuardar().addActionListener(e -> guardar());

    }

    private void guardar() {

        Persona persona = usuario.getPersona();

        persona.setNombre(txtNombre.getText());

        persona.setApellido(txtApellido.getText());

        persona.setEmail(txtEmail.getText());

        usuarioService.actualizar(usuario);

        dispose();

    }

}