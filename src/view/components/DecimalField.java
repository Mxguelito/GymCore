package view.components;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DecimalField extends JPanel {

    private PrimaryLabel lblCampo;

    private JSpinner spinner;

    public DecimalField(
            String titulo,
            int ancho,
            double minimo,
            double maximo,
            double valorInicial,
            double paso
    ) {

        configurarComponente(ancho);

        inicializarComponentes(
                titulo,
                ancho,
                minimo,
                maximo,
                valorInicial,
                paso
        );

    }

    private void configurarComponente(int ancho) {

        setLayout(null);

        setOpaque(false);

        setSize(ancho, 70);

    }

    private void inicializarComponentes(

            String titulo,

            int ancho,

            double minimo,

            double maximo,

            double valorInicial,

            double paso

    ) {

        lblCampo = new PrimaryLabel(titulo);

        lblCampo.setBounds(0, 0, ancho, 25);

        spinner = new JSpinner(

                new SpinnerNumberModel(

                        valorInicial,

                        minimo,

                        maximo,

                        paso

                )

        );

        spinner.setBounds(0, 30, ancho, 40);

        add(lblCampo);

        add(spinner);

    }

    public Double getValue() {

        return ((Number) spinner.getValue()).doubleValue();

    }

    public void setValue(Double valor) {

        spinner.setValue(valor);

    }

}