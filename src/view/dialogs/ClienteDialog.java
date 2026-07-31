package view.dialogs;

import javax.swing.JDialog;
import view.components.SectionTitle;
import view.components.FormField;
import view.components.DialogButtons;

import controller.ClienteController;
import model.Cliente;
import model.Persona;
import java.time.LocalDate;

public class ClienteDialog extends JDialog {
	
	private FormField txtNombre;
	
	private FormField txtApellido;

	private FormField txtEmail;
	
	private DialogButtons botones;
	
	private ClienteController clienteController;
	
	private Cliente cliente;

	private boolean modoEdicion = false;
	
	

	public ClienteDialog() {
		
		 clienteController = new ClienteController();

	    configurarVentana();

	    inicializarComponentes();
	    
	    configurarEventos();
	    
	    

	}
	public ClienteDialog(Cliente cliente) {

	    this();

	    this.cliente = cliente;

	    this.modoEdicion = true;

	    cargarDatos();

	}
	
	private void cargarDatos() {

	    setTitle("Editar Cliente");

	    txtNombre.setText(
	            cliente.getPersona().getNombre()
	    );

	    txtApellido.setText(
	            cliente.getPersona().getApellido()
	    );

	    txtEmail.setText(
	            cliente.getPersona().getEmail()
	    );

	}
	
	private void configurarVentana() {

	    setTitle("Nuevo Cliente");

	    setSize(500, 450);

	    setLocationRelativeTo(null);

	    setModal(true);

	    setResizable(false);

	    setLayout(null);

	}
	
	private void inicializarComponentes() {

	    SectionTitle lblTitulo = new SectionTitle("Nuevo Cliente");

	    lblTitulo.setBounds(30, 20, 250, 40);

	    add(lblTitulo);

	    txtNombre = crearCampo(
	            "Nombre",
	            30,
	            80,
	            300
	    );
	    
	    txtApellido = crearCampo(
	            "Apellido",
	            30,
	            170,
	            300
	    );
	    
	    txtEmail = crearCampo(
	            "Email",
	            30,
	            260,
	            450
	    );
	    
	    botones = new DialogButtons();

	    botones.setLocation(30, 360);

	    add(botones);

	}
	
	private FormField crearCampo(String titulo,
            int x,
            int y,
            int ancho) {

          FormField campo = new FormField(titulo, ancho);

                    campo.setLocation(x, y);

                    add(campo);

           return campo;

}
	
	private void limpiarFormulario() {

	    txtNombre.limpiar();

	    txtApellido.limpiar();

	    txtEmail.limpiar();

	    txtNombre.requestFocusField();

	}
	
	private void configurarEventos() {

	    botones.getBtnCancelar().addActionListener(e -> dispose());

	    botones.getBtnGuardar().addActionListener(e -> guardarCliente());

	}
	
	private boolean validarFormulario() {

	    if (txtNombre.getText().isBlank()) {

	        txtNombre.requestFocusField();

	        return false;

	    }

	    if (txtApellido.getText().isBlank()) {

	        txtApellido.requestFocusField();

	        return false;

	    }

	    if (txtEmail.getText().isBlank()) {

	        txtEmail.requestFocusField();

	        return false;

	    }

	    return true;

	}
	
	private void guardarCliente() {

	    try {

	        if (!validarFormulario()) {
	            return;
	        }

	        if (!modoEdicion) {

	            cliente = new Cliente();

	            Persona persona = new Persona();

	            cliente.setPersona(persona);

	            cliente.setFechaIngreso(LocalDate.now());

	            cliente.setEstado("ACTIVO");

	        }

	        cliente.getPersona().setNombre(txtNombre.getText());
	        cliente.getPersona().setApellido(txtApellido.getText());
	        cliente.getPersona().setEmail(txtEmail.getText());

	        if (modoEdicion) {

	            clienteController.actualizar(cliente);

	        } else {

	            clienteController.guardar(cliente);

	        }

	        dispose();

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	}
	
	
}