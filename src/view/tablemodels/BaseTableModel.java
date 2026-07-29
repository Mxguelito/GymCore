package view.tablemodels;

import javax.swing.table.DefaultTableModel;

public abstract class BaseTableModel extends DefaultTableModel {

    public BaseTableModel() {

        super();

    }

    @Override
    public boolean isCellEditable(int row, int column) {

        return false;

    }

}