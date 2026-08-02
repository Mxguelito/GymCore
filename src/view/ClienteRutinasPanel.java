package view;

import controller.RutinaController;

import view.components.BasePanel;
import view.components.PrimaryLabel;
import view.components.PrimaryTable;
import view.components.SectionTitle;

import controller.ClienteController;

import model.Cliente;

import model.Usuario;

import view.tablemodels.RutinaTableModel;

public class ClienteRutinasPanel extends BasePanel {

    private PrimaryTable tablaRutinas;

    private RutinaController controller;

    private RutinaTableModel modelo;

    private Usuario usuario;

    private ClienteController clienteController;

    public ClienteRutinasPanel(Usuario usuario) {

        this.usuario = usuario;

        controller = new RutinaController();

        clienteController = new ClienteController();

        inicializarComponentes();

    }
    private void inicializarComponentes() {

        add(new SectionTitle("Mis Rutinas"));

        PrimaryLabel descripcion =
                new PrimaryLabel(
                        "Consulta las rutinas asignadas por tu entrenador."
                );

        descripcion.setBounds(40, 80, 500, 25);

        add(descripcion);

        crearTabla();

    }

    private void crearTabla() {

        tablaRutinas = new PrimaryTable();

        modelo = new RutinaTableModel();

        Cliente cliente =
                clienteController.buscarPorPersona(
                        usuario.getPersona().getIdPersona()
                );

        if (cliente != null) {

            modelo.cargarRutinas(
                    controller.listarPorCliente(
                            cliente.getIdCliente()
                    )
            );

        }

        tablaRutinas.getTable().setModel(modelo);

        tablaRutinas.setBounds(
                40,
                130,
                900,
                420
        );

        add(tablaRutinas);

    }

}