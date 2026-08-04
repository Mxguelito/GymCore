package view.components;

import javax.swing.Box;
import javax.swing.BoxLayout;

import core.theme.Colors;
import core.theme.Fonts;
import view.listener.NavigationListener;

import model.Usuario;

import java.util.ArrayList;
import java.util.List;
import view.components.branding.SidebarLogo;

import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;

import view.LoginFrame;
import view.DashboardFrame;

import java.awt.Color;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class SidebarPanel extends BasePanel {

    private NavigationListener navigationListener;
    
    private Usuario usuario;
    
    private final List<MenuButton> botones = new ArrayList<>();
    
    private JPanel panelMenu;

    private JPanel panelLogout;

    public SidebarPanel(Usuario usuario) {

        this.usuario = usuario;

        inicializarComponentes();

    }

    public void setNavigationListener(NavigationListener navigationListener) {

        this.navigationListener = navigationListener;

    }
    
    public void seleccionarPantalla(String pantalla) {

        for (MenuButton boton : botones) {
            boton.setSeleccionado(false);
        }

        switch (pantalla) {

            // ADMIN
            case "DASHBOARD":
                botones.get(0).setSeleccionado(true);
                break;

            case "CLIENTES":
                botones.get(1).setSeleccionado(true);
                break;

            case "ENTRENADORES":
                botones.get(2).setSeleccionado(true);
                break;

            case "RUTINAS":
                botones.get(3).setSeleccionado(true);
                break;

            case "PAGOS":
                botones.get(4).setSeleccionado(true);
                break;

            case "CONFIGURACION":
                botones.get(5).setSeleccionado(true);
                break;

            // CLIENTE
            case "CLIENTE_INICIO":
                botones.get(0).setSeleccionado(true);
                break;

            case "CLIENTE_PERFIL":
                botones.get(1).setSeleccionado(true);
                break;

            case "CLIENTE_RUTINAS":
                botones.get(2).setSeleccionado(true);
                break;

            case "CLIENTE_PROGRESO":
                botones.get(3).setSeleccionado(true);
                break;

            case "CLIENTE_PAGOS":
                botones.get(4).setSeleccionado(true);
                break;

            // ENTRENADOR
            case "ENTRENADOR_INICIO":
                botones.get(0).setSeleccionado(true);
                break;

            case "ENTRENADOR_CLIENTES":
                botones.get(1).setSeleccionado(true);
                break;

            case "ENTRENADOR_RUTINAS":
                botones.get(2).setSeleccionado(true);
                break;

            case "ENTRENADOR_EJERCICIOS":
                botones.get(3).setSeleccionado(true);
                break;
        }

    }

    private void inicializarComponentes() {

        setBackground(Colors.PRIMARY);

        setLayout(new BorderLayout());

        panelMenu = new BasePanel();

        panelMenu.setOpaque(false);

        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        panelLogout = new BasePanel();

        panelLogout.setOpaque(false);

        panelLogout.setLayout(new BoxLayout(panelLogout, BoxLayout.Y_AXIS));

       
        
        
        panelMenu.add(new SidebarLogo());

        panelMenu.add(Box.createVerticalStrut(20));

        UserCard userCard = new UserCard(usuario);

        panelMenu.add(userCard);

        panelMenu.add(Box.createVerticalStrut(25));

        crearMenu();

        crearLogout();

        add(panelMenu, BorderLayout.CENTER);

        add(panelLogout, BorderLayout.SOUTH);
        
        

    }
    private void crearTitulo() {

        PrimaryLabel lblTitulo = new PrimaryLabel("MENÚ");

        lblTitulo.setFont(Fonts.TITLE);

        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));

        add(lblTitulo);

        add(Box.createVerticalStrut(30));

    }

    private void crearMenu() {

        String rol = usuario.getRol().getNombre();

        switch (rol) {

            case "ADMIN":

                crearMenuAdmin();

                break;

            case "CLIENTE":

                crearMenuCliente();

                break;

            case "ENTRENADOR":

                crearMenuEntrenador();

                break;

        }

    }
    
    private void crearMenuAdmin() {

        agregarBoton("Dashboard", "DASHBOARD");

        agregarBoton("Clientes", "CLIENTES");

        agregarBoton("Entrenadores", "ENTRENADORES");

        agregarBoton("Rutinas", "RUTINAS");

        agregarBoton("Pagos", "PAGOS");

        agregarBoton("Configuración", "CONFIGURACION");

    }
    
    private void crearMenuCliente() {

        agregarBoton("Inicio", "CLIENTE_INICIO");

        agregarBoton("Mi Perfil", "CLIENTE_PERFIL");

        agregarBoton("Mis Rutinas", "CLIENTE_RUTINAS");

        agregarBoton("Mi Progreso", "CLIENTE_PROGRESO");

        agregarBoton("Mis Pagos", "CLIENTE_PAGOS");
        
        if (!botones.isEmpty()) {

            botones.get(0).setSeleccionado(true);

        }

    }
    
    private void crearMenuEntrenador() {

        agregarBoton("Inicio", "ENTRENADOR_INICIO");

        agregarBoton("Mis Clientes", "ENTRENADOR_CLIENTES");

        agregarBoton("Rutinas", "ENTRENADOR_RUTINAS");

        agregarBoton("Ejercicios", "ENTRENADOR_EJERCICIOS");

    }

    private void agregarBoton(String texto, String pantalla) {

        MenuButton boton = new MenuButton(texto);
        
        boton.setAlignmentX(CENTER_ALIGNMENT); 

        botones.add(boton);

        boton.addActionListener(e -> {

            for (MenuButton b : botones) {

                b.setSeleccionado(false);

            }

            boton.setSeleccionado(true);

            if (navigationListener != null) {

                navigationListener.navegar(pantalla);

            }

        });

        panelMenu.add(boton);
        panelMenu.add(Box.createVerticalStrut(6));

    }
    
    private void crearLogout() {

    	panelLogout.add(Box.createVerticalStrut(10));

        LogoutButton botonSalir = new LogoutButton();

        botonSalir.setAlignmentX(CENTER_ALIGNMENT);

        botonSalir.addActionListener(e -> {

        	int opcion = JOptionPane.showConfirmDialog(
        	        SwingUtilities.getWindowAncestor(this),
        	        "¿Deseás cerrar la sesión actual?\n\nTendrás que volver a iniciar sesión para acceder nuevamente.",
        	        "Cerrar sesión",
        	        JOptionPane.YES_NO_OPTION,
        	        JOptionPane.QUESTION_MESSAGE
        	);

            if (opcion == JOptionPane.YES_OPTION) {

                DashboardFrame dashboard = (DashboardFrame)
                        SwingUtilities.getWindowAncestor(this);

                dashboard.dispose();

                new LoginFrame().setVisible(true);

            }

        });

        panelLogout.add(botonSalir);
        PrimaryLabel salir = new PrimaryLabel("Cerrar sesión");

        salir.setAlignmentX(CENTER_ALIGNMENT);

        salir.setForeground(new Color(240,240,240));

        salir.setFont(Fonts.SMALL);

        panelLogout.add(Box.createVerticalStrut(6));

        panelLogout.add(salir);

        panelLogout.add(Box.createVerticalStrut(20));

        

    }

}