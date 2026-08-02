package view;

import javax.swing.JFrame;

import model.Usuario;

public class DashboardFactory {

    public static JFrame crearDashboard(Usuario usuario) {

        return new DashboardFrame(usuario);

    }

}