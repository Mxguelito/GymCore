package model;

import java.time.LocalDateTime;

public class Usuario {

    private int id;

    private String username;

    private String passwordHash;

    private String estado;

    private int rolId;

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

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
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

	public Usuario(String username, String passwordHash, String estado, int rolId) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.estado = estado;
		this.rolId = rolId;
	}
    
    

}