package view.components;

import javax.swing.JPanel;

public class FormField extends JPanel {
	
	private PrimaryLabel lblCampo;

	private PrimaryTextField txtCampo;
	
	public FormField(String titulo, int ancho) {

	    configurarComponente(ancho);

	    inicializarComponentes(titulo, ancho);

	}
	
	private void configurarComponente(int ancho) {

	    setLayout(null);

	    setOpaque(false);
	    
	    setSize(ancho,70);

	}
	
	private void inicializarComponentes(String titulo, int ancho) {

	    lblCampo = new PrimaryLabel(titulo);

	    txtCampo = new PrimaryTextField();

	    lblCampo.setBounds(0, 0, ancho, 25);

	    txtCampo.setBounds(0, 30, ancho, 40);

	    add(lblCampo);

	    add(txtCampo);

	}
	
	public String getText() {

	    return txtCampo.getText();

	}
	
	public void setText(String texto) {

	    txtCampo.setText(texto);

	}
	
	public void limpiar() {

	    txtCampo.setText("");

	}
	
	public void requestFocusField() {

	    txtCampo.requestFocus();

	}
}