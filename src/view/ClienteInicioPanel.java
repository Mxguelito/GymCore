package view;

import model.Usuario;
import view.components.BasePanel;
import view.components.PrimaryLabel;
import view.components.SectionTitle;
import controller.ClienteController;
import model.Cliente;

public class ClienteInicioPanel extends BasePanel {

    private Usuario usuario;
    private ClienteController clienteController;

    private Cliente cliente;

    public ClienteInicioPanel(Usuario usuario) {

        this.usuario = usuario;

        clienteController = new ClienteController();

        cliente = clienteController.buscarPorPersona(
                usuario.getPersona().getIdPersona()
        );

        inicializarComponentes();

    }

    public ClienteInicioPanel() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Inicio"));

        PrimaryLabel descripcion =
                new PrimaryLabel("Bienvenido a GymCore. Aquí podrás consultar el resumen de tu actividad.");

        descripcion.setBounds(40, 80, 600, 25);

        add(descripcion);

        crearResumen();

    }

    private void crearResumen() {

        int y = 150;

        agregarDato(
                "Objetivo actual",
                "Sin asignar",
                y
        );

        y += 50;

        agregarDato(
                "Próximo entrenamiento",
                "Sin asignar",
                y
        );

        y += 50;

        agregarDato(
                "Estado de la membresía",
                cliente.getEstado() != null
                        ? cliente.getEstado()
                        : "Sin asignar",
                y
        );

        y += 50;

        agregarDato(
                "Último pago",
                "Sin registrar",
                y
        );

        y += 50;

        agregarDato(
                "Entrenador asignado",
                "Sin asignar",
                y
        );

    }

    private void agregarDato(
            String titulo,
            String valor,
            int y
    ) {

        PrimaryLabel lblTitulo =
                new PrimaryLabel(titulo + ":");

        lblTitulo.setBounds(
                40,
                y,
                220,
                25
        );

        add(lblTitulo);

        PrimaryLabel lblValor =
                new PrimaryLabel(valor);

        lblValor.setBounds(
                270,
                y,
                400,
                25
        );

        add(lblValor);

    }

}