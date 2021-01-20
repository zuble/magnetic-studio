-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 18-Jan-2021 às 15:09
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

-- --------------------------------------------------------

--
-- Estrutura da tabela `backpack_chall`
--

CREATE TABLE `backpack_chall` (
  `id` int(30) NOT NULL,
  `usr` varchar(15) NOT NULL,
  `chall_id` int(30) NOT NULL,
  `chall_quest` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `backpack_item`
--

CREATE TABLE `backpack_item` (
  `id` int(50) NOT NULL,
  `usr` varchar(15) NOT NULL,
  `item_id` int(30) NOT NULL,
  `state` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `challenge`
--

CREATE TABLE `challenge` (
  `chall_id` int(30) NOT NULL,
  `chall_quest` varchar(30) NOT NULL,
  `chall_text` varchar(500) NOT NULL,
  `coins` int(30) NOT NULL,
  `wisdom` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `challenge`
--

INSERT INTO `challenge` (`chall_id`, `chall_quest`, `chall_text`, `coins`, `wisdom`) VALUES
(1, 'mind', 'To understand a bit about how remembering works, consider the “telephone game”. Over time the message passed on the game can become very different from the original. Here is your mission: get with your family or friends and talk about some event that you all experienced together then check how many different versions of the same event will there be. \r\n', 5, 10),
(2, 'mind', 'When you write by hand, you actually give your brain’s encoding process a boost. Here is your mind mission: forget about typing and remember the handwriting. You can start by writing down your agenda, school\'s studies or even your dreams.', 5, 10),
(3, 'mind', 'We can improve our perceptions of others by developing empathetic listening skills, becoming aware of stereotypes and prejudice, and engaging in self-reflection. Write down stereotypes or pre-conceptions you used to have about anything and why did it change.', 5, 10),
(4, 'mind', 'Crossword puzzles, Chess and Sudoku are all games that can improve your memory. Choose one of them and play for two weeks, everyday, then let me know if it helped or if I\'m wrong. Tip 1: I\'m never wrong. Tip 2: Discipline is the key.', 5, 10),
(5, 'mind', 'According to researchers, listening to sounds such as music and noise has a significant effect on our moods and emotions because of brain dopamine regulation. Make a playlist for at least 3 of the 7 following emotions: anger, fear, disgust, happiness, sadness, surprise and contempt.', 5, 10),
(6, 'mind', 'The very act of smiling releases feel-good chemicals in your brain that may also help with nervousness. Even when you don’t feel like it, try cracking a smile.', 5, 10),
(7, 'mind', 'Art offers a safe arena for exploring feelings and expressing them. Here is your mission: use any type of art to express yourself and then try to interpret it. Write down any insight you get from this experience.', 5, 10),
(8, 'mind', 'Exercise your creativity like a muscle! Your mission is to set a goal in any area of your life and look for inspirations. Don`t forget to use an organized place to save it all!', 5, 10),
(9, 'mind', 'Have you heard about the art of not giving a f*? Well, you should. It works based on three things: money, time and energy. Set a list of things that pisses you off and ask yourself if you really want or even have money, time or energy to spend on all of that. If the answer is no, you know what to do!', 5, 10),
(10, 'mind', 'It`s time to get nostalgic and bring some feelings back to surface. Go through some good memories and let yourself be grateful for the good old days! Tip: Use a song to teleport you to the past, that`s still the best teleporter we got.', 5, 10),
(11, 'fitness', 'The human body contain up to 60% of water, but that doesn\'t mean you don\'t have to drink it. In general, you should try to drink between half an ounce and an ounce of water for each pound you weigh, every day. You look like you need a drink! Time to get hydrated.', 5, 10),
(12, 'fitness', 'Mens sana in corpore sano! How about a 30 minute walk? It can increase cardiovascular fitness, strengthen bones, reduce excess body fat, and boost muscle power and endurance. I`m even going to do it myself! Talk to you in a bit.', 5, 10),
(13, 'fitness', 'Deep breathing is one of the best ways to lower stress in the body. This is because when you breathe deeply, it sends a message to your brain to calm down and relax. Have you ever tried meditation? Well, the time has finally come! It doesn`t have to last long, set your alarm clock for a 5 minutes of deep breaths and relaxing.', 5, 10),
(14, 'fitness', 'Oh, I just realized I have never heard you singing. Did you know that singing burn calories?  You are using your abdominal muscles, lungs, and stamina. In general, singing while standing can burn 136 calories per hour. Let me hear that beautiful voice of yours!', 5, 10),
(15, 'fitness', 'An apple a day may keep the doctor away! Did you know that an adult should eat between two to five fruits per day? You know what to do, right? Time for some healthy sugar!', 5, 10),
(16, 'fitness', 'When was your last meal? Eating every 2-3 hours maintains body processes and metabolism remains intact. So, is it time for a snack? If you had already eaten, it`s always time for a glass of water!', 5, 10),
(17, 'fitness', 'Skipping breakfast has been linked to elevated blood pressure, higher levels of total and low-density lipoprotein cholesterol – known as “bad” cholesterol – and changes in appetite that cause people to overeat later in the day. Let`s not do that, ok?', 5, 10),
(18, 'fitness', 'It`s best to drink fluids before and two hours after meals as this helps in absorption of nutrients. Here is the mission: drinking only two hours after meals for at least a week. Sipping a little water is still allowed, not an entire glass. Thank me later.', 5, 10),
(19, 'fitness', 'Did you know that sleep can boost your immune system and improve memory? A good night of sleep is the key for the rest of the day`s activities. Let`s take this very seriously. No phones or distractions when it`s time to sleep. First things first.', 5, 10),
(20, 'fitness', 'Stretching activates your parasympathetic nervous system and increases blood flow to your muscles. It`s thought that stretching may also release endorphins that help to reduce pain and enhance your mood. Morning stretches, that`s the mission!', 5, 10),
(21, 'academic', 'Dictionaries can be really helpful when you are reading because they help you find out the meaning of words you don\'t know. Here is the mission: open a dictionary and look for 5 new words for your vocabulary improvement! Try looking for words you may actually use. \r\n', 5, 10),
(22, 'academic', 'Lots of problems can arise from misunderstandings, especially because we live in a multicultural world. By learning and understanding different cultures you understand why people do things the way they do. Here is the mission: do some research about different aspects of 3 different cultures. It can\'t be the culture you were born in. ', 5, 10),
(23, 'academic', 'There are 195 Capital Cities of the World. How many of them do you know? Oh, there\'s even one country with no capital, Nauru, an island in the Pacific Ocean, the second-smallest republic in the world. Next time we talk you must know at least 30 of the 195 capital cities.\r\n', 5, 10),
(24, 'academic', 'Let\'s go for some simple science experiment? Tornado in a Bottle! You\'re gonna need two bottles, a tube to connect the bottles, and some water. When you whirl the liquid in the top bottle, it creates a vortex as it drains into the bottom bottle. That\'s because as the water flows down, air must flow up, creating a spiraling tornado.\r\n', 5, 10),
(25, 'academic', 'Do you know what the Socratic method is? Not only your mission is to know what the method is about, but also use it in your next discussion! “There is only one good, knowledge, and one evil, ignorance.” \r\n', 5, 10),
(26, 'academic', 'Multiple studies suggest that bilingual education has cognitive, social, and health benefits. Don\'t you think it\'s time to begin a new language study? Let\'s go easy, choose 10 words from your natural born language then translate it to the next language you would like to learn, 幸運を!', 5, 10),
(27, 'academic', '27', 5, 10),
(28, 'academic', '28', 5, 10),
(29, 'academic', '29', 5, 10),
(30, 'academic', '30', 5, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `item`
--

CREATE TABLE `item` (
  `item_id` int(30) NOT NULL,
  `item_name` varchar(25) NOT NULL,
  `item_info` varchar(500) NOT NULL,
  `price` int(100) NOT NULL,
  `power` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `item`
--

INSERT INTO `item` (`item_id`, `item_name`, `item_info`, `price`, `power`) VALUES
(1, ' Ocapse Blood Rubi', 'This is straight made from one wound that my dragon, Ocapse, got from one of our adventures. This amulet really has a powerful energy within! Each challenge will give you more 5 points of wisdom.', 30, 2),
(2, 'Russia Diamond', 'A trully space traveller. It will take you to the coldest and whitest landscape on Earth, Russia. Ahh and it gives you 1 more wisdom point.', 50, 1),
(3, 'Plutoniam Love Pearl', 'Your soul is going to feel it! Love is the force for transformation. If you follow the love you will find failure, which is the principle of evolution.', 10, 1),
(4, 'Ocean Cookie', 'Ahhhh I created this one for those moments that I miss the wind breeze and the salty atmosphere that the ocean leaves by the coast. The waves, the waves, the waves and their hipnotic sound. The ocean is trully close with this piece of mineral :)\r\n\r\n', 10, 1);

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
  `wallet` int(100) NOT NULL DEFAULT 0,
  `wisdom` int(100) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`username`, `password`, `full_name`, `Class`, `Quest`, `wallet`, `wisdom`) VALUES
('aca', '123', 'aca', 'healer', 'mind', 0, 0),
('fit', '123', 'aca', 'healer', 'fitness', 0, 0),
('mind', '123', 'aca', 'healer', 'mind', 0, 0);

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
-- Índices para tabela `backpack_item`
--
ALTER TABLE `backpack_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `backpack_item-user` (`usr`),
  ADD KEY `backpack_item-item` (`item_id`);

--
-- Índices para tabela `challenge`
--
ALTER TABLE `challenge`
  ADD PRIMARY KEY (`chall_id`);

--
-- Índices para tabela `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`);

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
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `backpack_item`
--
ALTER TABLE `backpack_item`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `challenge`
--
ALTER TABLE `challenge`
  MODIFY `chall_id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de tabela `item`
--
ALTER TABLE `item`
  MODIFY `item_id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `backpack_chall`
--
ALTER TABLE `backpack_chall`
  ADD CONSTRAINT `backpack_chall-chall` FOREIGN KEY (`chall_id`) REFERENCES `challenge` (`chall_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `backpack_chall-user` FOREIGN KEY (`usr`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `backpack_item`
--
ALTER TABLE `backpack_item`
  ADD CONSTRAINT `backpack_item-item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `backpack_item-user` FOREIGN KEY (`usr`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
