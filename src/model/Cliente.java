package model;

import java.time.LocalDate;

public class Cliente {

    private int idCliente;
    private Persona persona;
    private LocalDate fechaIngreso;
    private String estado;

    public Cliente() {
    }

    public Cliente(Persona persona,
                   LocalDate fechaIngreso,
                   String estado) {

        this.persona = persona;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;

    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {

        return persona.getNombreCompleto();

    }

}