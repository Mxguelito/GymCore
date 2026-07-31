package view.tablemodels;

import model.GrupoMuscular;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GrupoMuscularTableModel extends AbstractTableModel {

    private final String[] columnas = {

            "ID",
            "Nombre",
            "Descripción",
            "Estado"

    };

    private List<GrupoMuscular> gruposMusculares = new ArrayList<>();

    @Override
    public int getRowCount() {

        return gruposMusculares.size();

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

        GrupoMuscular grupo = gruposMusculares.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> grupo.getIdGrupoMuscular();

            case 1 -> grupo.getNombre();

            case 2 -> grupo.getDescripcion();

            case 3 -> grupo.getActivo() ? "ACTIVO" : "INACTIVO";

            default -> "";

        };

    }

    public void setGruposMusculares(List<GrupoMuscular> gruposMusculares) {

        this.gruposMusculares = gruposMusculares;

        fireTableDataChanged();

    }

    public void cargarGruposMusculares(List<GrupoMuscular> gruposMusculares) {

        setGruposMusculares(gruposMusculares);

    }

    public GrupoMuscular getGrupoMuscular(int fila) {

        return gruposMusculares.get(fila);

    }

}