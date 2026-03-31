CREATE TABLE proprietario_animal (
    cpf_proprietario VARCHAR(11) UNIQUE PRIMARY KEY,
    nome VARCHAR(150),
    telefone VARCHAR(150),
    endereco VARCHAR(150),
    email VARCHAR(150)
);

CREATE TABLE animal (
    id_animal INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    cpf_proprietario VARCHAR(11),
    nome_animal VARCHAR(150),
    especie VARCHAR(150),
    raca VARCHAR(150),
    data_nascimento DATE,
    peso DOUBLE PRECISION,
    FOREIGN KEY (cpf_proprietario) REFERENCES proprietario_animal(cpf_proprietario)
);

CREATE TABLE veterinario (
    crmv VARCHAR(10) UNIQUE PRIMARY KEY,
    nome_veterinario VARCHAR(150),
    especialidade VARCHAR(150),
    telefone VARCHAR(13)
);

CREATE TABLE consulta (
    id_consulta INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_animal INTEGER,
    crmv VARCHAR(10),
    data_consulta DATE,
    hora_consulta TIME,
    diagnostico VARCHAR(150),
    valor DOUBLE PRECISION,
    FOREIGN KEY (id_animal) REFERENCES animal(id_animal),
    FOREIGN KEY (crmv) REFERENCES veterinario(crmv)
);

select * from proprietario_animal;
select * from animal;
select * from veterinario;
select * from consulta;