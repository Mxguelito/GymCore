package view.dialogs;

import javax.swing.JDialog;

import view.components.DialogButtons;
import view.components.FormField;


import view.components.PasswordField;

import service.UsuarioService;
import javax.swing.JOptionPane;
import model.Persona;

import view.components.branding.LogoBadge;
import view.components.PrimaryLabel;
import core.theme.Colors;
import core.theme.Fonts;
import java.awt.Font;

public class RegistroDialog extends JDialog {
	
	

	private FormField txtNombre;

	private FormField txtApellido;

	private FormField txtEmail;

	private FormField txtUsuario;

	private PasswordField txtPassword;

	private PasswordField txtConfirmarPassword;

	private DialogButtons botones;
	
	private UsuarioService usuarioService;

	public RegistroDialog() {
		
		 usuarioService = new UsuarioService();

	    configurarVentana();

	    inicializarComponentes();
	    
	    configurarEventos();

	}

	private void configurarVentana() {

	    setTitle("Crear Cuenta");

	    setSize(520, 860);

	    setLocationRelativeTo(null);

	    setModal(true);

	    setResizable(false);

	    setLayout(null);

	    getContentPane().setBackground(Colors.BACKGROUND);

	}
    
	private void inicializarComponentes() {

	    LogoBadge logo = new LogoBadge();

	    logo.setBounds(205, 20, 100, 70);

	    add(logo);

	    PrimaryLabel lblTitulo = new PrimaryLabel("Crear Cuenta");

	    lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));

	    lblTitulo.setForeground(Colors.TEXT_PRIMARY);

	    lblTitulo.setBounds(135, 105, 260, 40);

	    add(lblTitulo);

	    PrimaryLabel lblSubtitulo =
	            new PrimaryLabel("Completa tus datos para comenzar");

	    lblSubtitulo.setFont(Fonts.SMALL);

	    lblSubtitulo.setForeground(Colors.TEXT_SECONDARY);

	    lblSubtitulo.setBounds(120, 145, 300, 25);

	    add(lblSubtitulo);

	    txtNombre = crearCampo(
	            "Nombre",
	            35,
	            200,
	            450
	    );

	    txtApellido = crearCampo(
	            "Apellido",
	            35,
	            290,
	            450
	    );

	    txtEmail = crearCampo(
	            "Email",
	            35,
	            380,
	            450
	    );

	    txtUsuario = crearCampo(
	            "Usuario",
	            35,
	            470,
	            450
	    );

	    txtPassword = new PasswordField(
	            "Contraseña",
	            450
	    );

	    txtPassword.setLocation(
	            35,
	            560
	    );

	    add(txtPassword);

	    txtConfirmarPassword = new PasswordField(
	            "Confirmar Contraseña",
	            450
	    );

	    txtConfirmarPassword.setLocation(
	            35,
	            650
	    );

	    add(txtConfirmarPassword);

	    botones = new DialogButtons();

	    botones.setBounds(
	            100,
	            755,
	            320,
	            45
	    );

	    add(botones);

	}
    
    private FormField crearCampo(
            String titulo,
            int x,
            int y,
            int ancho) {

        FormField campo = new FormField(
                titulo,
                ancho
        );

        campo.setLocation(x, y);

        add(campo);

        return campo;

    }
    
    private boolean validarFormulario() {

        if (txtNombre.getText().isBlank()) {

            JOptionPane.showMessageDialog(this,
                    "Ingrese el nombre.");

            txtNombre.requestFocusField();

            return false;
        }

        if (txtApellido.getText().isBlank()) {

            JOptionPane.showMessageDialog(this,
                    "Ingrese el apellido.");

            txtApellido.requestFocusField();

            return false;
        }

        if (txtEmail.getText().isBlank()) {

            JOptionPane.showMessageDialog(this,
                    "Ingrese el email.");

            txtEmail.requestFocusField();

            return false;
        }

        if (txtUsuario.getText().isBlank()) {

            JOptionPane.showMessageDialog(this,
                    "Ingrese un usuario.");

            txtUsuario.requestFocusField();

            return false;
        }

        if (txtPassword.getText().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una contraseña."
            );

            txtPassword.requestFocusField();

            return false;

        }

        if (!txtPassword.getText().equals(
                txtConfirmarPassword.getText())) {

            JOptionPane.showMessageDialog(
                    this,
                    "Las contraseñas no coinciden."
            );

            return false;

        }

        return true;

    }
    
    private void guardar() {

        if (!validarFormulario()) {

            return;

        }

        Persona persona = new Persona();

        persona.setNombre(
                txtNombre.getText()
        );

        persona.setApellido(
                txtApellido.getText()
        );

        
        
        
        
        persona.setEmail(
                txtEmail.getText()
        );

        String password = String.valueOf(
                txtPassword.getPassword()
        );

        try {

            usuarioService.registrarCliente(
                    persona,
                    txtUsuario.getText(),
                    password
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Cuenta creada correctamente."
            );

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        }
        
        
        
        

        

    }
    
    private void configurarEventos() {

        botones.getBtnCancelar().addActionListener(e -> dispose());

        botones.getBtnGuardar().addActionListener(e -> guardar());

    }
    
   

}