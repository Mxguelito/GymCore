-- =====================================================================
-- Proyecto.....: GymCore
-- Seed.........: S5
-- Nombre.......: default_entrenador
-- Descripcion..: Inserta un entrenador inicial
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-30
-- =====================================================================

INSERT INTO persona
(
    nombre,
    apellido,
    dni,
    telefono,
    email,
    activo
)
VALUES
(
    'Juan',
    'Entrenador',
    '11111111',
    '1122334455',
    'entrenador@gymcore.com',
    TRUE
);

INSERT INTO entrenador
(
    persona_id,
    especialidad,
    fecha_ingreso,
    estado
)
VALUES
(
    LAST_INSERT_ID(),
    'Musculación',
    CURDATE(),
    'ACTIVO'
);