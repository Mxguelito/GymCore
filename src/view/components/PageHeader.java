package view.components;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.theme.Colors;

public class PageHeader extends JPanel {

    private SectionTitle titulo;

    private PrimaryButton btnVolver;
    
    private Runnable accionVolver;

    public PageHeader(String texto) {
    	
    	

        setLayout(null);
        setOpaque(false);
        setSize(1000, 60);

        titulo = new SectionTitle(texto);
        titulo.setBounds(0, 0, 400, 40);

        btnVolver = new PrimaryButton("← Volver");
        btnVolver.setBounds(720, 0, 180, 40);

        add(titulo);
        add(btnVolver);
        btnVolver.addActionListener(e -> {

            if (accionVolver != null) {

                accionVolver.run();

            }

        });

    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
    
    public void setAccionVolver(Runnable accionVolver) {
        this.accionVolver = accionVolver;
    }

}