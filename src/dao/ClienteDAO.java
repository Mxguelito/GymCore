package dao;

import model.Cliente;

public class ClienteDAO {

    public void guardar(Cliente cliente) {

        System.out.println("===== CLIENTE RECIBIDO EN DAO =====");

        System.out.println(cliente);

    }

}