ALTER TABLE pacientes ADD COLUMN activo BOOLEAN;
UPDATE pacientes SET activo = true;