package service;

import java.util.List;

import dao.EjercicioDAO;
import framework.service.BaseService;
import model.Ejercicio;

public class EjercicioService extends BaseService<Ejercicio> {

    private final EjercicioDAO ejercicioDAO;

    public EjercicioService() {

        this.ejercicioDAO = new EjercicioDAO();

    }

    @Override
    public void guardar(Ejercicio ejercicio) {

        ejercicioDAO.guardar(ejercicio);

    }

    @Override
    public void actualizar(Ejercicio ejercicio) {

        ejercicioDAO.actualizar(ejercicio);

    }

    @Override
    public void eliminar(Integer id) {

        ejercicioDAO.eliminar(id);

    }

    @Override
    public Ejercicio buscarPorId(Integer id) {

        return ejercicioDAO.buscarPorId(id);

    }

    @Override
    public List<Ejercicio> listar() {

        return ejercicioDAO.listar();

    }
    
    public List<Ejercicio> listarPorGrupo(Integer idGrupo) {

        return ejercicioDAO.listarPorGrupo(idGrupo);

    }

}