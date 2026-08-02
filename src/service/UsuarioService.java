package service;
import dao.UsuarioDAO;
import model.Usuario;

import dao.PersonaDAO;
import dao.ClienteDAO;

import model.Cliente;
import model.Persona;
import model.Rol;

import java.time.LocalDate;

import core.security.PasswordUtils;

public class UsuarioService {
	
	private PersonaDAO personaDAO;

	private ClienteDAO clienteDAO;
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioService() {

	    usuarioDAO = new UsuarioDAO();

	    personaDAO = new PersonaDAO();

	    clienteDAO = new ClienteDAO();

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

	    if (!PasswordUtils.verify(password, usuario.getPasswordHash())) {

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
	
	
	
	public void registrarCliente(
	        Persona persona,
	        String username,
	        String password
	) {

	    persona.setActivo(true);
	    
	    if (usuarioDAO.existeUsername(username)) {

	        throw new RuntimeException(
	            "El nombre de usuario ya existe."
	        );

	    }
	    
	    if (personaDAO.existeEmail(persona.getEmail())) {

	        throw new RuntimeException(
	                "Ese email ya está registrado."
	        );

	    }

	    personaDAO.guardar(persona);
	    
	    Cliente cliente = new Cliente();

	    cliente.setPersona(persona);

	    cliente.setFechaIngreso(LocalDate.now());

	    cliente.setEstado("ACTIVO");

	    clienteDAO.guardar(cliente);
	    
	    
	    Usuario usuario = new Usuario();

	    usuario.setPersona(persona);

	    usuario.setUsername(username);

	    usuario.setPasswordHash(
	            PasswordUtils.hash(password)
	    );

	    usuario.setEstado("ACTIVO");
	    
	    Rol rol = new Rol();

	    rol.setIdRol(4);// CLIENTE

	    usuario.setRol(rol);
	    
	    usuarioDAO.guardar(usuario);

	}
	
	
	public void actualizar(Usuario usuario) {

	    usuarioDAO.actualizar(usuario);

	}
}



