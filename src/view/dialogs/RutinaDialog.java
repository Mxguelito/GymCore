package view.dialogs;

import java.awt.Color;

import javax.swing.JDialog;

import controller.ClienteController;
import controller.EntrenadorController;

import model.Cliente;
import model.Entrenador;
import model.Rutina;

import view.components.ComboField;
import view.components.DateField;
import view.components.DialogButtons;
import view.components.FormField;
import view.components.SectionTitle;
import view.components.PrimaryTable;
import view.components.CrudButtons;

import view.tablemodels.RutinaDetalleTableModel;

import model.RutinaDetalle;

import view.dialogs.RutinaDetalleDialog;

import javax.swing.JOptionPane;

public class RutinaDialog extends JDialog {

    private ClienteController clienteController;

    private EntrenadorController entrenadorController;

    private Rutina rutina;

    private SectionTitle lblTitulo;

    private ComboField<Cliente> cmbCliente;

    private ComboField<Entrenador> cmbEntrenador;

    private FormField txtNombre;

    private FormField txtDescripcion;

    private DateField txtFechaInicio;

    private DateField txtFechaFin;

    private ComboField<String> cmbEstado;

    private PrimaryTable tablaDetalles;

    private RutinaDetalleTableModel modeloDetalles;

    private CrudButtons crudButtons;

    private DialogButtons dialogButtons;

    public RutinaDialog() {

        configurarVentana();

        inicializarComponentes();

        cargarClientes();

        cargarEntrenadores();

        cargarEstados();

        registrarEventos();

    }

    private void configurarVentana() {

        setTitle("Rutina");

        setLayout(null);

        setSize(950, 760);

        setLocationRelativeTo(null);

        setModal(true);

        getContentPane().setBackground(Color.WHITE);

    }

    private void inicializarComponentes() {

        clienteController = new ClienteController();

        entrenadorController = new EntrenadorController();

        lblTitulo = new SectionTitle("Nueva Rutina");

        cmbCliente = new ComboField<>("Cliente",360);

        cmbEntrenador = new ComboField<>("Entrenador",360);

        txtNombre = new FormField("Nombre",360);

        txtDescripcion = new FormField("Descripción",360);

        txtFechaInicio = new DateField("Fecha Inicio",360);

        txtFechaFin = new DateField("Fecha Fin",360);

        cmbEstado = new ComboField<>("Estado",360);

        tablaDetalles = new PrimaryTable();

        modeloDetalles = new RutinaDetalleTableModel();

        crudButtons = new CrudButtons();

        dialogButtons = new DialogButtons();

        tablaDetalles.getTable().setModel(modeloDetalles);

        posicionarComponentes();

    }

    private void posicionarComponentes() {

        lblTitulo.setBounds(40,20,300,40);

        cmbCliente.setBounds(40,80,360,70);

        cmbEntrenador.setBounds(480,80,360,70);

        txtNombre.setBounds(40,160,360,70);

        txtDescripcion.setBounds(480,160,360,70);

        txtFechaInicio.setBounds(40,240,360,70);

        txtFechaFin.setBounds(480,240,360,70);

        cmbEstado.setBounds(40,320,360,70);

        tablaDetalles.setBounds(
                40,
                410,
                820,
                180
        );

        crudButtons.setBounds(
                40,
                610,
                430,
                45
        );

        dialogButtons.setBounds(
                520,
                610,
                320,
                45
        );

        add(lblTitulo);

        add(cmbCliente);

        add(cmbEntrenador);

        add(txtNombre);

        add(txtDescripcion);

        add(txtFechaInicio);

        add(txtFechaFin);

        add(cmbEstado);

        add(tablaDetalles);

        add(crudButtons);

        add(dialogButtons);

    }
    
    private void cargarClientes() {

        cmbCliente.getCombo().removeAllItems();

        for (Cliente cliente : clienteController.listar()) {

            cmbCliente.getCombo().addItem(cliente);

        }
        
        

    }
    
    private void cargarEntrenadores() {

        cmbEntrenador.getCombo().removeAllItems();

        for (Entrenador entrenador : entrenadorController.listar()) {

            cmbEntrenador.getCombo().addItem(entrenador);

        }

    }
    
    
    private void cargarEstados() {

        cmbEstado.getCombo().addItem("ACTIVA");

        cmbEstado.getCombo().addItem("PAUSADA");

        cmbEstado.getCombo().addItem("FINALIZADA");

    }
    
