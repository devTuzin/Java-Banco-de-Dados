-- ============================================================
-- Exercicio_1 — Biblioteca Escolar
-- ============================================================

CREATE DATABASE IF NOT EXISTS sistemabibliotecaescolar;
USE sistemabibliotecaescolar;

-- Cadastro de cada aluno
CREATE TABLE IF NOT EXISTS aluno (
    matricula INT PRIMARY KEY,
    nomeAluno VARCHAR(100) NOT NULL,
    possuiMulta TINYINT(1) DEFAULT 0,
    livrosEmprestados INT DEFAULT 0
);

-- Cadastro de cada livro
CREATE TABLE IF NOT EXISTS livro (
    codigoLivro INT PRIMARY KEY,
    tituloLivro VARCHAR(100) NOT NULL,
    livroDisponivel TINYINT(1) DEFAULT 1
);
