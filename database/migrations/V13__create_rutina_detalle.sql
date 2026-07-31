-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: V13
-- Nombre........: create_rutina_detalle
-- Descripcion...: Crea la tabla detalle de rutinas.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-07-31
-- Dependencia...: V12
-- ==========================================================

CREATE TABLE rutina_detalle (

    id_rutina_detalle INT AUTO_INCREMENT PRIMARY KEY,

    rutina_id INT NOT NULL,

    ejercicio_id INT NOT NULL,

    series INT NOT NULL,

    repeticiones INT NOT NULL,

    peso DECIMAL(6,2),

    descanso INT,

    orden INT NOT NULL,

    CONSTRAINT fk_rutina_detalle_rutina
        FOREIGN KEY (rutina_id)
        REFERENCES rutina(id_rutina),

    CONSTRAINT fk_rutina_detalle_ejercicio
        FOREIGN KEY (ejercicio_id)
        REFERENCES ejercicio(id_ejercicio)

);