package app;

import java.util.List;

import model.Persona;
import service.PersonaService;

public class TestListarPersonas {

    public static void main(String[] args) {

        PersonaService service = new PersonaService();

        List<Persona> personas = service.listar();

        System.out.println("====================================");
        System.out.println("LISTA DE PERSONAS");
        System.out.println("====================================");

        if (personas.isEmpty()) {

            System.out.println("No hay personas registradas.");

        } else {

            for (Persona persona : personas) {

                System.out.println("------------------------------");
                System.out.println("ID: " + persona.getIdPersona());
                System.out.println("Nombre: " + persona.getNombre());
                System.out.println("Apellido: " + persona.getApellido());
                System.out.println("DNI: " + persona.getDni());
                System.out.println("Telefono: " + persona.getTelefono());
                System.out.println("Email: " + persona.getEmail());
                System.out.println("Fecha Nacimiento: " + persona.getFechaNacimiento());
                System.out.println("Sexo: " + persona.getSexo());
                System.out.println("Activo: " + persona.getActivo());

            }

        }

    }

}