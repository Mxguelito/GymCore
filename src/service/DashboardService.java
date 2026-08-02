package service;

import dao.DashboardDAO;

public class DashboardService {

    private DashboardDAO dao;

    public DashboardService() {
        dao = new DashboardDAO();
    }

    public int getClientes() {
        return dao.contarClientes();
    }

    public int getEntrenadores() {
        return dao.contarEntrenadores();
    }

    public int getRutinas() {
        return dao.contarRutinas();
    }

    public int getObjetivos() {
        return dao.contarObjetivos();
    }

    public int getGruposMusculares() {
        return dao.contarGruposMusculares();
    }

    public int getNiveles() {
        return dao.contarNiveles();
    }
    
    public int obtenerCantidadClientes() {
        return getClientes();
    }

    public int obtenerCantidadEntrenadores() {
        return getEntrenadores();
    }

    public int obtenerCantidadRutinas() {
        return getRutinas();
    }

    public int obtenerCantidadObjetivos() {
        return getObjetivos();
    }

    public int obtenerCantidadGruposMusculares() {
        return getGruposMusculares();
    }

    public int obtenerCantidadNiveles() {
        return getNiveles();
    }

    public int obtenerTotalPagos() {
        return dao.contarPagos();
    }

}