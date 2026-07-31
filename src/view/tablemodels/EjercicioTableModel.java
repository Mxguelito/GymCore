package view.tablemodels;

import model.Ejercicio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EjercicioTableModel extends AbstractTableModel {

    private final String[] columnas = {

            "ID",
            "Grupo Muscular",
            "Ejercicio",
            "Descripción",
            "Estado"

    };

    private List<Ejercicio> ejercicios = new ArrayList<>();

    @Override
    public int getRowCount() {

        return ejercicios.size();

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

        Ejercicio ejercicio = ejercicios.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> ejercicio.getIdEjercicio();

            case 1 -> ejercicio.getGrupoMuscular().getNombre();

            case 2 -> ejercicio.getNombre();

            case 3 -> ejercicio.getDescripcion();

            case 4 -> ejercicio.getActivo() ? "ACTIVO" : "INACTIVO";

            default -> "";

        };

    }

    public void setEjercicios(List<Ejercicio> ejercicios) {

        this.ejercicios = ejercicios;

        fireTableDataChanged();

    }

    public void cargarEjercicios(List<Ejercicio> ejercicios) {

        setEjercicios(ejercicios);

    }

    public Ejercicio getEjercicio(int fila) {

        return ejercicios.get(fila);

    }

}