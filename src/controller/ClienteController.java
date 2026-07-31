package controller;

import model.Cliente;
import service.ClienteService;

import java.util.List;

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public void guardar(Cliente cliente) {
        clienteService.guardar(cliente);
    }

    public void actualizar(Cliente cliente) {
        clienteService.actualizar(cliente);
    }

    public void eliminar(Integer id) {
        clienteService.eliminar(id);
    }

    public Cliente buscarPorId(Integer id) {
        return clienteService.buscarPorId(id);
    }

    public List<Cliente> listar() {
        return clienteService.listar();
    }

}