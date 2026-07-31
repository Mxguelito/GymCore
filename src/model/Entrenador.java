package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Entrenador {

    private int idEntrenador;
    private int personaId;
    private String nombrePersona;
    private String apellidoPersona;
    private String especialidad;
    private Date fechaIngreso;
    private String estado;
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;

    public Entrenador() {
    }

    public Entrenador(int idEntrenador, int personaId, String especialidad,
                      Date fechaIngreso, String estado,
                      Timestamp fechaCreacion,
                      Timestamp fechaActualizacion) {

        this.idEntrenador = idEntrenador;
        this.personaId = personaId;
        this.especialidad = especialidad;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }
    
    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {

        return getNombreCompleto();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entrenador)) return false;
        Entrenador that = (Entrenador) o;
        return idEntrenador == that.idEntrenador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEntrenador);
    }
    
    public String getNombreCompleto() {

        String nombre = nombrePersona == null ? "" : nombrePersona;
        String apellido = apellidoPersona == null ? "" : apellidoPersona;

        return (nombre + " " + apellido).trim();

    }

}