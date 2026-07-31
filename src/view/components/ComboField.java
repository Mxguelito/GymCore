package view.components;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboField<T> extends JPanel {

    private PrimaryLabel lblCampo;

    private JComboBox<T> combo;

    public ComboField(String titulo, int ancho) {

        setLayout(null);
        setOpaque(false);
        setSize(ancho, 70);

        lblCampo = new PrimaryLabel(titulo);
        lblCampo.setBounds(0, 0, ancho, 25);

        combo = new JComboBox<>();
        combo.setBounds(0, 30, ancho, 40);

        add(lblCampo);
        add(combo);

    }
    public ComboField(String titulo) {

        this(titulo, 380);

    }

    public JComboBox<T> getCombo() {
        return combo;
    }
    
    public void agregarItem(T item) {

        combo.addItem(item);

    }

    public void limpiar() {

        combo.removeAllItems();

    }

    public T getSelectedItem() {

        return (T) combo.getSelectedItem();

    }

    public void setSelectedItem(T item) {

        combo.setSelectedItem(item);

    }

}