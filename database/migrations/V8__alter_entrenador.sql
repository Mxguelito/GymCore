-- =====================================================================
-- Proyecto.....: GymCore
-- Version......: V8
-- Nombre.......: alter_entrenador
-- Descripcion..: Agrega restricciones y auditoría a la tabla entrenador
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-31
-- Dependencia..: V7
-- =====================================================================

-- Una persona solo puede ser un entrenador
ALTER TABLE entrenador
ADD CONSTRAINT uq_entrenador_persona
UNIQUE (persona_id);

-- Fecha de creación
ALTER TABLE entrenador
ADD COLUMN fecha_creacion TIMESTAMP NOT NULL
DEFAULT CURRENT_TIMESTAMP;

-- Fecha de actualización
ALTER TABLE entrenador
ADD COLUMN fecha_actualizacion TIMESTAMP NOT NULL
DEFAULT CURRENT_TIMESTAMP
ON UPDATE CURRENT_TIMESTAMP;