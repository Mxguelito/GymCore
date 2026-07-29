package view.tablemodels;

import javax.swing.table.DefaultTableModel;

public class ClienteTableModel extends BaseTableModel{

    public ClienteTableModel() {

        super();

        inicializar();

    }

    private void inicializar() {

        addColumn("ID");
        addColumn("Nombre");
        addColumn("Apellido");
        addColumn("Email");
        addColumn("Estado");

        addRow(new Object[] {1, "Juan", "Pérez", "juan@gmail.com", "Activo"});
        addRow(new Object[] {2, "Ana", "López", "ana@gmail.com", "Activo"});
        addRow(new Object[] {3, "Carlos", "Gómez", "carlos@gmail.com", "Inactivo"});

    }

  
}