-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 05-Jul-2021 às 00:03
-- Versão do servidor: 5.7.25
-- versão do PHP: 7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tap_beer`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id_Produto` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `valor` double(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id_Produto`, `nome`, `valor`) VALUES
(1, 'kaiser', 10.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `torneira`
--

CREATE TABLE `torneira` (
  `id_Torneira` int(11) NOT NULL,
  `id_Produto` int(11) DEFAULT NULL,
  `localizacao` varchar(200) NOT NULL,
  `quant_Produto` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `torneira`
--

INSERT INTO `torneira` (`id_Torneira`, `id_Produto`, `localizacao`, `quant_Produto`) VALUES
(1, 1, 'Anita Garibaldi nº', 500);

-- --------------------------------------------------------

--
-- Estrutura da tabela `transacao`
--

CREATE TABLE `transacao` (
  `id_Transacao` bigint(20) NOT NULL,
  `cpf_Usuario` varchar(11) NOT NULL,
  `nome_Produto` varchar(50) NOT NULL,
  `quant_Prod` double NOT NULL,
  `valor_Pago` double(7,2) NOT NULL,
  `data_Transacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `transacao`
--

INSERT INTO `transacao` (`id_Transacao`, `cpf_Usuario`, `nome_Produto`, `quant_Prod`, `valor_Pago`, `data_Transacao`) VALUES
(6, '12345678909', 'kaiser', 0.11060002995, 1.11, '2021-07-04 23:56:54'),
(7, '12345678909', 'kaiser', 0.11862308504999998, 1.19, '2021-07-05 00:02:22');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_cartao`
--

CREATE TABLE `usuario_cartao` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `saldo` double(7,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario_cartao`
--

INSERT INTO `usuario_cartao` (`cpf`, `nome`, `saldo`) VALUES
('12345678909', 'teste', 7.70);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id_Produto`);

--
-- Indexes for table `torneira`
--
ALTER TABLE `torneira`
  ADD PRIMARY KEY (`id_Torneira`),
  ADD UNIQUE KEY `FK_Prod` (`id_Produto`);

--
-- Indexes for table `transacao`
--
ALTER TABLE `transacao`
  ADD PRIMARY KEY (`id_Transacao`);

--
-- Indexes for table `usuario_cartao`
--
ALTER TABLE `usuario_cartao`
  ADD PRIMARY KEY (`cpf`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id_Produto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `torneira`
--
ALTER TABLE `torneira`
  MODIFY `id_Torneira` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transacao`
--
ALTER TABLE `transacao`
  MODIFY `id_Transacao` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `torneira`
--
ALTER TABLE `torneira`
  ADD CONSTRAINT `torneira_ibfk_1` FOREIGN KEY (`id_Produto`) REFERENCES `produto` (`id_Produto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
