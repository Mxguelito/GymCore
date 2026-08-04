package view;

import controller.ClienteController;
import model.Cliente;
import model.Usuario;
import view.components.BasePanel;
import view.components.InfoPanel;
import view.components.PrimaryLabel;
import view.components.SectionTitle;

public class ClienteProgresoPanel extends BasePanel {

    private Usuario usuario;

    private ClienteController clienteController;

    private Cliente cliente;

    public ClienteProgresoPanel(Usuario usuario) {

        this.usuario = usuario;

        clienteController = new ClienteController();

        cliente = clienteController.buscarPorPersona(
                usuario.getPersona().getIdPersona()
        );

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        add(new SectionTitle("Mi Progreso"));

        PrimaryLabel descripcion =
                new PrimaryLabel("Consulta la evolución de tu entrenamiento.");

        descripcion.setBounds(40,80,500,25);

        add(descripcion);

        crearPanelInformacion();

        crearPanelSeguimiento();

    }

    private void crearPanelInformacion() {

        InfoPanel panel =
                new InfoPanel("Información Física");

        panel.setBounds(40,130,430,280);

        add(panel);

        agregarDato(panel,
                "Objetivo",
                "Sin asignar",
                20,
                70);

        agregarDato(panel,
                "Nivel",
                "Sin asignar",
                20,
                110);

        agregarDato(panel,
                "Entrenador",
                "Sin asignar",
                20,
                150);

        agregarDato(panel,
                "Fecha ingreso",
                cliente.getFechaIngreso() != null
                        ? cliente.getFechaIngreso().toString()
                        : "Sin registrar",
                20,
                190);

        agregarDato(panel,
                "Estado",
                cliente.getEstado() != null
                        ? cliente.getEstado()
                        : "Sin registrar",
                20,
                230);

    }

    private void crearPanelSeguimiento() {

        InfoPanel panel =
                new InfoPanel("Seguimiento");

        panel.setBounds(500,130,430,280);

        add(panel);

        agregarDato(panel,
                "Peso inicial",
                "Pendiente de implementar",
                20,
                70);

        agregarDato(panel,
                "Peso actual",
                "Pendiente de implementar",
                20,
                110);

        agregarDato(panel,
                "Cambio total",
                "Pendiente de implementar",
                20,
                150);

        agregarDato(panel,
                "Asistencia",
                "Pendiente de implementar",
                20,
                190);

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
                150,
                25
        );

        panel.add(lblTitulo);

        PrimaryLabel lblValor =
                new PrimaryLabel(valor);

        lblValor.setBounds(
                x + 160,
                y,
                220,
                25
        );

        panel.add(lblValor);

    }

}