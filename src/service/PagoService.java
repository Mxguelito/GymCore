package service;

import java.util.List;

import dao.PagoDAO;
import framework.service.BaseService;
import model.Pago;

public class PagoService extends BaseService<Pago> {

    private final PagoDAO pagoDAO;

    public PagoService() {

        pagoDAO = new PagoDAO();

    }

    @Override
    public void guardar(Pago pago) {

        pagoDAO.guardar(pago);

    }

    @Override
    public void actualizar(Pago pago) {

        pagoDAO.actualizar(pago);

    }

    @Override
    public void eliminar(Integer id) {

        pagoDAO.eliminar(id);

    }

    @Override
    public Pago buscarPorId(Integer id) {

        return pagoDAO.buscarPorId(id);

    }

    @Override
    public List<Pago> listar() {

        return pagoDAO.listar();

    }

}