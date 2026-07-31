package view.tablemodels;

import model.Objetivo;

import java.util.ArrayList;
import java.util.List;

public class ObjetivoTableModel extends BaseTableModel {

    private List<Objetivo> objetivos;

    public ObjetivoTableModel() {

        super();

        objetivos = new ArrayList<>();

        inicializar();

    }

    private void inicializar() {

        addColumn("ID");
        addColumn("Nombre");
        addColumn("Descripción");
        addColumn("Activo");

    }

    public void cargarObjetivos(List<Objetivo> objetivos) {

        this.objetivos = objetivos;

        setRowCount(0);

        for (Objetivo objetivo : objetivos) {

            addRow(new Object[]{

                    objetivo.getIdObjetivo(),
                    objetivo.getNombre(),
                    objetivo.getDescripcion(),
                    objetivo.getActivo() ? "SI" : "NO"

            });

        }

    }

    public Objetivo getObjetivo(int fila) {

        return objetivos.get(fila);

    }

}