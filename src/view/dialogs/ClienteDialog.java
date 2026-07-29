package view.dialogs;

import javax.swing.JDialog;
import view.components.SectionTitle;
import view.components.FormField;
import view.components.DialogButtons;

import controller.ClienteController;
import model.Cliente;

public class ClienteDialog extends JDialog {
	
	private FormField txtNombre;
	
	private FormField txtApellido;

	private FormField txtEmail;
	
	private DialogButtons botones;
	
	private ClienteController clienteController;
	
	

	public ClienteDialog() {
		
		 clienteController = new ClienteController();

	    configurarVentana();

	    inicializarComponentes();
	    
	    configurarEventos();
	    
	    

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

	    if (!validarFormulario()) {

	        return;

	    }

	    Cliente cliente = new Cliente();

	    cliente.setNombre(txtNombre.getText());

	    cliente.setApellido(txtApellido.getText());

	    cliente.setEmail(txtEmail.getText());

	    clienteController.guardar(cliente);

	    limpiarFormulario();

	}
	
	
}