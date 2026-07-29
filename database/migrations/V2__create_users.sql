-- ===============================================
-- Version.....: V2
-- Nombre......: create_users
-- Descripcion.: Crea la tabla de usuarios
-- Autor.......: Victor Montejo
-- Fecha.......: 2026-07-29
-- Dependencia.: V1
-- ===============================================

CREATE TABLE usuario(
	
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(100) NOT NULL UNIQUE,
	password_hash VARCHAR(255) NOT NULL,
	estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	rol_id INT NOT NULL,
	CONSTRAINT fk_usuario_rol
	FOREIGN KEY (rol_id)
	REFERENCES rol(id_rol)

);