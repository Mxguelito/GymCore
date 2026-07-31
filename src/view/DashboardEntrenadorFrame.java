package view;

import javax.swing.JFrame;

import model.Usuario;

public class DashboardEntrenadorFrame extends JFrame {

    public DashboardEntrenadorFrame(Usuario usuario) {

        setTitle("Dashboard Entrenador");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}