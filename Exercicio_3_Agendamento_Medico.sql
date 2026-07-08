-- ============================================================
-- Exercicio_3 — Agendamento Médico
-- ============================================================

CREATE DATABASE IF NOT EXISTS sistemamedico;
USE sistemamedico;

-- Cadastro de cada paciente
CREATE TABLE IF NOT EXISTS pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL
);

-- Cadastro de cada médico
CREATE TABLE IF NOT EXISTS medicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50) NOT NULL
);

-- Registro de cada consulta agendada
CREATE TABLE IF NOT EXISTS consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    horario VARCHAR(10) NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);
