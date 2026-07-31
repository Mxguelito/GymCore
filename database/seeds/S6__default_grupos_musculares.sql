-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: S6
-- Nombre........: default_grupos_musculares
-- Descripcion...: Inserta los grupos musculares
--                 predeterminados del sistema.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-07-31
-- Dependencia...: V10
-- ==========================================================

-- ==========================================================
-- DATOS INICIALES
--
-- Estos registros representan el catálogo base de grupos
-- musculares utilizados por GymCore.
--
-- Todos los ejercicios deberán pertenecer a uno de estos
-- grupos musculares.
--
-- Si en el futuro se necesita agregar nuevos grupos,
-- podrán incorporarse desde el módulo de administración.
-- ==========================================================

INSERT INTO grupo_muscular
(
    nombre,
    descripcion
)
VALUES

(
    'Pecho',
    'Ejercicios destinados al desarrollo del pecho.'
),

(
    'Espalda',
    'Ejercicios destinados al desarrollo de la espalda.'
),

(
    'Hombros',
    'Ejercicios destinados al desarrollo de los hombros.'
),

(
    'Bíceps',
    'Ejercicios destinados al desarrollo de los bíceps.'
),

(
    'Tríceps',
    'Ejercicios destinados al desarrollo de los tríceps.'
),

(
    'Piernas',
    'Ejercicios destinados al desarrollo de las piernas.'
),

(
    'Glúteos',
    'Ejercicios destinados al desarrollo de los glúteos.'
),

(
    'Gemelos',
    'Ejercicios destinados al desarrollo de los gemelos.'
),

(
    'Abdominales',
    'Ejercicios destinados al fortalecimiento del abdomen.'
);

-- ==========================================================
-- FIN DEL SEED S6
-- ==========================================================