package framework.repository;

import java.util.List;

public interface Repository<T> {

    void guardar(T entidad);

    void actualizar(T entidad);

    void eliminar(Integer id);

    T buscarPorId(Integer id);

    List<T> listar();

}