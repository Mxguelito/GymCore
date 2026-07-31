-- =====================================================================
-- Proyecto.....: GymCore
-- Version......: V5
-- Nombre.......: create_entrenadores
-- Descripcion..: Crea la tabla de entrenadores
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-30
-- Dependencia..: V4
-- =====================================================================

CREATE TABLE entrenador (

    id_entrenador INT AUTO_INCREMENT PRIMARY KEY,

    persona_id INT NOT NULL,

    especialidad VARCHAR(100),

    fecha_ingreso DATE NOT NULL,

    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',

    CONSTRAINT fk_entrenador_persona
        FOREIGN KEY (persona_id)
        REFERENCES persona(id_persona)

);

CREATE INDEX idx_entrenador_persona
ON entrenador(persona_id);