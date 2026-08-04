package view;

import controller.ClienteController;
import model.Cliente;
import model.Usuario;
import view.components.BasePanel;
import view.components.InfoCard;
import view.components.PrimaryLabel;
import view.components.SectionTitle;

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

        SectionTitle titulo = new SectionTitle("Inicio");
        titulo.setBounds(40, 30, 300, 40);
        add(titulo);

        PrimaryLabel descripcion =
                new PrimaryLabel(
                        "Bienvenido a GymCore. Aquí podrás consultar el resumen de tu actividad."
                );

        descripcion.setBounds(40, 80, 650, 25);

        add(descripcion);

        crearCards();

    }

    private void crearCards() {

        InfoCard cardObjetivo =
                new InfoCard(
                        "Objetivo actual",
                        "Sin asignar"
                );

        cardObjetivo.setBounds(40, 140, 260, 90);

        add(cardObjetivo);

        InfoCard cardEntrenamiento =
                new InfoCard(
                        "Próximo entrenamiento",
                        "Sin asignar"
                );

        cardEntrenamiento.setBounds(330, 140, 260, 90);

        add(cardEntrenamiento);

        InfoCard cardEstado =
                new InfoCard(
                        "Estado",
                        cliente != null
                                ? cliente.getEstado()
                                : "-"
                );

        cardEstado.setBounds(620, 140, 260, 90);

        add(cardEstado);

        InfoCard cardPago =
                new InfoCard(
                        "Último pago",
                        "Sin registrar"
                );

        cardPago.setBounds(40, 260, 260, 90);

        add(cardPago);

        InfoCard cardEntrenador =
                new InfoCard(
                        "Entrenador asignado",
                        "Sin asignar"
                );

        cardEntrenador.setBounds(330, 260, 260, 90);

        add(cardEntrenador);

    }

}