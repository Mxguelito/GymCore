package service;
import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioService() {

	    usuarioDAO = new UsuarioDAO();

	}
	
	public Usuario login(String username, String password) {

	    Usuario usuario = usuarioDAO.buscarPorUsername(username);

	    if (usuario == null) {
	        System.out.println("❌ Usuario no encontrado");
	        return null;
	    }

	    System.out.println("Usuario encontrado: " + usuario.getUsername());
	    System.out.println("Password BD: " + usuario.getPasswordHash());
	    System.out.println("Password ingresada: " + password);
	    System.out.println("Estado: " + usuario.getEstado());

	    if (!usuario.getPasswordHash().equals(password)) {
	        System.out.println("❌ La contraseña no coincide");
	        return null;
	    }

	    if (!usuario.getEstado().equals("ACTIVO")) {
	        System.out.println("❌ El usuario no está activo");
	        return null;
	    }

	    System.out.println("✅ LOGIN CORRECTO");

	    return usuario;
	}
}
