package database.migration;

public class Migration {

    private String version;

    private String nombre;

    private String descripcion;

    private boolean ejecutada;
    
    private String archivo;

    public Migration() {

    }

    public Migration(String version,
                     String nombre,
                     String descripcion,
                     String archivo,
                     boolean ejecutada) {

        this.version = version;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.archivo = archivo;
        this.ejecutada = ejecutada;

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getArchivo() {

        return archivo;

    }
    
    public void setArchivo(String archivo) {

        this.archivo = archivo;

    }

    public boolean isEjecutada() {
        return ejecutada;
    }

    public void setEjecutada(boolean ejecutada) {
        this.ejecutada = ejecutada;
    }

}