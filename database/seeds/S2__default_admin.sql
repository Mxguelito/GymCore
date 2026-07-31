-- =====================================================================
-- Proyecto.....: GymCore
-- Seed.........: S2
-- Nombre.......: default_admin
-- Descripcion..: Inserta el administrador inicial del sistema
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-30
-- Dependencia..: S1
-- =====================================================================

-- ==========================================================
-- Persona del administrador
-- ==========================================================

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
    'Administrador',
    'GymCore',
    '00000000',
    '',
    'admin@gymcore.com',
    TRUE
);

-- ==========================================================
-- Usuario administrador
-- ==========================================================

INSERT INTO usuario
(
    persona_id,
    username,
    password_hash,
    estado,
    rol_id
)
VALUES
(
    LAST_INSERT_ID(),
    'admin',
    'admin123',
    'ACTIVO',
    1
);