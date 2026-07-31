-- =====================================================================
-- Proyecto.....: GymCore
-- Version......: V4
-- Nombre.......: create_objetivos
-- Descripcion..: Crea la tabla de objetivos del sistema
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-30
-- Dependencia..: V3
-- =====================================================================

CREATE TABLE objetivo (

    id_objetivo INT AUTO_INCREMENT PRIMARY KEY,

    nombre VARCHAR(100) NOT NULL UNIQUE,

    descripcion VARCHAR(255),

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    fecha_creacion TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP,

    fecha_actualizacion TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP

);