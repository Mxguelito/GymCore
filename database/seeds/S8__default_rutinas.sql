-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: S8
-- Nombre........: default_rutinas
-- Descripcion...: Inserta rutinas iniciales.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-07-31
-- Dependencia...: V13
-- ==========================================================

INSERT INTO rutina
(
    cliente_id,
    entrenador_id,
    nombre,
    descripcion,
    fecha_inicio,
    fecha_fin,
    estado,
    activo
)
VALUES
(
    1,
    1,
    'Hipertrofia Semana 1',
    'Rutina enfocada en hipertrofia general.',
    CURDATE(),
    DATE_ADD(CURDATE(), INTERVAL 30 DAY),
    'ACTIVA',
    TRUE
),
(
    2,
    1,
    'Definicion',
    'Rutina enfocada en definicion muscular.',
    CURDATE(),
    DATE_ADD(CURDATE(), INTERVAL 45 DAY),
    'ACTIVA',
    TRUE
);