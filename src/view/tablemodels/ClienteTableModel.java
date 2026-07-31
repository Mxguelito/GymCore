package view.tablemodels;

import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteTableModel extends BaseTableModel {

    private List<Cliente> clientes;

    public ClienteTableModel() {

        super();

        clientes = new ArrayList<>();

        inicializar();

    }

    private void inicializar() {

        addColumn("ID");
        addColumn("Nombre");
        addColumn("Apellido");
        addColumn("Email");
        addColumn("Estado");

    }

    public void cargarClientes(List<Cliente> clientes) {

        this.clientes = clientes;

        setRowCount(0);

        for (Cliente cliente : clientes) {

            addRow(new Object[]{

                    cliente.getIdCliente(),

                    cliente.getPersona().getNombre(),

                    cliente.getPersona().getApellido(),

                    cliente.getPersona().getEmail(),

                    cliente.getEstado()

            });

        }

    }

    public Cliente getCliente(int fila) {

        return clientes.get(fila);

    }

}