    private void registrarEventos() {

        crudButtons

                .getBtnNuevo()

                .addActionListener(e -> {

                    agregarDetalle();

                });

        crudButtons

                .getBtnEditar()

                .addActionListener(e -> {

                    editarDetalle();

                });

        crudButtons

                .getBtnEliminar()

                .addActionListener(e -> {

                    eliminarDetalle();

                });

        dialogButtons

                .getBtnCancelar()

                .addActionListener(e -> {

                    dispose();

                });
        
        dialogButtons.getBtnGuardar().addActionListener(e -> {

            System.out.println("CLICK EN GUARDAR");

            JOptionPane.showMessageDialog(
                    this,
                    "Entró al botón Guardar"
            );

            guardarRutina();

        });

    }
    
    private void agregarDetalle() {

        RutinaDetalleDialog dialog =
                new RutinaDetalleDialog();

        dialog.setVisible(true);

        RutinaDetalle detalle =
                dialog.getDetalle();

        if (detalle == null) {

            return;

        }

        detalle.setOrden(

                modeloDetalles.getRowCount() + 1

        );

        modeloDetalles.agregar(detalle);

    }
    
    private void editarDetalle() {

        int fila =
                tablaDetalles.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(

                    this,

                    "Seleccione un ejercicio."

            );

            return;

        }

        RutinaDetalle detalle =

                modeloDetalles.getDetalle(fila);

        RutinaDetalleDialog dialog =
                new RutinaDetalleDialog();

        dialog.cargarDetalle(detalle);

        dialog.setVisible(true);

        RutinaDetalle actualizado =
                dialog.getDetalle();

        if (actualizado == null) {

            return;

        }

        actualizado.setOrden(
                detalle.getOrden()
        );

        modeloDetalles.actualizar(

                fila,

                actualizado

        );

    }
    
    private void eliminarDetalle() {

        int fila =
                tablaDetalles.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(

                    this,

                    "Seleccione un ejercicio."

            );

            return;

        }

        modeloDetalles.eliminar(fila);

        for (

                int i = 0;

                i < modeloDetalles.getRowCount();

                i++

        ) {

            modeloDetalles

                    .getDetalle(i)

                    .setOrden(i + 1);

        }

        modeloDetalles.fireTableDataChanged();

    }
    
    private void guardarRutina() {

        System.out.println("PASO 0");

        if (cmbCliente.getSelectedItem() == null) {
            System.out.println("FALLO CLIENTE");
            return;
        }

        System.out.println("PASO 1");

        if (cmbEntrenador.getSelectedItem() == null) {
            System.out.println("FALLO ENTRENADOR");
            return;
        }

        System.out.println("PASO 2");

        if (txtNombre.getText().trim().isEmpty()) {
            System.out.println("FALLO NOMBRE");
            return;
        }

        System.out.println("PASO 3");

        if (modeloDetalles.getRowCount() == 0) {

            System.out.println("FALLO DETALLES");

            JOptionPane.showMessageDialog(
                    this,
                    "Debe agregar al menos un ejercicio."
            );

            return;
        }

        System.out.println("PASO 4");

        if (rutina == null) {

            rutina = new Rutina();

        }

        rutina.setCliente(
                cmbCliente.getSelectedItem()
        );

        rutina.setEntrenador(
                cmbEntrenador.getSelectedItem()
        );

        rutina.setNombre(
                txtNombre.getText().trim()
        );

        rutina.setDescripcion(
                txtDescripcion.getText().trim()
        );
        
        if (txtFechaInicio.getDate() == null) {

            System.out.println("FALLO FECHA INICIO");

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una fecha de inicio."
            );

            return;
        }

        System.out.println("PASO 5");

        if (txtFechaFin.getDate() == null) {

            System.out.println("FALLO FECHA FIN");

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una fecha de fin."
            );

            return;
        }

        System.out.println("PASO 6");

        rutina.setFechaInicio(
                txtFechaInicio.getDate()
        );

        rutina.setFechaFin(
                txtFechaFin.getDate()
        );

        rutina.setEstado(
                cmbEstado.getSelectedItem()
        );

        rutina.setDetalles(
                modeloDetalles.getDetalles()
        );

        System.out.println("PASO 7");

        dispose();

    }
    
    public Rutina getRutina() {

        return rutina;

    }
    
    public void editar(Rutina rutina) {

        this.rutina = rutina;

        cmbCliente.setSelectedItem(
                rutina.getCliente()
        );

        cmbEntrenador.setSelectedItem(
                rutina.getEntrenador()
        );

        txtNombre.setText(
                rutina.getNombre()
        );

        txtDescripcion.setText(
                rutina.getDescripcion()
        );

        txtFechaInicio.setDate(
                rutina.getFechaInicio()
        );

        txtFechaFin.setDate(
                rutina.getFechaFin()
        );

        cmbEstado.setSelectedItem(
                rutina.getEstado()
        );

        modeloDetalles.cargarDetalles(
                rutina.getDetalles()
        );

    }

}