CREATE DATABASE clinica;
USE clinica;
CREATE TABLE paciente(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    idade INT NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE medico(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    crm VARCHAR(10) NOT NULL UNIQUE,
    especialidade VARCHAR(50) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE atendimento(
    id INT PRIMARY KEY AUTO_INCREMENT,
    especialidade VARCHAR(50) NOT NULL,
    data_atendimento DATE NOT NULL,
    hora_atendimento TIME NOT NULL,
    sala VARCHAR(10) NOT NULL,
    medico INT NOT NULL,
    paciente INT NOT NULL,
    foreign key (medico) references medico(id),
    foreign key (paciente) references paciente(id)
);