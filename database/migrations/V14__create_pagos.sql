-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: V14
-- Nombre........: create_pagos
-- Descripcion...: Crea la tabla de pagos.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-08-01
-- Dependencia...: V13
-- ==========================================================

CREATE TABLE pago (

    id_pago INT AUTO_INCREMENT PRIMARY KEY,

    cliente_id INT NOT NULL,

    fecha_pago DATE NOT NULL,

    periodo VARCHAR(30) NOT NULL,

    importe DECIMAL(10,2) NOT NULL,

    metodo_pago VARCHAR(30) NOT NULL,

    estado VARCHAR(20) NOT NULL,

    observaciones VARCHAR(255),

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_pago_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES cliente(id_cliente)

);