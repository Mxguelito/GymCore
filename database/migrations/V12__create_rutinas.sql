-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: V12
-- Nombre........: create_rutinas
-- Descripcion...: Crea la tabla de rutinas.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-07-31
-- Dependencia...: V11
-- ==========================================================

CREATE TABLE rutina (

    id_rutina INT AUTO_INCREMENT PRIMARY KEY,

    cliente_id INT NOT NULL,

    entrenador_id INT NOT NULL,

    nombre VARCHAR(100) NOT NULL,

    descripcion VARCHAR(255),

    fecha_inicio DATE NOT NULL,

    fecha_fin DATE,

    estado ENUM(
        'ACTIVA',
        'PAUSADA',
        'FINALIZADA'
    ) NOT NULL DEFAULT 'ACTIVA',

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    fecha_creacion TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP,

    fecha_actualizacion TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_rutina_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES cliente(id_cliente),

    CONSTRAINT fk_rutina_entrenador
        FOREIGN KEY (entrenador_id)
        REFERENCES entrenador(id_entrenador)

);