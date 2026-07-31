package view.components;

import java.awt.Color;
import java.awt.Font;

public class DashboardCard extends BasePanel {

    private PrimaryLabel lblTitulo;
    private PrimaryLabel lblValor;

    public DashboardCard(String titulo, String valor) {

        setLayout(null);

        setBackground(Color.WHITE);

        setSize(220, 120);

        lblTitulo = new PrimaryLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTitulo.setBounds(20, 15, 180, 25);

        lblValor = new PrimaryLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblValor.setBounds(20, 50, 180, 40);

        add(lblTitulo);
        add(lblValor);

    }

    public void setValor(String valor) {

        lblValor.setText(valor);

    }

}