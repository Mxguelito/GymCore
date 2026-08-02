package view.tablemodels;

import model.Pago;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PagoTableModel extends AbstractTableModel {

    private final String[] columnas = {

            "ID",
            "Cliente",
            "Periodo",
            "Importe",
            "Estado",
            "Fecha"

    };

    private List<Pago> pagos = new ArrayList<>();

    @Override
    public int getRowCount() {

        return pagos.size();

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

        Pago pago = pagos.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> pago.getIdPago();

            case 1 -> pago.getCliente()
                          .getPersona()
                          .getNombreCompleto();

            case 2 -> pago.getPeriodo();

            case 3 -> pago.getImporte();

            case 4 -> pago.getEstado();

            case 5 -> pago.getFechaPago();

            default -> "";

        };

    }

    public void cargarPagos(List<Pago> lista) {

        pagos = lista;

        fireTableDataChanged();

    }

    public Pago getPago(int fila) {

        return pagos.get(fila);

    }

}