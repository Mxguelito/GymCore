package view.dialogs;

import java.awt.Color;

import javax.swing.JDialog;

import controller.ClienteController;

import model.Cliente;
import model.Pago;

import view.components.ComboField;
import view.components.DateField;
import view.components.DialogButtons;
import view.components.FormField;
import view.components.SectionTitle;

public class PagoDialog extends JDialog {
	
	private ClienteController clienteController;

	private Pago pago;

	private SectionTitle lblTitulo;

	private ComboField<Cliente> cmbCliente;

	private DateField txtFechaPago;

	private FormField txtPeriodo;

	private FormField txtImporte;

	private ComboField<String> cmbMetodoPago;

	private ComboField<String> cmbEstado;

	private FormField txtObservaciones;

	private DialogButtons dialogButtons;

    public PagoDialog() {

    	configurarVentana();

    	inicializarComponentes();

    	cargarDatos();

    	registrarEventos();

    }
    
    
    public PagoDialog(Pago pago) {

        this();

        this.pago = pago;

        cargarPago();

    }
    
    private void cargarPago() {

        cmbCliente.setSelectedItem(
                pago.getCliente()
        );

        txtFechaPago.setDate(
                pago.getFechaPago()
        );

        txtPeriodo.setText(
                pago.getPeriodo()
        );

        txtImporte.setText(
                String.valueOf(
                        pago.getImporte()
                )
        );

        cmbMetodoPago.setSelectedItem(
                pago.getMetodoPago()
        );

        cmbEstado.setSelectedItem(
                pago.getEstado()
        );

        txtObservaciones.setText(
                pago.getObservaciones()
        );

    }
    

    private void configurarVentana() {

        setTitle("Pago");

        setLayout(null);

        setSize(750,650);

        setLocationRelativeTo(null);

        setModal(true);

        getContentPane().setBackground(Color.WHITE);

    }

    private void inicializarComponentes() {

        clienteController = new ClienteController();

        lblTitulo = new SectionTitle("Nuevo Pago");

        cmbCliente = new ComboField<>("Cliente", 300);

        txtFechaPago = new DateField("Fecha",300);

        txtPeriodo = new FormField("Periodo",300);

        txtImporte = new FormField("Importe",300);

        cmbMetodoPago = new ComboField<>("Método",300);

        cmbEstado = new ComboField<>("Estado",300);

        txtObservaciones = new FormField("Observaciones",620);

        dialogButtons = new DialogButtons();
        
        posicionarComponentes();

    }
    
    private void posicionarComponentes() {

        lblTitulo.setBounds(40,20,300,40);

        cmbCliente.setBounds(40,80,300,70);

        txtFechaPago.setBounds(380,80,300,70);

        txtPeriodo.setBounds(40,170,300,70);

        txtImporte.setBounds(380,170,300,70);

        cmbMetodoPago.setBounds(40,260,300,70);

        cmbEstado.setBounds(380,260,300,70);

        txtObservaciones.setBounds(40,350,640,70);

        dialogButtons.setBounds(360,470,320,45);

        add(lblTitulo);

        add(cmbCliente);

        add(txtFechaPago);

        add(txtPeriodo);

        add(txtImporte);

        add(cmbMetodoPago);

        add(cmbEstado);

        add(txtObservaciones);

        add(dialogButtons);

    }
    
    private void cargarDatos() {

        cmbCliente.getCombo().removeAllItems();

        for (Cliente cliente : clienteController.listar()) {

            cmbCliente.getCombo().addItem(cliente);

        }

        cmbMetodoPago.getCombo().addItem("EFECTIVO");
        cmbMetodoPago.getCombo().addItem("TRANSFERENCIA");
        cmbMetodoPago.getCombo().addItem("DEBITO");
        cmbMetodoPago.getCombo().addItem("CREDITO");

        cmbEstado.getCombo().addItem("PAGADO");
        cmbEstado.getCombo().addItem("PENDIENTE");
        cmbEstado.getCombo().addItem("VENCIDO");

    }
    
    private void registrarEventos() {

        dialogButtons.getBtnCancelar().addActionListener(e -> {

            dispose();

        });

        dialogButtons.getBtnGuardar().addActionListener(e -> {

            guardarPago();

        });

    }
    
    private void guardarPago() {
    	
    	javax.swing.JOptionPane.showMessageDialog(
    		    this,
    		    "Entró a guardarPago()"
    		);

        if (cmbCliente.getSelectedItem() == null) {
            return;
        }

        if (txtFechaPago.getDate() == null) {
            return;
        }

        if (txtPeriodo.getText().trim().isEmpty()) {
            return;
        }

        if (txtImporte.getText().trim().isEmpty()) {
            return;
        }

        if (pago == null) {

            pago = new Pago();

        }

        pago.setCliente(
                cmbCliente.getSelectedItem()
        );

        pago.setFechaPago(
                txtFechaPago.getDate()
        );

        pago.setPeriodo(
                txtPeriodo.getText().trim()
        );

        pago.setImporte(
                Double.parseDouble(
                        txtImporte.getText()
                )
        );

        pago.setMetodoPago(
                cmbMetodoPago.getSelectedItem()
        );

        pago.setEstado(
                cmbEstado.getSelectedItem()
        );

        pago.setObservaciones(
                txtObservaciones.getText()
        );

        dispose();

    }
    
    public Pago getPago() {

        return pago;

    }

}