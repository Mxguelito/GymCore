package view.components;

import javax.swing.JPanel;

public class DialogButtons extends JPanel {

    private PrimaryButton btnGuardar;

    private PrimaryButton btnCancelar;

    public DialogButtons() {

        configurarComponente();

        inicializarComponentes();

    }

    private void configurarComponente() {

        setLayout(null);

        setOpaque(false);

        setSize(320, 45);

    }

    private void inicializarComponentes() {

        btnGuardar = new PrimaryButton("Guardar");

        btnCancelar = new PrimaryButton("Cancelar");

        btnGuardar.setBounds(0, 0, 150, 40);

        btnCancelar.setBounds(170, 0, 150, 40);

        add(btnGuardar);

        add(btnCancelar);

    }

    public PrimaryButton getBtnGuardar() {

        return btnGuardar;

    }

    public PrimaryButton getBtnCancelar() {

        return btnCancelar;

    }

}