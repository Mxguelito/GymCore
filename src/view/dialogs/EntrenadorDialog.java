package view.dialogs;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import controller.EntrenadorController;
import controller.PersonaController;
import model.Entrenador;
import model.Persona;
import view.components.ComboField;
import view.components.DateField;
import view.components.DialogButtons;
import view.components.FormField;
import view.components.SectionTitle;

public class EntrenadorDialog extends JDialog {

    private EntrenadorController entrenadorController;
    private PersonaController personaController;

    private Entrenador entrenador;

    private SectionTitle lblTitulo;

    private ComboField<Persona> cmbPersona;

    private FormField txtEspecialidad;

    private DateField txtFechaIngreso;

    private ComboField<String> cmbEstado;

    private DialogButtons botones;

    public EntrenadorDialog() {

        configurarVentana();

        inicializarComponentes();

        cargarEstados();

        cargarPersonas();

        registrarEventos();

    }

    private void configurarVentana() {

        setTitle("Entrenador");

        setLayout(null);

        setSize(470, 470);

        setLocationRelativeTo(null);

        setModal(true);

        getContentPane().setBackground(Color.WHITE);

    }

    private void inicializarComponentes() {

        entrenadorController = new EntrenadorController();

        personaController = new PersonaController();

        lblTitulo = new SectionTitle("Nuevo Entrenador");

        cmbPersona = new ComboField<>("Persona", 360);

        txtEspecialidad = new FormField("Especialidad", 360);

        txtFechaIngreso = new DateField("Fecha de ingreso", 360);

        cmbEstado = new ComboField<>("Estado", 360);

        botones = new DialogButtons();

        lblTitulo.setBounds(40, 20, 300, 40);

        cmbPersona.setBounds(40, 80, 360, 70);

        txtEspecialidad.setBounds(40, 160, 360, 70);

        txtFechaIngreso.setBounds(40, 240, 360, 70);

        cmbEstado.setBounds(40, 320, 360, 70);

        botones.setBounds(70, 390, 320, 45);

        add(lblTitulo);
        add(cmbPersona);
        add(txtEspecialidad);
        add(txtFechaIngreso);
        add(cmbEstado);
        add(botones);

    }

    private void cargarEstados() {

        JComboBox<String> combo = cmbEstado.getCombo();

        combo.removeAllItems();

        combo.addItem("ACTIVO");

        combo.addItem("INACTIVO");

    }

    private void cargarPersonas() {

        JComboBox<Persona> combo = cmbPersona.getCombo();

        combo.removeAllItems();

        for (Persona persona : personaController.listar()) {

            combo.addItem(persona);

        }

    }

    private void registrarEventos() {

        // Próximo paso:
        // botones.getBtnGuardar().addActionListener(...)
        // botones.getBtnCancelar().addActionListener(...)

    }

}