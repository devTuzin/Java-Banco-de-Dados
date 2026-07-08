-- ============================================================
-- Exercicio_7 — Streaming
-- ============================================================

CREATE DATABASE IF NOT EXISTS contausuario;
USE contausuario;

-- Cadastro de cada usuário com seu plano
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    tipoPlano ENUM('PADRAO', 'PREMIUM') NOT NULL,
    valorMensal DECIMAL(10,2) NOT NULL
);
