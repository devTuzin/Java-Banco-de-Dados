-- ============================================================
-- Exercicio_8 — Passagens Aéreas
-- ============================================================

CREATE DATABASE IF NOT EXISTS sistemapassagens;
USE sistemapassagens;

-- Cadastro de cada passageiro
CREATE TABLE IF NOT EXISTS passageiros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE
);

-- Cadastro de cada voo disponível
CREATE TABLE IF NOT EXISTS voos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroVoo VARCHAR(20) NOT NULL UNIQUE,
    origem VARCHAR(50) NOT NULL,
    destino VARCHAR(50) NOT NULL
);

-- Registro de cada passagem comprada
CREATE TABLE IF NOT EXISTS passagens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomePassageiro VARCHAR(100) NOT NULL,
    numeroVoo VARCHAR(20) NOT NULL,
    tipoClasse ENUM('ECONOMICA', 'EXECUTIVA') NOT NULL,
    precoOriginal DECIMAL(10,2) NOT NULL,
    precoFinal DECIMAL(10,2) NOT NULL
);
