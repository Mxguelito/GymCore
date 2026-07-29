package view.components;

import javax.swing.Box;
import javax.swing.BoxLayout;

import core.theme.Colors;
import core.theme.Fonts;
import view.listener.NavigationListener;

public class SidebarPanel extends BasePanel {

    private NavigationListener navigationListener;

    public SidebarPanel() {

        inicializarComponentes();

    }

    public void setNavigationListener(NavigationListener navigationListener) {

        this.navigationListener = navigationListener;

    }

    private void inicializarComponentes() {

        setBackground(Colors.PRIMARY);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        crearTitulo();

        crearMenu();

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

        agregarBoton("Dashboard", "DASHBOARD");

        agregarBoton("Clientes", "CLIENTES");

        agregarBoton("Entrenadores", "ENTRENADORES");

        agregarBoton("Rutinas", "RUTINAS");

        agregarBoton("Pagos", "PAGOS");

    }

    private void agregarBoton(String texto, String pantalla) {

        MenuButton boton = new MenuButton(texto);

        boton.addActionListener(e -> {

            if (navigationListener != null) {

                navigationListener.navegar(pantalla);

            }

        });

        add(boton);

        add(Box.createVerticalStrut(10));

    }

}