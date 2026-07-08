-- ============================================================
-- Exercicio_5 — Delivery Restaurante
-- ============================================================

CREATE DATABASE IF NOT EXISTS sistemadelivery;
USE sistemadelivery;

-- Cadastro de cada usuário do sistema
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

-- Produtos disponíveis no cardápio
CREATE TABLE IF NOT EXISTS produtos (
    codigo INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

-- Registro de cada pedido realizado
CREATE TABLE IF NOT EXISTS pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_login VARCHAR(100) NOT NULL,
    numeroPedido INT NOT NULL,
    tipoPagamento VARCHAR(20) NOT NULL,
    totalProdutos DECIMAL(10,2) NOT NULL,
    taxaEntrega DECIMAL(10,2) NOT NULL,
    totalFinal DECIMAL(10,2) NOT NULL,
    dataPedido DATETIME NOT NULL,
    FOREIGN KEY (usuario_login) REFERENCES usuarios(login)
);
