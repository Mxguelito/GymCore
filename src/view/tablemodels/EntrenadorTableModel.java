package view.tablemodels;

import model.Entrenador;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorTableModel extends AbstractTableModel {

    private final String[] columnas = {
            "ID",
            "Persona",
            "Especialidad",
            "Fecha Ingreso",
            "Estado"
    };

    private List<Entrenador> entrenadores = new ArrayList<>();

    @Override
    public int getRowCount() {
        return entrenadores.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Entrenador entrenador = entrenadores.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> entrenador.getIdEntrenador();
            case 1 -> entrenador.getNombreCompleto();
            case 2 -> entrenador.getEspecialidad();
            case 3 -> entrenador.getFechaIngreso();
            case 4 -> entrenador.getEstado();

            default -> "";
        };
    }

    public void setEntrenadores(List<Entrenador> entrenadores) {

        this.entrenadores = entrenadores;

        fireTableDataChanged();

    }

    public void cargarEntrenadores(List<Entrenador> entrenadores) {

        setEntrenadores(entrenadores);

    }

    public Entrenador getEntrenador(int fila) {

        return entrenadores.get(fila);

    }
}