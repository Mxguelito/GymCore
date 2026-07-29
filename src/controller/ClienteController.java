package controller;

import model.Cliente;
import service.ClienteService;



public class ClienteController {
	
	private ClienteService clienteService;
	
	
	

	public void guardar(Cliente cliente) {

	    clienteService.guardar(cliente);

	}
    
    public ClienteController() {

        clienteService = new ClienteService();

    }

}

