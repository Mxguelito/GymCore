package framework.controller;

import java.util.List;

public abstract class BaseController<T> {

    public abstract void guardar(T entidad);

    public abstract void actualizar(T entidad);

    public abstract void eliminar(Integer id);

    public abstract T buscarPorId(Integer id);

    public abstract List<T> listar();

}