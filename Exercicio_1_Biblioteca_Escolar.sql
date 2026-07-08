CREATE DATABASE IF NOT EXISTS sistemabibliotecaescolar;
USE sistemabibliotecaescolar;

CREATE TABLE IF NOT EXISTS aluno (
    matricula INT PRIMARY KEY,
    nomeAluno VARCHAR(100) NOT NULL,
    possuiMulta TINYINT(1) DEFAULT 0,
    livrosEmprestados INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS livro (
    codigoLivro INT PRIMARY KEY,
    tituloLivro VARCHAR(100) NOT NULL,
    livroDisponivel TINYINT(1) DEFAULT 1
);
