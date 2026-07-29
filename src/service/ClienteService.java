package service;

import model.Cliente;
import dao.ClienteDAO;

public class ClienteService {
	
	private ClienteDAO clienteDAO;

	public void guardar(Cliente cliente) {

	    clienteDAO.guardar(cliente);

	}
    
    public ClienteService() {

        clienteDAO = new ClienteDAO();

    }

}