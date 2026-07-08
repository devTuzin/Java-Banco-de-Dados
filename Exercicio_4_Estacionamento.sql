CREATE DATABASE IF NOT EXISTS sistemaestacionamento;
USE sistemaestacionamento;

CREATE TABLE IF NOT EXISTS veiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    tipo VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS movimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    veiculo_placa VARCHAR(10) NOT NULL,
    horaEntrada DATETIME NOT NULL,
    FOREIGN KEY (veiculo_placa) REFERENCES veiculos(placa)
);

CREATE TABLE IF NOT EXISTS historico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    veiculo_placa VARCHAR(10) NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    horaEntrada DATETIME NOT NULL,
    horaSaida DATETIME NOT NULL,
    valorPago DECIMAL(10,2) NOT NULL
);
