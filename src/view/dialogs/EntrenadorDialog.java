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
    
    private boolean modoEdicion = false;

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
    
    public EntrenadorDialog(Entrenador entrenador) {

        this();

        this.entrenador = entrenador;

        this.modoEdicion = true;

        cargarDatos();

    }
    
    private void cargarDatos() {

        setTitle("Editar Entrenador");

        lblTitulo.setText("Editar Entrenador");

        txtEspecialidad.setText(
                entrenador.getEspecialidad()
        );

        cmbEstado.getCombo().setSelectedItem(
                entrenador.getEstado()
        );

        if (entrenador.getFechaIngreso() != null) {

            txtFechaIngreso.setDate(
                    entrenador.getFechaIngreso().toLocalDate()
            );

        }

    }

    private void configurarVentana() {

        setTitle("Entrenador");

        setLayout(null);

        setSize(540, 580);

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

       
        lblTitulo.setBounds(45, 25, 350, 40);

        cmbPersona.setBounds(45, 85, 430, 70);

        txtEspecialidad.setBounds(45, 165, 430, 70);

        txtFechaIngreso.setBounds(45, 245, 430, 70);

        cmbEstado.setBounds(45, 325, 430, 70);

        botones.setBounds(95, 435, 340, 45);
        
        
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

        botones.getBtnCancelar().addActionListener(e -> dispose());

        botones.getBtnGuardar().addActionListener(e -> guardarEntrenador());

    }
    
    private void guardarEntrenador() {

        try {

        	if (!modoEdicion) {

        	    entrenador = new Entrenador();

        	}

            Persona persona = (Persona) cmbPersona.getCombo().getSelectedItem();

            entrenador.setPersonaId(persona.getIdPersona());

            entrenador.setEspecialidad(
                    txtEspecialidad.getText()
            );

            if (txtFechaIngreso.getDate() == null) {

                entrenador.setFechaIngreso(
                        new java.sql.Date(System.currentTimeMillis())
                );

            } else {

                entrenador.setFechaIngreso(
                        java.sql.Date.valueOf(
                                txtFechaIngreso.getDate()
                        )
                );

            }
            entrenador.setEstado(
                    (String) cmbEstado.getCombo().getSelectedItem()
            );

            if (modoEdicion) {

                entrenadorController.actualizar(entrenador);

            } else {

                entrenadorController.guardar(entrenador);

            }

            dispose();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}