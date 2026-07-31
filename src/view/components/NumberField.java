package view.components;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class NumberField extends JPanel {

    private PrimaryLabel lblCampo;

    private JSpinner spinner;

    public NumberField(
            String titulo,
            int ancho,
            int minimo,
            int maximo,
            int valorInicial
    ) {

        configurarComponente(ancho);

        inicializarComponentes(
                titulo,
                ancho,
                minimo,
                maximo,
                valorInicial
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

            int minimo,

            int maximo,

            int valorInicial

    ) {

        lblCampo = new PrimaryLabel(titulo);

        lblCampo.setBounds(0, 0, ancho, 25);

        spinner = new JSpinner(

                new SpinnerNumberModel(

                        valorInicial,

                        minimo,

                        maximo,

                        1

                )

        );

        spinner.setBounds(0, 30, ancho, 40);

        add(lblCampo);

        add(spinner);

    }

    public Integer getValue() {

        return (Integer) spinner.getValue();

    }

    public void setValue(Integer valor) {

        spinner.setValue(valor);

    }

}