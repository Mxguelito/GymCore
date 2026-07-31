-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: S7
-- Nombre........: default_ejercicios
-- Descripcion...: Inserta los ejercicios básicos del sistema.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-07-31
-- Dependencia...: V11
-- ==========================================================

INSERT INTO ejercicio
(
    grupo_muscular_id,
    nombre,
    descripcion,
    video_url
)
VALUES

-- ==========================
-- PECHO
-- ==========================

(
    1,
    'Press de banca',
    'Press plano con barra.',
    NULL
),

(
    1,
    'Press inclinado',
    'Press inclinado con barra.',
    NULL
),

(
    1,
    'Aperturas',
    'Aperturas con mancuernas.',
    NULL
),

-- ==========================
-- ESPALDA
-- ==========================

(
    2,
    'Dominadas',
    'Dominadas con agarre prono.',
    NULL
),

(
    2,
    'Remo con barra',
    'Remo inclinado con barra.',
    NULL
),

(
    2,
    'Jalón al pecho',
    'Jalón en polea alta.',
    NULL
),

-- ==========================
-- HOMBROS
-- ==========================

(
    3,
    'Press militar',
    'Press militar con barra.',
    NULL
),

(
    3,
    'Elevaciones laterales',
    'Elevaciones laterales con mancuernas.',
    NULL
),

-- ==========================
-- BÍCEPS
-- ==========================

(
    4,
    'Curl con barra',
    'Curl de bíceps con barra.',
    NULL
),

(
    4,
    'Curl martillo',
    'Curl con mancuernas agarre neutro.',
    NULL
),

-- ==========================
-- TRÍCEPS
-- ==========================

(
    5,
    'Fondos',
    'Fondos en paralelas.',
    NULL
),

(
    5,
    'Extensión en polea',
    'Extensión de tríceps en polea.',
    NULL
),

-- ==========================
-- PIERNAS
-- ==========================

(
    6,
    'Sentadilla',
    'Sentadilla con barra.',
    NULL
),

(
    6,
    'Prensa',
    'Prensa inclinada.',
    NULL
),

(
    6,
    'Peso muerto rumano',
    'Peso muerto enfocado en femorales.',
    NULL
),

-- ==========================
-- GLÚTEOS
-- ==========================

(
    7,
    'Hip Thrust',
    'Empuje de cadera con barra.',
    NULL
),

-- ==========================
-- GEMELOS
-- ==========================

(
    8,
    'Elevación de talones',
    'Elevación de gemelos de pie.',
    NULL
),

-- ==========================
-- ABDOMINALES
-- ==========================

(
    9,
    'Crunch',
    'Crunch abdominal tradicional.',
    NULL
),

(
    9,
    'Plancha',
    'Plancha isométrica.',
    NULL
);