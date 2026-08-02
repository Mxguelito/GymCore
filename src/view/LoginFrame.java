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
import view.components.LinkLabel;
import view.components.PrimaryButton;
import view.components.PrimaryPasswordField;
import view.components.PrimaryTextField;

import view.dialogs.RegistroDialog;

import view.components.branding.SidebarLogo;

import core.theme.Colors;

public class LoginFrame extends JFrame {

    private JPanel panel;

    private PrimaryLabel lblLogo;
    private PrimaryLabel lblSubtitulo;

    private PrimaryLabel lblUsuario;
    private PrimaryLabel lblPassword;
    
    
    private PrimaryTextField txtUsuario;

    private PrimaryPasswordField txtPassword;

    private PrimaryButton btnIngresar;
    
    private PrimaryButton btnRegistrarse;
    
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

        
        panel.setBackground(Colors.BACKGROUND);

        add(panel);

    }

    private void inicializarComponentes() {

        crearTitulo();

        crearFormulario();

        crearBoton();

        crearBotonRegistro();

        crearFooter();

    }
    private void crearTitulo() {

    	SidebarLogo logo = new SidebarLogo(true);

        logo.setBounds(0, 30, 470, 170);

        panel.add(logo);

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
    
    private void crearBotonRegistro() {

        PrimaryLabel lblPregunta =
                new PrimaryLabel("¿No tienes una cuenta?");

        lblPregunta.setBounds(135, 540, 220, 25);

        panel.add(lblPregunta);

        LinkLabel linkRegistro =
                new LinkLabel("Registrarse");

        linkRegistro.setBounds(175, 565, 120, 25);

        linkRegistro.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                RegistroDialog dialog = new RegistroDialog();

                dialog.setVisible(true);

            }

        });

        panel.add(linkRegistro);

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

        JFrame dashboard = DashboardFactory.crearDashboard(usuario);

        dashboard.setVisible(true);

        dispose();

    }

    private void crearFooter() {

        PrimaryLabel lblFooter = new PrimaryLabel("© 2026 GymCore");

        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        lblFooter.setForeground(new Color(160, 160, 160));

        lblFooter.setHorizontalAlignment(JLabel.CENTER);

        lblFooter.setBounds(0, 645, 470, 20);

        panel.add(lblFooter);

    }
}