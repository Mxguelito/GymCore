-- =====================================================================
-- Proyecto.....: GymCore
-- Version......: V2
-- Nombre.......: create_personas
-- Descripcion..: Crea la entidad base Persona del sistema
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-30
-- Dependencia..: V1
-- =====================================================================

CREATE TABLE persona (

    id_persona INT AUTO_INCREMENT PRIMARY KEY,

    nombre VARCHAR(100) NOT NULL,

    apellido VARCHAR(100) NOT NULL,

    dni VARCHAR(20) UNIQUE,

    telefono VARCHAR(30),

    email VARCHAR(150) UNIQUE,

    fecha_nacimiento DATE,

    sexo VARCHAR(20),

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    fecha_creacion TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP,

    fecha_actualizacion TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP

);