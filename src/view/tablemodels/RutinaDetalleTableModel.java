package view.tablemodels;

import model.RutinaDetalle;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class RutinaDetalleTableModel extends AbstractTableModel {

    private final String[] columnas = {

            "Orden",
            "Ejercicio",
            "Series",
            "Repeticiones",
            "Peso",
            "Descanso"

    };

    private final List<RutinaDetalle> detalles = new ArrayList<>();

    @Override
    public int getRowCount() {

        return detalles.size();

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

        RutinaDetalle detalle = detalles.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> detalle.getOrden();

            case 1 -> detalle.getEjercicio().getNombre();

            case 2 -> detalle.getSeries();

            case 3 -> detalle.getRepeticiones();

            case 4 -> detalle.getPeso();

            case 5 -> detalle.getDescanso();

            default -> "";

        };

    }

    public void agregar(RutinaDetalle detalle) {

        detalles.add(detalle);

        fireTableRowsInserted(
                detalles.size() - 1,
                detalles.size() - 1
        );

    }

    public void actualizar(int fila, RutinaDetalle detalle) {

        detalles.set(fila, detalle);

        fireTableRowsUpdated(fila, fila);

    }

    public void eliminar(int fila) {

        detalles.remove(fila);

        fireTableRowsDeleted(fila, fila);

    }

    public RutinaDetalle getDetalle(int fila) {

        return detalles.get(fila);

    }

    public List<RutinaDetalle> getDetalles() {

        return detalles;

    }

    public void limpiar() {

        detalles.clear();

        fireTableDataChanged();

    }
    
    public void cargarDetalles(List<RutinaDetalle> lista) {

        detalles.clear();

        if (lista != null) {

            detalles.addAll(lista);

        }

        fireTableDataChanged();

    }

}