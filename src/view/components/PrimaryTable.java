package view.components;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.JTableHeader;
import core.theme.Colors;

import core.theme.Fonts;

public class PrimaryTable extends JScrollPane {

    private JTable table;

    public PrimaryTable() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        table = new JTable();

        configurarTabla();

        configurarHeader();

        setViewportView(table);

    }
    
    private void configurarTabla() {

        table.setRowHeight(35);

        table.setFillsViewportHeight(true);

        table.setAutoCreateRowSorter(true);

    }
    
    private void configurarHeader() {

        JTableHeader header = table.getTableHeader();

        header.setBackground(Colors.PRIMARY);

        header.setForeground(Colors.WHITE);

        header.setFont(Fonts.BUTTON);

        header.setReorderingAllowed(false);

        header.setResizingAllowed(true);

    }

    public JTable getTable() {

        return table;

    }
    
    public int getSelectedRow() {

        return table.getSelectedRow();

    }

}