package app;

import model.Persona;
import service.PersonaService;

public class TestBuscarPersona {

    public static void main(String[] args) {

        PersonaService service = new PersonaService();

        Persona persona = service.buscarPorId(1);

        if (persona != null) {

            System.out.println("====================================");
            System.out.println("PERSONA ENCONTRADA");
            System.out.println("====================================");

            System.out.println("ID: " + persona.getIdPersona());
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Apellido: " + persona.getApellido());
            System.out.println("DNI: " + persona.getDni());
            System.out.println("Telefono: " + persona.getTelefono());
            System.out.println("Email: " + persona.getEmail());
            System.out.println("Fecha Nacimiento: " + persona.getFechaNacimiento());
            System.out.println("Sexo: " + persona.getSexo());
            System.out.println("Activo: " + persona.getActivo());

        } else {

            System.out.println("No se encontró ninguna persona.");

        }

    }

}