package view;

import javax.swing.JFrame;

import model.Usuario;

public class DashboardFactory {

    public static JFrame crearDashboard(Usuario usuario) {

        String rol = usuario.getRol().getNombre();

        switch (rol) {

            case "ADMIN":
                return new DashboardFrame(usuario);

            case "ENTRENADOR":
                return new DashboardEntrenadorFrame(usuario);

            case "CLIENTE":
                return new DashboardClienteFrame(usuario);

            default:
                throw new IllegalArgumentException(
                        "Rol no reconocido: " + rol
                );
        }

    }

}