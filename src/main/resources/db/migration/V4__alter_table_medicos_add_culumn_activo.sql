ALTER TABLE medicos ADD COLUMN activo BOOLEAN;
UPDATE medicos SET activo = true;