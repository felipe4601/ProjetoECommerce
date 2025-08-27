CREATE SCHEMA clinica;

CREATE TABLE clinica.medico(
	id_medico INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome TEXT NOT NULL,
	crm TEXT NOT NULL,
	especialidade TEXT NOT NULL
);

CREATE TABLE clinica.paciente(
	id_paciente INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome TEXT NOT NULL,
	cpf TEXT NOT NULL,
	idade INT NOT NULL, 
	data_nascimento DATE NOT NULL
);

CREATE TABLE clinica.clinica(
	id_clinica INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome INT NOT NULL,
	endereco TEXT,
	descricao TEXT NOT NULL
);

CREATE TABLE clinica.consulta(
	id_consulta INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	data_consulta TIMESTAMPTZ NOT NULL,
	medico_id INT NOT NULL,
	clinica_id INT NOT NULL,
	valor NUMERIC(10,4),
	FOREIGN KEY (medico_id) REFERENCES  clinica.medico(id_medico),
	FOREIGN KEY (clinica_id) REFERENCES  clinica.clinica(id_clinica),
	paciente_id INT NOT NULL REFERENCES clinica.paciente(id_paciente)
	
);

-- Apagar Tabela - DROP

/*
DROP TABLE clinica.consulta;
DROP TABLE clinica.clinica;
DROP TABLE clinica.paciente;
DROP TABLE clinica.medico;
*/




