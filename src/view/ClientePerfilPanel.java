package view;

import controller.ClienteController;
import model.Cliente;
import model.Persona;
import model.Usuario;

import view.components.BasePanel;
import view.components.InfoPanel;
import view.components.PrimaryButton;
import view.components.PrimaryLabel;
import view.components.SectionTitle;
import view.dialogs.ClientePerfilDialog;

public class ClientePerfilPanel extends BasePanel {

    private Usuario usuario;
    private Persona persona;

    private ClienteController clienteController;
    private Cliente cliente;

    private InfoPanel panelDatos;
    private InfoPanel panelCuenta;

    private PrimaryButton btnEditar;

    public ClientePerfilPanel(Usuario usuario) {

        this.usuario = usuario;
        this.persona = usuario.getPersona();

        clienteController = new ClienteController();

        cliente = clienteController.buscarPorPersona(
                persona.getIdPersona()
        );

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        SectionTitle titulo = new SectionTitle("Mi Perfil");
        add(titulo);

        PrimaryLabel descripcion =
                new PrimaryLabel(
                        "Consulta y administra la información de tu cuenta."
                );

        descripcion.setBounds(40, 80, 500, 25);

        add(descripcion);

        crearDatosPersonales();

        crearDatosCuenta();

        crearBoton();

        configurarEventos();

    }

    private void crearDatosPersonales() {

        panelDatos = new InfoPanel("Datos Personales");

        panelDatos.setBounds(40,130,430,340);

        add(panelDatos);

        int y = 70;

        agregarCampo(
                panelDatos,
                "Nombre",
                persona.getNombre(),
                y
        );

        y += 40;

        agregarCampo(
                panelDatos,
                "Apellido",
                persona.getApellido(),
                y
        );

        y += 40;

        agregarCampo(
                panelDatos,
                "DNI",
                persona.getDni(),
                y
        );

        y += 40;

        agregarCampo(
                panelDatos,
                "Teléfono",
                persona.getTelefono(),
                y
        );

        y += 40;

        agregarCampo(
                panelDatos,
                "Fecha nacimiento",
                persona.getFechaNacimiento() != null
                        ? persona.getFechaNacimiento().toString()
                        : "-",
                y
        );

        y += 40;

        agregarCampo(
                panelDatos,
                "Sexo",
                persona.getSexo(),
                y
        );

    }

    private void crearDatosCuenta() {

        panelCuenta = new InfoPanel("Cuenta");

        panelCuenta.setBounds(500,130,360,260);

        add(panelCuenta);

        int y = 70;

        agregarCampo(
                panelCuenta,
                "Usuario",
                usuario.getUsername(),
                y
        );

        y += 40;

        agregarCampo(
                panelCuenta,
                "Email",
                persona.getEmail(),
                y
        );

        y += 40;

        agregarCampo(
                panelCuenta,
                "Estado",
                cliente != null
                        ? cliente.getEstado()
                        : "-",
                y
        );

        y += 40;

        agregarCampo(
                panelCuenta,
                "Rol",
                usuario.getRol().getNombre(),
                y
        );

    }

    private void crearBoton() {

        btnEditar = new PrimaryButton("Editar Perfil");

        btnEditar.setBounds(
                590,
                420,
                180,
                45
        );

        add(btnEditar);

    }

    private void agregarCampo(
            InfoPanel panel,
            String titulo,
            String valor,
            int y
    ) {

        PrimaryLabel lblTitulo =
                new PrimaryLabel(titulo + ":");

        lblTitulo.setBounds(
                20,
                y,
                150,
                25
        );

        panel.add(lblTitulo);

        PrimaryLabel lblValor =
                new PrimaryLabel(valor != null ? valor : "-");

        lblValor.setBounds(
                180,
                y,
                200,
                25
        );

        panel.add(lblValor);

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