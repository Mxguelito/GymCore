package view;

import javax.swing.table.DefaultTableModel;

import view.components.BasePanel;
import view.components.PrimaryLabel;
import view.components.PrimaryTable;
import view.components.SectionTitle;

import model.Usuario;

import controller.ClienteController;
import model.Cliente;

public class ClienteProgresoPanel extends BasePanel {

    private PrimaryTable tablaProgreso;

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

        descripcion.setBounds(40, 80, 400, 25);

        add(descripcion);

        crearResumen();

        

    }

    private void crearResumen() {

        agregarDato(
                "Objetivo",
                "Sin asignar",
                40,
                140
        );

        agregarDato(
                "Nivel",
                "Sin asignar",
                40,
                180
        );

        agregarDato(
                "Entrenador",
                "Sin asignar",
                40,
                220
        );

        agregarDato(
                "Fecha de ingreso",
                cliente.getFechaIngreso() != null
                        ? cliente.getFechaIngreso().toString()
                        : "Sin completar",
                40,
                260
        );

        agregarDato(
                "Estado",
                cliente.getEstado() != null
                        ? cliente.getEstado()
                        : "Sin completar",
                40,
                300
        );

        agregarDato(
                "Peso actual",
                "Sin completar",
                500,
                140
        );

        agregarDato(
                "Peso inicial",
                "Sin completar",
                500,
                180
        );

        agregarDato(
                "Cambio total",
                "Sin completar",
                500,
                220
        );

        agregarDato(
                "Asistencia",
                "Sin completar",
                500,
                260
        );

    }

    private void crearTabla() {

        PrimaryLabel tituloTabla =
                new PrimaryLabel("Historial de Progreso");

        tituloTabla.setBounds(40, 320, 250, 25);

        add(tituloTabla);

        tablaProgreso = new PrimaryTable();

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("Fecha");
        modelo.addColumn("Peso");
        modelo.addColumn("Observación");

        modelo.addRow(new Object[] {
                "28/06/2026",
                "76 kg",
                "Inicio"
        });

        modelo.addRow(new Object[] {
                "05/07/2026",
                "77 kg",
                "+1 kg"
        });

        modelo.addRow(new Object[] {
                "12/07/2026",
                "78 kg",
                "Excelente progreso"
        });

        tablaProgreso.getTable().setModel(modelo);

        tablaProgreso.setBounds(
                40,
                360,
                900,
                260
        );

        add(tablaProgreso);

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
                170,
                25
        );

        add(lblTitulo);

        PrimaryLabel lblValor =
                new PrimaryLabel(valor);

        lblValor.setBounds(
                x + 180,
                y,
                250,
                25
        );

        add(lblValor);

    }

}