package model;

public class RutinaDetalle {

    private Integer idRutinaDetalle;

    private Rutina rutina;

    private Ejercicio ejercicio;

    private Integer series;

    private Integer repeticiones;

    private Double peso;

    private Integer descanso;

    private Integer orden;

    public RutinaDetalle() {

    }

    public Integer getIdRutinaDetalle() {
        return idRutinaDetalle;
    }

    public void setIdRutinaDetalle(Integer idRutinaDetalle) {
        this.idRutinaDetalle = idRutinaDetalle;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getDescanso() {
        return descanso;
    }

    public void setDescanso(Integer descanso) {
        this.descanso = descanso;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

}