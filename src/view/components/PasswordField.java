package view.components;

import javax.swing.JPanel;

public class PasswordField extends JPanel {

    private PrimaryLabel lblCampo;

    private PrimaryPasswordField txtCampo;

    public PasswordField(String titulo, int ancho) {

        configurarComponente(ancho);

        inicializarComponentes(titulo, ancho);

    }

    private void configurarComponente(int ancho) {

        setLayout(null);

        setOpaque(false);

        setSize(ancho, 70);

    }

    private void inicializarComponentes(String titulo, int ancho) {

        lblCampo = new PrimaryLabel(titulo);

        txtCampo = new PrimaryPasswordField();

        lblCampo.setBounds(0, 0, ancho, 25);

        txtCampo.setBounds(0, 30, ancho, 40);

        add(lblCampo);

        add(txtCampo);

    }

    public char[] getPassword() {

        return txtCampo.getPassword();

    }

    public void limpiar() {

        txtCampo.setText("");

    }

    public void requestFocusField() {

        txtCampo.requestFocus();

    }
    
    public String getText() {

        return String.valueOf(
                txtCampo.getPassword()
        );

    }
    
    public void setText(String texto) {

        txtCampo.setText(texto);

    }

}