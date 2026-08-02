package model;

import java.time.LocalDateTime;

public class Usuario {

    private int id;

    private String username;

    private String passwordHash;

    private String estado;

    private Rol rol;
    
    private Persona persona;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Rol getRol() {
	    return rol;
	}

	public void setRol(Rol rol) {
	    this.rol = rol;
	}
	
	public Persona getPersona() {
	    return persona;
	}

	public void setPersona(Persona persona) {
	    this.persona = persona;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	public Usuario() {
		
	}

	public Usuario(Persona persona,
            String username,
            String passwordHash,
            String estado,
            Rol rol) {

 this.persona = persona;
 this.username = username;
 this.passwordHash = passwordHash;
 this.estado = estado;
 this.rol = rol;
}
    
    

}