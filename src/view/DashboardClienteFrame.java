package view;

import javax.swing.JFrame;

import model.Usuario;

public class DashboardClienteFrame extends JFrame {

    public DashboardClienteFrame(Usuario usuario) {

        setTitle("Dashboard Cliente");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}