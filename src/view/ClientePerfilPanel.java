package view;

import model.Persona;
import model.Usuario;

import view.components.BasePanel;
import view.components.PrimaryButton;
import view.components.PrimaryLabel;
import view.components.SectionTitle;
import view.dialogs.ClientePerfilDialog;

public class ClientePerfilPanel extends BasePanel {

    private Usuario usuario;
    private Persona persona;

    private PrimaryButton btnEditar;

    public ClientePerfilPanel(Usuario usuario) {

        this.usuario = usuario;
        this.persona = usuario.getPersona();

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        SectionTitle titulo = new SectionTitle("Mi Perfil");
        add(titulo);

        PrimaryLabel descripcion =
                new PrimaryLabel("Consulta y administra la información de tu cuenta.");

        descripcion.setBounds(40, 80, 450, 25);

        add(descripcion);

        crearDatosPersonales();

        crearDatosCuenta();

        crearBoton();

        configurarEventos();

    }

    private void crearDatosPersonales() {

        PrimaryLabel titulo =
                new PrimaryLabel("DATOS PERSONALES");

        titulo.setBounds(40, 130, 250, 30);

        add(titulo);

        int y = 175;

        agregarCampo("Nombre", persona.getNombre(), y);
        y += 40;

        agregarCampo("Apellido", persona.getApellido(), y);
        y += 40;

        agregarCampo("DNI", persona.getDni(), y);
        y += 40;

        agregarCampo("Teléfono", persona.getTelefono(), y);
        y += 40;

        agregarCampo(
                "Fecha nacimiento",
                persona.getFechaNacimiento() != null
                        ? persona.getFechaNacimiento().toString()
                        : "-",
                y
        );

        y += 40;

        agregarCampo("Sexo", persona.getSexo(), y);

    }

    private void crearDatosCuenta() {

        PrimaryLabel titulo =
                new PrimaryLabel("CUENTA");

        titulo.setBounds(500, 130, 200, 30);

        add(titulo);

        int y = 175;

        agregarCampoDerecha("Usuario", usuario.getUsername(), y);
        y += 40;

        agregarCampoDerecha("Email", persona.getEmail(), y);
        y += 40;

        agregarCampoDerecha("Estado", usuario.getEstado(), y);
        y += 40;

        agregarCampoDerecha("Rol", usuario.getRol().getNombre(), y);

    }

    private void crearBoton() {

        btnEditar = new PrimaryButton("Editar Perfil");

        btnEditar.setBounds(
                500,
                380,
                180,
                45
        );

        add(btnEditar);

    }

    private void agregarCampo(
            String titulo,
            String valor,
            int y
    ) {

        PrimaryLabel lblTitulo =
                new PrimaryLabel(titulo + ":");

        lblTitulo.setBounds(
                40,
                y,
                150,
                25
        );

        add(lblTitulo);

        PrimaryLabel lblValor =
                new PrimaryLabel(valor != null ? valor : "-");

        lblValor.setBounds(
                180,
                y,
                220,
                25
        );

        add(lblValor);

    }

    private void agregarCampoDerecha(
            String titulo,
            String valor,
            int y
    ) {

        PrimaryLabel lblTitulo =
                new PrimaryLabel(titulo + ":");

        lblTitulo.setBounds(
                500,
                y,
                120,
                25
        );

        add(lblTitulo);

        PrimaryLabel lblValor =
                new PrimaryLabel(valor != null ? valor : "-");

        lblValor.setBounds(
                610,
                y,
                250,
                25
        );

        add(lblValor);

    }

    private void configurarEventos() {

        btnEditar.addActionListener(e -> {

            ClientePerfilDialog dialog =
                    new ClientePerfilDialog(usuario);

            dialog.setVisible(true);

            repaint();

        });

    }

}