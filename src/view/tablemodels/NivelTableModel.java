package view.tablemodels;

import model.Nivel;

import java.util.ArrayList;
import java.util.List;

public class NivelTableModel extends BaseTableModel {

    private List<Nivel> niveles;

    public NivelTableModel() {

        niveles = new ArrayList<>();

        addColumn("ID");
        addColumn("Nombre");
        addColumn("Descripción");
        addColumn("Activo");

    }

    public void cargarNiveles(List<Nivel> niveles) {

        this.niveles = niveles;

        setRowCount(0);

        for (Nivel nivel : niveles) {

            addRow(new Object[]{

                    nivel.getIdNivel(),
                    nivel.getNombre(),
                    nivel.getDescripcion(),
                    nivel.getActivo() ? "SI" : "NO"

            });

        }

    }

    public Nivel getNivel(int fila) {

        return niveles.get(fila);

    }

}