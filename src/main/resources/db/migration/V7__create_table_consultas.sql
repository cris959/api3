CREATE TABLE consultas (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   medico_id BIGINT NOT NULL,
   paciente_id BIGINT NOT NULL,
   fecha DATETIME NOT NULL,

   CONSTRAINT fk_consultas_medico_id foreign key(medico_id) REFERENCES medicos(id),
   CONSTRAINT fk_consultas_paciente_id foreign key(paciente_id) REFERENCES pacientes(id)

);