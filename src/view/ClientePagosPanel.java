package view;

import controller.ClienteController;

import model.Cliente;
import model.Usuario;

import view.components.BasePanel;
import view.components.PrimaryLabel;
import view.components.SectionTitle;

public class ClientePagosPanel extends BasePanel {

    private Usuario usuario;

    private ClienteController clienteController;

    private Cliente cliente;

    public ClientePagosPanel(Usuario usuario) {

        this.usuario = usuario;

        clienteController = new ClienteController();

        cliente = clienteController.buscarPorPersona(
                usuario.getPersona().getIdPersona()
        );

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Mi Membresía"));

        PrimaryLabel descripcion =
                new PrimaryLabel(
                        "Consulta el estado de tu membresía y pagos."
                );

        descripcion.setBounds(40, 80, 450, 25);

        add(descripcion);

        crearResumen();

    }

    private void crearResumen() {

        agregarDato(
                "Estado",
                cliente.getEstado() != null
                        ? cliente.getEstado()
                        : "Sin asignar",
                40,
                140
        );

        agregarDato(
                "Plan",
                "Sin asignar",
                40,
                180
        );

        agregarDato(
                "Próximo vencimiento",
                "Sin asignar",
                40,
                220
        );

        agregarDato(
                "Importe mensual",
                "Sin asignar",
                40,
                260
        );

        agregarDato(
                "Método de pago",
                "Sin asignar",
                40,
                300
        );

    }

    private void agregarDato(
            String titulo,
            String valor,
            int x,
            int y
    ) {

        PrimaryLabel lblTitulo =
                new PrimaryLabel(titulo + ":");

        lblTitulo.setBounds(
                x,
                y,
                180,
                25
        );

        add(lblTitulo);

        PrimaryLabel lblValor =
                new PrimaryLabel(valor);

        lblValor.setBounds(
                x + 190,
                y,
                300,
                25
        );

        add(lblValor);

    }

}