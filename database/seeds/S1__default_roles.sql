-- =====================================================================
-- Seed.........: S1
-- Nombre.......: default_roles
-- Descripcion..: Inserta los roles iniciales del sistema
-- Autor........: Victor Montejo
-- =====================================================================

INSERT INTO rol (nombre, descripcion)
VALUES
('ADMIN', 'Administrador del sistema'),
('ENTRENADOR', 'Gestiona clientes y rutinas'),
('RECEPCIONISTA', 'Gestiona clientes, pagos y asistencias');