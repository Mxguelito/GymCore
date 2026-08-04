package view;

import controller.ClienteController;

import model.Cliente;
import model.Usuario;

import view.components.BasePanel;
import view.components.InfoPanel;
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

        descripcion.setBounds(40,80,500,25);

        add(descripcion);

        crearPanelMembresia();

        crearPanelPagos();

    }

    private void crearPanelMembresia() {

        InfoPanel panel =
                new InfoPanel("Membresía");

        panel.setBounds(40,130,430,280);

        add(panel);

        agregarDato(
                panel,
                "Estado",
                cliente.getEstado() != null
                        ? cliente.getEstado()
                        : "Sin registrar",
                20,
                70
        );

        agregarDato(
                panel,
                "Plan",
                "Pendiente de implementar",
                20,
                110
        );

        agregarDato(
                panel,
                "Próximo vencimiento",
                "Pendiente de implementar",
                20,
                150
        );

        agregarDato(
                panel,
                "Importe mensual",
                "Pendiente de implementar",
                20,
                190
        );

    }

    private void crearPanelPagos() {

        InfoPanel panel =
                new InfoPanel("Información de Pago");

        panel.setBounds(500,130,430,280);

        add(panel);

        agregarDato(
                panel,
                "Método de pago",
                "Pendiente de implementar",
                20,
                70
        );

        agregarDato(
                panel,
                "Último pago",
                "Pendiente de implementar",
                20,
                110
        );

        agregarDato(
                panel,
                "Estado del pago",
                "Pendiente de implementar",
                20,
                150
        );

        agregarDato(
                panel,
                "Observaciones",
                "Pendiente de implementar",
                20,
                190
        );

    }

    private void agregarDato(
            InfoPanel panel,
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
                170,
                25
        );

        panel.add(lblTitulo);

        PrimaryLabel lblValor =
                new PrimaryLabel(valor);

        lblValor.setBounds(
                x + 170,
                y,
                220,
                25
        );

        panel.add(lblValor);

    }

}