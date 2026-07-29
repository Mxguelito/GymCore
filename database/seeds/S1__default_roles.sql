-- Seed: S1__default_roles.sql
-- Description: Inserta los roles iniciales del sistema.
-- Author: Victor Montejo

INSERT INTO rol (nombre, descripcion)
VALUES
('ADMIN', 'Administrador del sistema'),
('PROFESOR', 'Gestiona entrenamientos y rutinas'),
('RECEPCIONISTA', 'Gestiona el ingreso de socios y turnos'),
('SOCIO', 'Usuario del gimnasio');