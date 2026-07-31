package app;

import model.Persona;
import service.PersonaService;

public class TestActualizarPersona {

    public static void main(String[] args) {

        PersonaService service = new PersonaService();

        // Buscar la persona existente
        Persona persona = service.buscarPorId(1);

        if (persona == null) {

            System.out.println("No existe la persona.");
            return;

        }

        System.out.println("Nombre anterior: " + persona.getNombre());

        // Cambiamos algunos datos
        persona.setNombre("Miguel");
        persona.setApellido("Montejo Velasquez");
        persona.setTelefono("1199999999");

        // Actualizamos en la base
        service.actualizar(persona);

        // La volvemos a buscar para comprobar
        Persona actualizada = service.buscarPorId(1);

        System.out.println();
        System.out.println("========== RESULTADO ==========");
        System.out.println("Nombre: " + actualizada.getNombre());
        System.out.println("Apellido: " + actualizada.getApellido());
        System.out.println("Telefono: " + actualizada.getTelefono());

    }

}