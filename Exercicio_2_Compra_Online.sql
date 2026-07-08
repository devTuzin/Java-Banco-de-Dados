-- ============================================================
-- Exercicio_2 — Compra Online
-- ============================================================

CREATE DATABASE IF NOT EXISTS compraonline;
USE compraonline;

-- Cadastro de cada cliente
CREATE TABLE IF NOT EXISTS pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    endereco VARCHAR(200) NOT NULL
);

-- Produtos disponíveis para compra
CREATE TABLE IF NOT EXISTS produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    marca VARCHAR(100) NOT NULL
);

-- Registro de cada compra realizada
CREATE TABLE IF NOT EXISTS compra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    frete DECIMAL(10,2) NOT NULL,
    FormaDePagamento VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id)
);

-- Produtos iniciais do sistema
INSERT INTO produtos (nome, preco_unitario, marca) VALUES
('Camiseta Básica', 49.90, 'Hering'),
('Calça Jeans', 129.90, 'Levi\'s'),
('Tênis Esportivo', 299.90, 'Nike'),
('Mochila Escolar', 89.90, 'Sestini'),
('Fone de Ouvido', 149.90, 'JBL'),
('Mouse Gamer', 199.90, 'Logitech'),
('Teclado Mecânico', 349.90, 'Redragon'),
('Monitor 24"', 999.90, 'LG'),
('Cadeira Gamer', 1299.90, 'DXRacer'),
('Headset', 249.90, 'HyperX');
