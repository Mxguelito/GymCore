-- ============================================================
-- Migration: V1__create_roles.sql
-- Description: Crea la tabla de roles del sistema.
-- Author: Victor Montejo
-- ============================================================

CREATE TABLE rol (
	
	id_rol INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL UNIQUE,
	descripcion VARCHAR(255)
	
	);
	