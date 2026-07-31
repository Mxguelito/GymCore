-- =====================================================================
-- Proyecto.....: GymCore
-- Version......: V5
-- Nombre.......: create_niveles
-- Descripcion..: Crea la tabla de niveles de entrenamiento
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-30
-- Dependencia..: V4
-- =====================================================================

CREATE TABLE nivel (

    id_nivel INT AUTO_INCREMENT PRIMARY KEY,

    nombre VARCHAR(100) NOT NULL UNIQUE,

    descripcion VARCHAR(255),

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    fecha_creacion TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP,

    fecha_actualizacion TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP

);

CREATE INDEX idx_nivel_nombre
ON nivel(nombre);