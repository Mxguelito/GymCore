package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.UIConstants;
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
	private void crearHeader() {

	    panelHeader = new HeaderPanel(usuario);

	    panelPrincipal.add(panelHeader);

	}


	

	

	private void crearMenu() {

	    panelMenu = new SidebarPanel();

	    panelMenu.setBounds(
	            0,
	            UIConstants.HEADER_HEIGHT,
	            UIConstants.MENU_WIDTH,
	            UIConstants.WINDOW_HEIGHT
	                    - UIConstants.HEADER_HEIGHT
	                    - UIConstants.FOOTER_HEIGHT
	    );

	    panelMenu.setNavigationListener(this);

	    panelPrincipal.add(panelMenu);

	}
	private void crearContenido() {

	    cardLayout = new CardLayout();

	    panelContenido = new JPanel(cardLayout);

	    registrarPantallas();

	    panelContenido.setBounds(
	            UIConstants.MENU_WIDTH,
	            UIConstants.HEADER_HEIGHT,
	            UIConstants.WINDOW_WIDTH - UIConstants.MENU_WIDTH,
	            UIConstants.WINDOW_HEIGHT
	                    - UIConstants.HEADER_HEIGHT
	                    - UIConstants.FOOTER_HEIGHT
	    );

	    panelPrincipal.add(panelContenido);

	}
	
	private void registrarPantallas() {

	    panelContenido.add(new DashboardPanel(), "DASHBOARD");

	    panelContenido.add(new ClientesPanel(), "CLIENTES");
	    
	    panelContenido.add(new ObjetivosPanel(), "OBJETIVOS");

	    panelContenido.add(new EntrenadoresPanel(), "ENTRENADORES");

	    panelContenido.add(new RutinasPanel(), "RUTINAS");

	    panelContenido.add(new PagosPanel(), "PAGOS");
	    
	    panelContenido.add(new NivelesPanel(), "NIVELES");
	    
	    panelContenido.add(new GruposMuscularesPanel(), "GRUPOS_MUSCULARES");
	    
	    

	}

	private void crearFooter() {

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

    private void configurarVentana() {

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

        panelPrincipal.setBackground(UIConstants.BACKGROUND);

        add(panelPrincipal);

    }
    
    @Override
    public void navegar(String pantalla) {

        System.out.println("Pantalla seleccionada: " + pantalla);
        
        cardLayout.show(panelContenido, pantalla);

    }
   

   

}
