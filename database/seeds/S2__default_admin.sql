-- Seed: S2__default_admin.sql
-- Description: Inserta el administrador inicial del sistema.
-- Author: Victor Montejo

INSERT INTO usuario
(
    username,
    password_hash,
    estado,
    rol_id
)
VALUES
(
    'admin',
    'admin123',
    'ACTIVO',
    1
);