package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.constants.UIConstants;
import view.components.FooterPanel;
import view.components.HeaderPanel;
import view.components.SidebarPanel;
import model.Usuario;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import java.awt.CardLayout;

import view.listener.NavigationListener;

import core.constants.LayoutConstants;

import view.ObjetivosPanel;

import view.NivelesPanel;
import core.theme.Colors;
import javax.swing.JScrollPane;



public class DashboardFrame extends JFrame implements NavigationListener {
	private JPanel panelPrincipal;

	private JPanel panelHeader;

	private SidebarPanel panelMenu;

	private JPanel panelContenido;

	private JPanel panelFooter;
	
	private CardLayout cardLayout;
	
	private Usuario usuario;
	
	
	public DashboardFrame(Usuario usuario) {

		this.usuario = usuario;

	    configurarVentana();

	    crearHeader();

	    crearMenu();

	    crearContenido();

	    crearFooter();

	    setVisible(true);

	}
	
	protected void crearHeader() {

	    panelHeader = new HeaderPanel(usuario);

	    panelPrincipal.add(panelHeader);

	}


	

	
     
	
	protected void crearMenu() {

		panelMenu = new SidebarPanel(usuario);
		panelMenu.setNavigationListener(this);

		JScrollPane scrollSidebar = new JScrollPane(panelMenu);

		scrollSidebar.setBorder(null);
		scrollSidebar.setHorizontalScrollBarPolicy(
		        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSidebar.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		scrollSidebar.setBounds(
		        0,
		        UIConstants.HEADER_HEIGHT,
		        UIConstants.SIDEBAR_WIDTH,
		        UIConstants.WINDOW_HEIGHT
		                - UIConstants.HEADER_HEIGHT
		                - UIConstants.FOOTER_HEIGHT
		);

		panelPrincipal.add(scrollSidebar);

	}

	protected void crearContenido() {

	    cardLayout = new CardLayout();

	    panelContenido = new JPanel(cardLayout);

	    registrarPantallas();

	    panelContenido.setBounds(
	            UIConstants.SIDEBAR_WIDTH,
	            UIConstants.HEADER_HEIGHT,
	            UIConstants.WINDOW_WIDTH - UIConstants.SIDEBAR_WIDTH,
	            UIConstants.WINDOW_HEIGHT
	                    - UIConstants.HEADER_HEIGHT
	                    - UIConstants.FOOTER_HEIGHT
	    );

	    panelPrincipal.add(panelContenido);

	}
	
	
	protected void registrarPantallas() {

	    String rol = usuario.getRol().getNombre();

	    if (rol.equals("ADMIN")) {

	        registrarPantallasAdmin();

	    } else if (rol.equals("CLIENTE")) {

	        registrarPantallasCliente();

	    } else if (rol.equals("ENTRENADOR")) {

	        registrarPantallasEntrenador();

	    }

	}
	private void registrarPantallasAdmin() {

	    panelContenido.add(new DashboardPanel(), "DASHBOARD");

	    panelContenido.add(new ClientesPanel(), "CLIENTES");

	    panelContenido.add(new ObjetivosPanel(), "OBJETIVOS");

	    panelContenido.add(new EntrenadoresPanel(), "ENTRENADORES");

	    panelContenido.add(new RutinasPanel(), "RUTINAS");

	    panelContenido.add(new PagosPanel(), "PAGOS");

	    panelContenido.add(new NivelesPanel(), "NIVELES");

	    panelContenido.add(new GruposMuscularesPanel(), "GRUPOS_MUSCULARES");
	    
	    panelContenido.add(
	            new ConfiguracionPanel(),
	            "CONFIGURACION"
	    );

	}
	
	private void registrarPantallasCliente() {

	    panelContenido.add(
	            new ClienteInicioPanel(usuario),
	            "CLIENTE_INICIO"
	    );

	    panelContenido.add(
	            new ClientePerfilPanel(usuario),
	            "CLIENTE_PERFIL"
	    );

	    panelContenido.add(
	    		new ClienteRutinasPanel(usuario),
	            "CLIENTE_RUTINAS"
	    );

	    panelContenido.add(
	            new ClienteProgresoPanel(usuario),
	            "CLIENTE_PROGRESO"
	    );

	    panelContenido.add(
	            new ClientePagosPanel(usuario),
	            "CLIENTE_PAGOS"
	    );

	}
	
	private void registrarPantallasEntrenador() {

	}
	
	

	
	protected void crearFooter() {

	    panelFooter = new FooterPanel();

	    panelFooter.setBounds(
	            0,
	            UIConstants.WINDOW_HEIGHT
	                    - UIConstants.FOOTER_HEIGHT,
	            UIConstants.WINDOW_WIDTH,
	            UIConstants.FOOTER_HEIGHT
	    );

	    panelPrincipal.add(panelFooter);

	}

	
	protected void configurarVentana() {

        setTitle("GymCore");

        setSize(
                LayoutConstants.MIN_WIDTH,
                LayoutConstants.MIN_HEIGHT
        );
        
        setMinimumSize(
                new java.awt.Dimension(
                        LayoutConstants.MIN_WIDTH,
                        LayoutConstants.MIN_HEIGHT
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelPrincipal = new JPanel();

        panelPrincipal.setLayout(null);

        panelPrincipal.setBackground(Colors.BACKGROUND);

        add(panelPrincipal);

    }
    
	@Override
	public void navegar(String pantalla) {

	    System.out.println("Pantalla seleccionada: " + pantalla);

	    cardLayout.show(panelContenido, pantalla);

	    panelMenu.seleccionarPantalla(pantalla);

	}
    public void volverAlDashboard() {

        cardLayout.show(panelContenido, "DASHBOARD");

        panelMenu.seleccionarPantalla("DASHBOARD");

    }
   

   

}
