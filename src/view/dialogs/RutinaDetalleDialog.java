package view.dialogs;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import controller.EjercicioController;
import controller.GrupoMuscularController;

import model.Ejercicio;
import model.GrupoMuscular;
import model.RutinaDetalle;

import view.components.ComboField;
import view.components.DecimalField;
import view.components.DialogButtons;
import view.components.NumberField;
import view.components.SectionTitle;

public class RutinaDetalleDialog extends JDialog {

    private GrupoMuscularController grupoController;

    private EjercicioController ejercicioController;

    private RutinaDetalle detalle;

    private SectionTitle lblTitulo;

    private ComboField<GrupoMuscular> cmbGrupo;

    private ComboField<Ejercicio> cmbEjercicio;

    private NumberField txtSeries;

    private NumberField txtRepeticiones;

    private DecimalField txtPeso;

    private NumberField txtDescanso;

    private DialogButtons botones;

    public RutinaDetalleDialog() {

        configurarVentana();

        inicializarComponentes();

        cargarGrupos();

        registrarEventos();

    }

    private void configurarVentana() {

        setTitle("Detalle de Rutina");

        setLayout(null);

        setSize(560, 760);

        setLocationRelativeTo(null);

        setModal(true);

        getContentPane().setBackground(Color.WHITE);

    }

    private void inicializarComponentes() {

        grupoController = new GrupoMuscularController();

        ejercicioController = new EjercicioController();

        
        lblTitulo = new SectionTitle("Agregar Ejercicio");

        cmbGrupo = new ComboField<>("Grupo Muscular",360);

        cmbEjercicio = new ComboField<>("Ejercicio",360);

        txtSeries = new NumberField(
                "Series",
                360,
                1,
                20,
                4
        );

        txtRepeticiones = new NumberField(
                "Repeticiones",
                360,
                1,
                100,
                10
        );

        txtPeso = new DecimalField(
                "Peso (Kg)",
                360,
                0,
                500,
                20,
                2.5
        );

        txtDescanso = new NumberField(
                "Descanso (seg)",
                360,
                0,
                600,
                90
        );

        botones = new DialogButtons();

        lblTitulo.setBounds(45, 25, 350, 40);

        cmbGrupo.setBounds(45, 90, 430, 70);

        cmbEjercicio.setBounds(45, 170, 430, 70);

        txtSeries.setBounds(45, 250, 430, 70);

        txtRepeticiones.setBounds(45, 330, 430, 70);

        txtPeso.setBounds(45, 410, 430, 70);

        txtDescanso.setBounds(45, 490, 430, 70);

        botones.setBounds(105, 620, 340, 45);

       
        add(lblTitulo);

        add(cmbGrupo);

        add(cmbEjercicio);

        add(txtSeries);

        add(txtRepeticiones);

        add(txtPeso);

        add(txtDescanso);

        add(botones);

    }

    private void cargarGrupos() {

        JComboBox<GrupoMuscular> combo =
                cmbGrupo.getCombo();

        combo.removeAllItems();

        for (GrupoMuscular grupo :
                grupoController.listar()) {

            combo.addItem(grupo);

        }

    }

    private void cargarEjercicios() {

        JComboBox<Ejercicio> combo =
                cmbEjercicio.getCombo();

        combo.removeAllItems();

        GrupoMuscular grupo =
                (GrupoMuscular) cmbGrupo
                        .getCombo()
                        .getSelectedItem();

        if (grupo == null) {

            return;

        }

        for (Ejercicio ejercicio :

                ejercicioController.listarPorGrupo(
                        grupo.getIdGrupoMuscular()
                )

        ) {

            combo.addItem(ejercicio);

        }

    }

    private void registrarEventos() {

        cmbGrupo.getCombo().addActionListener(e -> {

            cargarEjercicios();

        });

        botones.getBtnGuardar().addActionListener(e -> {

            guardarDetalle();

        });

        botones.getBtnCancelar().addActionListener(e -> {

            dispose();

        });

    }
    
    private void guardarDetalle() {

        if (cmbGrupo.getSelectedItem() == null) {

            return;

        }

        if (cmbEjercicio.getSelectedItem() == null) {

            return;

        }

        if (detalle == null) {

            detalle = new RutinaDetalle();

        }

        detalle.setEjercicio(
                cmbEjercicio.getSelectedItem()
        );

        detalle.setSeries(
                txtSeries.getValue()
        );

        detalle.setRepeticiones(
                txtRepeticiones.getValue()
        );

        detalle.setPeso(
                txtPeso.getValue()
        );

        detalle.setDescanso(
                txtDescanso.getValue()
        );

        dispose();

    }

    public RutinaDetalle getDetalle() {

        return detalle;

    }
    
    public void cargarDetalle(RutinaDetalle detalle) {

        this.detalle = detalle;

        
        cargarGrupos();

        cmbGrupo.getCombo().setSelectedItem(
                detalle.getEjercicio().getGrupoMuscular()
        );

        cargarEjercicios();

        cmbEjercicio.getCombo().setSelectedItem(
                detalle.getEjercicio()
        );
        
        

        txtSeries.setValue(
                detalle.getSeries()
        );

        txtRepeticiones.setValue(
                detalle.getRepeticiones()
        );

        txtPeso.setValue(
                detalle.getPeso()
        );

        txtDescanso.setValue(
                detalle.getDescanso()
        );

    }

}