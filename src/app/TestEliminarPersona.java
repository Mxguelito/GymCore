package app;

import model.Persona;
import service.PersonaService;

public class TestEliminarPersona {

    public static void main(String[] args) {

        PersonaService service = new PersonaService();

        Persona persona = service.buscarPorId(1);

        if (persona == null) {

            System.out.println("La persona no existe.");
            return;

        }

        System.out.println("Persona encontrada: " + persona.getNombre());

        service.eliminar(persona.getIdPersona());

        Persona eliminada = service.buscarPorId(1);

        System.out.println();

        if (eliminada == null) {

            System.out.println("====================================");
            System.out.println("PERSONA ELIMINADA CORRECTAMENTE");
            System.out.println("====================================");

        } else {

            System.out.println("No se pudo eliminar la persona.");

        }

    }

}