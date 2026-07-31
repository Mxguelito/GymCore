-- ==========================================================
-- Proyecto......: GymCore
-- Version.......: S9
-- Nombre........: default_rutina_detalle
-- Descripcion...: Inserta ejercicios iniciales.
-- Autor.........: Victor Montejo
-- Fecha.........: 2026-07-31
-- Dependencia...: S8
-- ==========================================================

INSERT INTO rutina_detalle
(
    rutina_id,
    ejercicio_id,
    series,
    repeticiones,
    peso,
    descanso,
    orden
)
VALUES

(1,1,4,10,80,90,1),
(1,2,3,12,30,60,2),
(1,3,3,15,12,45,3),

(2,13,4,12,60,90,1),
(2,14,3,15,80,90,2),
(2,18,3,20,NULL,45,3);