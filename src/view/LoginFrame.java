package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import service.UsuarioService;
import model.Usuario;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.components.PrimaryLabel;
import view.components.PrimaryButton;
import view.components.PrimaryPasswordField;
import view.components.PrimaryTextField;

public class LoginFrame extends JFrame {

    private JPanel panel;

    private PrimaryLabel lblLogo;
    private PrimaryLabel lblSubtitulo;

    private PrimaryLabel lblUsuario;
    private PrimaryLabel lblPassword;
    
    
    private PrimaryTextField txtUsuario;

    private PrimaryPasswordField txtPassword;

    private PrimaryButton btnIngresar;
    
    private UsuarioService usuarioService;

    public LoginFrame() {

        configurarVentana();
        usuarioService = new UsuarioService();
        
        inicializarComponentes();

        setVisible(true);
    }

    private void configurarVentana() {

        setTitle("GymCore");

        setSize(470, 720);

        setResizable(false);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        panel.setLayout(null);

        // Fondo estilo Apple
        panel.setBackground(new Color(241, 245, 249));

        add(panel);

    }

    private void inicializarComponentes() {

        crearTitulo();

        crearFormulario();

        crearBoton();

        crearFooter();

    }
    private void crearTitulo() {

        //---------------- LOGO ----------------//

    	lblLogo = new PrimaryLabel("GYMCORE");

        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 42));

        lblLogo.setForeground(new Color(15, 23, 42));

        lblLogo.setBounds(100, 55, 280, 55);

        panel.add(lblLogo);

        //---------------- SUBTITULO ----------------//

        lblSubtitulo = new PrimaryLabel("Fitness Management System");

        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        lblSubtitulo.setForeground(new Color(100, 116, 139));

        lblSubtitulo.setBounds(70, 110, 320, 30);

        panel.add(lblSubtitulo);

    }

    private void crearFormulario() {

        //---------------- USUARIO ----------------//

    	 lblUsuario = new PrimaryLabel("Usuario");
    	 lblUsuario.setBounds(60,220,150,25);
    	  panel.add(lblUsuario);

    	  
        txtUsuario = new PrimaryTextField();
        txtUsuario.setBounds(60, 250, 340, 48);
        panel.add(txtUsuario);

        //---------------- PASSWORD ----------------//

        lblPassword = new PrimaryLabel("Contraseña");
        lblPassword.setBounds(60,335,150,25);
        panel.add(lblPassword);

        txtPassword = new PrimaryPasswordField();
        txtPassword.setBounds(60, 365, 340, 48);
        panel.add(txtPassword);

    }

    private void crearBoton() {

        //---------------- BOTON ----------------//

        btnIngresar = new PrimaryButton("Ingresar");

        btnIngresar.setBounds(60, 470, 340, 55);

        panel.add(btnIngresar);

        btnIngresar.addActionListener(e -> realizarLogin());

    }
    private void realizarLogin() {

        String username = txtUsuario.getText();

        String password = String.valueOf(txtPassword.getPassword());

        Usuario usuario = usuarioService.login(username, password);

        if (usuario == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contraseña incorrectos."
            );

            return;
        }

        DashboardFrame dashboard = new DashboardFrame(usuario);

        dashboard.setVisible(true);

        dispose();

    }

    private void crearFooter() {

        JLabel lblFooter =
                new JLabel("♠♠♠ Disciplina Perseverancia ♠♠♠");

        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblFooter.setForeground(new Color(148, 163, 184));

        lblFooter.setBounds(100, 640, 280, 30);

        panel.add(lblFooter);

    }
}