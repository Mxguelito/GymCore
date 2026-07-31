package view.components;

import javax.swing.JPanel;

public class CrudButtons extends JPanel {

    private PrimaryButton btnNuevo;

    private PrimaryButton btnEditar;

    private PrimaryButton btnEliminar;

    public CrudButtons() {

        configurarComponente();

        inicializarComponentes();

    }

    private void configurarComponente() {

        setLayout(null);

        setOpaque(false);

        setSize(430, 45);

    }

    private void inicializarComponentes() {

        btnNuevo = new PrimaryButton("Nuevo");

        btnEditar = new PrimaryButton("Editar");

        btnEliminar = new PrimaryButton("Eliminar");

        btnNuevo.setBounds(0, 0, 130, 40);

        btnEditar.setBounds(150, 0, 130, 40);

        btnEliminar.setBounds(300, 0, 130, 40);

        add(btnNuevo);

        add(btnEditar);

        add(btnEliminar);

    }

    public PrimaryButton getBtnNuevo() {
        return btnNuevo;
    }

    public PrimaryButton getBtnEditar() {
        return btnEditar;
    }

    public PrimaryButton getBtnEliminar() {
        return btnEliminar;
    }

}