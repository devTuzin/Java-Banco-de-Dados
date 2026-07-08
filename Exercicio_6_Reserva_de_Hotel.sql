CREATE DATABASE IF NOT EXISTS sistemareservahotel;
USE sistemareservahotel;

CREATE TABLE IF NOT EXISTS Quartos (
    idQuarto INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    ValorDiaria DECIMAL(15,2) NOT NULL,
    tipoQuartos ENUM('Simples', 'Duplo', 'Suite') NOT NULL,
    diasEstadia INT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS Chekin (
    idChekin INT AUTO_INCREMENT PRIMARY KEY,
    nomeCliente VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    dataCheckin DATETIME NOT NULL,
    dataCheckout DATETIME NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    ValorTotal DECIMAL(15,2) NOT NULL,
    pagamento ENUM('PIX', 'Dinheiro', 'Cartao') NOT NULL,
    idQuarto INT NOT NULL,
    FOREIGN KEY (idQuarto) REFERENCES Quartos(idQuarto)
);
