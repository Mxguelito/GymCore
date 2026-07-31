-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: V11
-- Nombre........: create_ejercicios
-- Descripcion...: Crea la tabla de ejercicios.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-07-31
-- Dependencia...: V10
-- ==========================================================

CREATE TABLE ejercicio (

    id_ejercicio INT AUTO_INCREMENT PRIMARY KEY,

    grupo_muscular_id INT NOT NULL,

    nombre VARCHAR(100) NOT NULL,

    descripcion VARCHAR(255),

    video_url VARCHAR(255),

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    fecha_creacion TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP,

    fecha_actualizacion TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_ejercicio_grupo
        FOREIGN KEY (grupo_muscular_id)
        REFERENCES grupo_muscular(id_grupo_muscular)

);