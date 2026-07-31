-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: V10
-- Nombre........: create_grupos_musculares
-- Descripcion...: Crea la tabla de grupos musculares para
--                 clasificar los ejercicios del gimnasio.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-07-31
-- Dependencia...: V9
-- ==========================================================

-- ==========================================================
-- TABLA: grupo_muscular
--
-- Esta tabla almacena el catálogo de grupos musculares
-- utilizados por los ejercicios.
--
-- Ejemplos:
--
-- • Pecho
-- • Espalda
-- • Hombros
-- • Bíceps
-- • Tríceps
-- • Piernas
-- • Glúteos
-- • Gemelos
-- • Abdominales
--
-- Esta información será utilizada por la tabla
-- "ejercicio" mediante una clave foránea.
-- ==========================================================

CREATE TABLE grupo_muscular (

    id_grupo_muscular INT AUTO_INCREMENT PRIMARY KEY,

    nombre VARCHAR(100) NOT NULL,

    descripcion VARCHAR(255),

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    fecha_creacion TIMESTAMP NOT NULL
    DEFAULT CURRENT_TIMESTAMP,

    fecha_actualizacion TIMESTAMP NOT NULL
    DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP

);

-- ==========================================================
-- FIN DE LA MIGRACIÓN V10
-- ==========================================================