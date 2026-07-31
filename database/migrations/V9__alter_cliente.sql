-- =====================================================================
-- Proyecto.....: GymCore
-- Version......: V9
-- Nombre.......: alter_cliente
-- Descripcion..: Agrega restricciones y auditoría a la tabla cliente
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-31
-- Dependencia..: V8
-- =====================================================================

-- Una persona solo puede ser un cliente
ALTER TABLE cliente
ADD CONSTRAINT uq_cliente_persona
UNIQUE (persona_id);

-- Fecha de creación
ALTER TABLE cliente
ADD COLUMN fecha_creacion TIMESTAMP NOT NULL
DEFAULT CURRENT_TIMESTAMP;

-- Fecha de actualización
ALTER TABLE cliente
ADD COLUMN fecha_actualizacion TIMESTAMP NOT NULL
DEFAULT CURRENT_TIMESTAMP
ON UPDATE CURRENT_TIMESTAMP;