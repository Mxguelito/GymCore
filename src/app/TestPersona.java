package app;

import java.time.LocalDate;

import model.Persona;
import service.PersonaService;

public class TestPersona {

    public static void main(String[] args) {

        Persona persona = new Persona();

        persona.setNombre("Victor");
        persona.setApellido("Montejo");
        persona.setDni("12345678");
        persona.setTelefono("1122334455");
        persona.setEmail("victor@gmail.com");
        persona.setFechaNacimiento(LocalDate.of(2000, 1, 1));
        persona.setSexo("Masculino");
        persona.setActivo(true);

        PersonaService service = new PersonaService();

        service.guardar(persona);

    }

}