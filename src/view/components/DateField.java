package view.components;

import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateField extends JPanel {

    private PrimaryLabel lblCampo;

    private JFormattedTextField txtFecha;

    public DateField(String titulo, int ancho) {

        setLayout(null);
        setOpaque(false);
        setSize(ancho,70);

        lblCampo = new PrimaryLabel(titulo);
        lblCampo.setBounds(0,0,ancho,25);

        try {

            MaskFormatter mascara =
                    new MaskFormatter("##/##/####");

            txtFecha =
                    new JFormattedTextField(mascara);

        } catch (Exception e) {

            txtFecha =
                    new JFormattedTextField();

        }

        txtFecha.setBounds(0,30,ancho,40);

        add(lblCampo);
        add(txtFecha);

    }

    public String getText() {
        return txtFecha.getText();
    }

    public void setText(String texto) {
        txtFecha.setText(texto);
    }
    
    public LocalDate getDate() {

        String texto = txtFecha.getText();

        if (!texto.matches("\\d{2}/\\d{2}/\\d{4}")) {

            return null;

        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.parse(texto, formatter);

    }
    public void setDate(LocalDate fecha) {

        if (fecha == null) {

            txtFecha.setText("");

            return;

        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        txtFecha.setText(
                fecha.format(formatter)
        );

    }
    

}