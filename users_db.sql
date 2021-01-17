-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 19-Dez-2020 às 00:25
-- Versão do servidor: 10.4.16-MariaDB
-- versão do PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `users_db`
--
create database users_db; 
use users_db;
-- --------------------------------------------------------

--
-- Estrutura da tabela `backpack_chall`
--

CREATE TABLE `backpack_chall` (
  `id` int(30) NOT NULL,
  `usr` varchar(15) NOT NULL,
  `chall_id` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `backpack_chall`
--

INSERT INTO `backpack_chall` (`id`, `usr`, `chall_id`) VALUES(1, 'mind', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `challenge`
--

CREATE TABLE `challenge` (
  `chall_id` int(11) NOT NULL,
  `chall_quest` varchar(30) NOT NULL,
  `chall_text` varchar(100) NOT NULL,
  `coins` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `challenge`
--

INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(1, 'mind', 'For the next week you must brush your theeths with your less used arm.', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(2, 'mind', 'Write down the 5 closest people to you and their most valuable atribute', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(3, 'mind', 'test mind 3', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(4, 'mind', 'test mind 4', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(5, 'academic', 'test academic 3', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(6, 'academic', 'test academic 4', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(7, 'academic', 'test academic 7', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(8, 'academic', 'test academic 8', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(9, 'fitness', 'test fitness 9', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(10, 'fitness', 'test fitness 10', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(11, 'fitness', 'test fitness 11', 5);
INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`) VALUES(12, 'fitness', 'test fitness 12', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `full_name` varchar(30) NOT NULL,
  `Class` varchar(15) NOT NULL,
  `Quest` varchar(30) NOT NULL,
  `wallet` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`username`, `password`, `full_name`, `Class`, `Quest`, `wallet`) VALUES('aca', '123', 'aca', 'healer', 'academic', 0);
INSERT INTO `users` (`username`, `password`, `full_name`, `Class`, `Quest`, `wallet`) VALUES('fit', '123', 'fiit', 'healer', 'fitness', 0);
INSERT INTO `users` (`username`, `password`, `full_name`, `Class`, `Quest`, `wallet`) VALUES('mind', '123', 'mind', 'healer', 'mind', 0);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `backpack_chall`
--
ALTER TABLE `backpack_chall`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usr` (`usr`),
  ADD KEY `chall_id` (`chall_id`);

--
-- Índices para tabela `challenge`
--
ALTER TABLE `challenge`
  ADD PRIMARY KEY (`chall_id`);

--
-- Índices para tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `backpack_chall`
--
ALTER TABLE `backpack_chall`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `challenge`
--
ALTER TABLE `challenge`
  MODIFY `chall_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `backpack_chall`
--
ALTER TABLE `backpack_chall`
  ADD CONSTRAINT `backpack-chall` FOREIGN KEY (`chall_id`) REFERENCES `challenge` (`chall_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `backpack-user` FOREIGN KEY (`usr`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
