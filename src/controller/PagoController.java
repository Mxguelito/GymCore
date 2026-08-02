package controller;

import java.util.List;

import model.Pago;
import service.PagoService;

public class PagoController {

	private final PagoService pagoService;

    public PagoController() {

        pagoService = new PagoService();

    }

    public void guardar(Pago pago) {

    	pagoService.guardar(pago);

    }

    public void actualizar(Pago pago) {

    	pagoService.actualizar(pago);

    }

    public void eliminar(Integer id) {

    	pagoService.eliminar(id);

    }

    public Pago buscarPorId(Integer id) {

        return pagoService.buscarPorId(id);

    }

    public List<Pago> listar() {

        return pagoService.listar();

    }

}