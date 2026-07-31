-- =====================================================================
-- Proyecto.....: GymCore
-- Version......: V4
-- Nombre.......: create_clientes
-- Descripcion..: Crea la tabla de clientes del sistema
-- Autor........: Victor Montejo
-- Fecha........: 2026-07-30
-- Dependencia..: V3
-- =====================================================================

CREATE TABLE cliente (

    id_cliente INT AUTO_INCREMENT PRIMARY KEY,

    persona_id INT NOT NULL,

    objetivo_id INT,

    nivel_id INT,

    entrenador_id INT,

    fecha_ingreso DATE NOT NULL,

    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',

    CONSTRAINT fk_cliente_persona
        FOREIGN KEY (persona_id)
        REFERENCES persona(id_persona),

    CONSTRAINT fk_cliente_objetivo
        FOREIGN KEY (objetivo_id)
        REFERENCES objetivo(id_objetivo),

    CONSTRAINT fk_cliente_nivel
        FOREIGN KEY (nivel_id)
        REFERENCES nivel(id_nivel),

    CONSTRAINT fk_cliente_entrenador
        FOREIGN KEY (entrenador_id)
        REFERENCES entrenador(id_entrenador)

);