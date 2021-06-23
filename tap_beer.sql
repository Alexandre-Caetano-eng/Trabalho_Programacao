-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 21-Jun-2021 às 15:53
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

-- --------------------------------------------------------

--
-- Estrutura da tabela `torneira`
--

CREATE TABLE `torneira` (
  `id_Torneira` int(11) NOT NULL,
  `id_Produto` int(11) DEFAULT NULL,
  `localizacao` varchar(200) NOT NULL,
  `quant_Produto` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `transacao`
--

CREATE TABLE `transacao` (
  `id_Transacao` bigint(20) NOT NULL,
  `cpf_Usuario` bigint(20) NOT NULL,
  `id_Torneira` int(11) NOT NULL,
  `quant_Prod` double NOT NULL,
  `valor_Pago` double(7,2) NOT NULL,
  `data_Transacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_cartao`
--

CREATE TABLE `usuario_cartao` (
  `cpf` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `saldo` double(7,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD PRIMARY KEY (`id_Transacao`),
  ADD UNIQUE KEY `FK_Usuario` (`cpf_Usuario`),
  ADD UNIQUE KEY `FK_Torneira` (`id_Torneira`);

--
-- Indexes for table `usuario_cartao`
--
ALTER TABLE `usuario_cartao`
  ADD PRIMARY KEY (`cpf`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id_Produto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `torneira`
--
ALTER TABLE `torneira`
  MODIFY `id_Torneira` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transacao`
--
ALTER TABLE `transacao`
  MODIFY `id_Transacao` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario_cartao`
--
ALTER TABLE `usuario_cartao`
  MODIFY `cpf` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `torneira`
--
ALTER TABLE `torneira`
  ADD CONSTRAINT `torneira_ibfk_1` FOREIGN KEY (`id_Produto`) REFERENCES `produto` (`id_Produto`);

--
-- Limitadores para a tabela `transacao`
--
ALTER TABLE `transacao`
  ADD CONSTRAINT `transacao_ibfk_1` FOREIGN KEY (`cpf_Usuario`) REFERENCES `usuario_cartao` (`cpf`),
  ADD CONSTRAINT `transacao_ibfk_2` FOREIGN KEY (`id_Torneira`) REFERENCES `torneira` (`id_Torneira`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;