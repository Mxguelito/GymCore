package view.tablemodels;

import model.Rutina;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class RutinaTableModel extends AbstractTableModel {

    private final String[] columnas = {

            "ID",
            "Cliente",
            "Entrenador",
            "Nombre",
            "Inicio",
            "Estado"

    };

    private List<Rutina> rutinas = new ArrayList<>();

    @Override
    public int getRowCount() {

        return rutinas.size();

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
    public Object getValueAt(int row, int column) {

        Rutina rutina = rutinas.get(row);

        return switch (column) {

            case 0 -> rutina.getIdRutina();

            case 1 -> rutina.getCliente()
                    .getPersona()
                    .getNombreCompleto();

            case 2 -> rutina.getEntrenador()
            .getNombreCompleto();

            case 3 -> rutina.getNombre();

            case 4 -> rutina.getFechaInicio();

            case 5 -> rutina.getEstado();

            default -> "";

        };

    }

    public void cargarRutinas(List<Rutina> lista) {

        rutinas = lista;

        fireTableDataChanged();

    }

    public Rutina getRutina(int fila) {

        return rutinas.get(fila);

    }

}