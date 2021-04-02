-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 03 avr. 2021 à 00:58
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestion_stock_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` bigint(20) NOT NULL,
  `libelle_categorie` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `libelle_categorie`) VALUES
(3, 'Electricité'),
(6, 'Outillage'),
(9, 'Travaux extérieur'),
(10, 'Droguerie');

-- --------------------------------------------------------

--
-- Structure de la table `databasechangelog`
--

CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `databasechangelog`
--

INSERT INTO `databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
('00000000000001', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', '2021-02-02 22:51:01', 1, 'EXECUTED', '8:d156c87d42028d6a91caf0f39e7f6563', 'createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127150433-1', 'jhipster', 'config/liquibase/changelog/20210127150433_added_entity_Categorie.xml', '2021-02-02 22:51:01', 2, 'EXECUTED', '8:45ef85383b194149e00c1ed49f014e20', 'createTable tableName=categorie', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127150433-1-relations', 'jhipster', 'config/liquibase/changelog/20210127150433_added_entity_Categorie.xml', '2021-02-02 22:51:01', 3, 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127150433-1-data', 'jhipster', 'config/liquibase/changelog/20210127150433_added_entity_Categorie.xml', '2021-02-02 22:51:02', 4, 'EXECUTED', '8:c4ab0616840b2627665b1f48186061a6', 'loadData tableName=categorie', '', NULL, '3.9.0', 'faker', NULL, '2306248236'),
('20210127150930-1', 'jhipster', 'config/liquibase/changelog/20210127150930_added_entity_Produit.xml', '2021-02-02 22:51:02', 5, 'EXECUTED', '8:c3d858ada28292bacbb192a33ad720ac', 'createTable tableName=produit', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127150930-1-relations', 'jhipster', 'config/liquibase/changelog/20210127150930_added_entity_Produit.xml', '2021-02-02 22:51:02', 6, 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127150930-1-data', 'jhipster', 'config/liquibase/changelog/20210127150930_added_entity_Produit.xml', '2021-02-02 22:51:03', 7, 'EXECUTED', '8:a1754d936da8ef61204ecd00955871be', 'loadData tableName=produit', '', NULL, '3.9.0', 'faker', NULL, '2306248236'),
('20210127151550-1', 'jhipster', 'config/liquibase/changelog/20210127151550_added_entity_Stock.xml', '2021-02-02 22:51:04', 8, 'EXECUTED', '8:eb001efb1a33d03386f8c2d4aabb17dc', 'createTable tableName=stock; dropDefaultValue columnName=date, tableName=stock', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127151550-1-relations', 'jhipster', 'config/liquibase/changelog/20210127151550_added_entity_Stock.xml', '2021-02-02 22:51:05', 9, 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127151550-1-data', 'jhipster', 'config/liquibase/changelog/20210127151550_added_entity_Stock.xml', '2021-02-02 22:51:05', 10, 'EXECUTED', '8:7cfce417d8134e10d3207b00585fd93f', 'loadData tableName=stock', '', NULL, '3.9.0', 'faker', NULL, '2306248236'),
('20210127152128-1', 'jhipster', 'config/liquibase/changelog/20210127152128_added_entity_Facture.xml', '2021-02-02 22:51:07', 11, 'EXECUTED', '8:70e94ba1d539cab2f44134fc8a5d80eb', 'createTable tableName=facture; dropDefaultValue columnName=date, tableName=facture', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127152128-1-relations', 'jhipster', 'config/liquibase/changelog/20210127152128_added_entity_Facture.xml', '2021-02-02 22:51:07', 12, 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127152128-1-data', 'jhipster', 'config/liquibase/changelog/20210127152128_added_entity_Facture.xml', '2021-02-02 22:51:08', 13, 'EXECUTED', '8:5f12a9684a2290295f16911a201ae291', 'loadData tableName=facture', '', NULL, '3.9.0', 'faker', NULL, '2306248236'),
('20210127152505-1', 'jhipster', 'config/liquibase/changelog/20210127152505_added_entity_Sortie.xml', '2021-02-02 22:51:08', 14, 'EXECUTED', '8:72fbe0ef75ec9b1500feaed320f87bb5', 'createTable tableName=sortie; dropDefaultValue columnName=date, tableName=sortie', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127152505-1-relations', 'jhipster', 'config/liquibase/changelog/20210127152505_added_entity_Sortie.xml', '2021-02-02 22:51:08', 15, 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127152505-1-data', 'jhipster', 'config/liquibase/changelog/20210127152505_added_entity_Sortie.xml', '2021-02-02 22:51:09', 16, 'EXECUTED', '8:436e1b313fd990e09f2b82e86448c2d7', 'loadData tableName=sortie', '', NULL, '3.9.0', 'faker', NULL, '2306248236'),
('20210127150930-2', 'jhipster', 'config/liquibase/changelog/20210127150930_added_entity_constraints_Produit.xml', '2021-02-02 22:51:10', 17, 'EXECUTED', '8:5c7efeef528a4aa2f29a903c520f0775', 'addForeignKeyConstraint baseTableName=produit, constraintName=fk_produit_categorie_id, referencedTableName=categorie', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127151550-2', 'jhipster', 'config/liquibase/changelog/20210127151550_added_entity_constraints_Stock.xml', '2021-02-02 22:51:11', 18, 'EXECUTED', '8:6385fe2175eb411449549961a53adc86', 'addForeignKeyConstraint baseTableName=stock, constraintName=fk_stock_produit_id, referencedTableName=produit', '', NULL, '3.9.0', NULL, NULL, '2306248236'),
('20210127152505-2', 'jhipster', 'config/liquibase/changelog/20210127152505_added_entity_constraints_Sortie.xml', '2021-02-02 22:51:14', 19, 'EXECUTED', '8:737cb81a391380e3e092e59e19c2b258', 'addForeignKeyConstraint baseTableName=sortie, constraintName=fk_sortie_produit_id, referencedTableName=produit; addForeignKeyConstraint baseTableName=sortie, constraintName=fk_sortie_facture_id, referencedTableName=facture', '', NULL, '3.9.0', NULL, NULL, '2306248236');

-- --------------------------------------------------------

--
-- Structure de la table `databasechangeloglock`
--

CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `databasechangeloglock`
--

INSERT INTO `databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
(1, b'0', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id` bigint(20) NOT NULL,
  `date` datetime,
  `quantite` int(11) DEFAULT NULL,
  `prenom_client` varchar(255) DEFAULT NULL,
  `nom_client` varchar(255) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `facture`
--

INSERT INTO `facture` (`id`, `date`, `quantite`, `prenom_client`, `nom_client`, `etat`) VALUES
(1, '2021-01-27 02:10:38', 36989, 'robust proactive', 'SQL Health', 'ACTIF'),
(2, '2021-01-27 03:34:56', 84996, 'explicit cutting-edge Cotton', 'Buckinghamshire', 'INACTIF'),
(3, '2021-01-26 20:50:06', 75354, 'Automotive quantifying', 'Industrial Analyst', 'ACTIF'),
(4, '2021-01-26 20:32:56', 89784, 'Metal asymmetric Home Loan Account', 'Garden Drive Investment Account', 'ACTIF'),
(5, '2021-01-27 02:36:24', 51413, 'Checking Account Avon', 'hack hard drive', 'ACTIF'),
(6, '2021-01-26 21:23:42', 40489, 'Distributed disintermediate Ridge', 'sensor Tasty', 'INACTIF'),
(7, '2021-01-27 13:45:15', 80138, 'circuit iterate', 'payment Soft Swedish Krona', 'ACTIF'),
(8, '2021-01-27 15:14:03', 86421, 'Accountability', 'Ford', 'ACTIF'),
(9, '2021-01-27 10:45:02', 19552, 'Open-source', 'context-sensitive PNG program', 'ACTIF'),
(10, '2021-01-26 22:07:44', 92363, 'sexy feed invoice', 'Dominican Peso green Lead', 'ACTIF');

-- --------------------------------------------------------

--
-- Structure de la table `jhi_authority`
--

CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `jhi_authority`
--

INSERT INTO `jhi_authority` (`name`) VALUES
('ROLE_ADMIN'),
('ROLE_GESTIONNAIRE'),
('ROLE_USER'),
('ROLE_VENDEUR');

-- --------------------------------------------------------

--
-- Structure de la table `jhi_persistent_audit_event`
--

CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `jhi_persistent_audit_event`
--

INSERT INTO `jhi_persistent_audit_event` (`event_id`, `principal`, `event_date`, `event_type`) VALUES
(12, 'admin', '2021-03-17 11:22:57', 'AUTHENTICATION_SUCCESS'),
(13, 'admin', '2021-03-23 18:35:00', 'AUTHENTICATION_SUCCESS'),
(14, 'admin', '2021-03-28 11:35:10', 'AUTHENTICATION_SUCCESS'),
(15, 'admin', '2021-03-28 13:22:06', 'AUTHENTICATION_SUCCESS'),
(16, 'admin', '2021-03-28 13:35:39', 'AUTHENTICATION_SUCCESS'),
(17, 'admin', '2021-04-02 21:52:23', 'AUTHENTICATION_SUCCESS');

-- --------------------------------------------------------

--
-- Structure de la table `jhi_persistent_audit_evt_data`
--

CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `jhi_user`
--

CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(191) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(10) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `jhi_user`
--

INSERT INTO `jhi_user` (`id`, `login`, `password_hash`, `first_name`, `last_name`, `email`, `image_url`, `activated`, `lang_key`, `activation_key`, `reset_key`, `created_by`, `created_date`, `reset_date`, `last_modified_by`, `last_modified_date`) VALUES
(1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '', b'1', 'en', NULL, NULL, 'system', NULL, NULL, 'system', NULL),
(2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '', b'1', 'en', NULL, NULL, 'system', NULL, NULL, 'system', NULL),
(3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', b'1', 'en', NULL, NULL, 'system', NULL, NULL, 'system', NULL),
(4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '', b'1', 'en', NULL, NULL, 'system', NULL, NULL, 'system', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `jhi_user_authority`
--

CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `jhi_user_authority`
--

INSERT INTO `jhi_user_authority` (`user_id`, `authority_name`) VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(3, 'ROLE_ADMIN'),
(3, 'ROLE_USER'),
(4, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` bigint(20) NOT NULL,
  `libelle_produit` varchar(255) DEFAULT NULL,
  `categorie_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `libelle_produit`, `categorie_id`) VALUES
(1, 'Ralonge 15M', 3),
(2, 'Peinture de couleur bleue', 9),
(8, 'Balanced', 10);

-- --------------------------------------------------------

--
-- Structure de la table `sortie`
--

CREATE TABLE `sortie` (
  `id` bigint(20) NOT NULL,
  `date` datetime,
  `quantite` int(11) DEFAULT NULL,
  `produit_id` bigint(20) DEFAULT NULL,
  `facture_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sortie`
--

INSERT INTO `sortie` (`id`, `date`, `quantite`, `produit_id`, `facture_id`) VALUES
(1, '2021-01-27 01:15:00', 10697, 2, 9),
(2, '2021-01-27 06:18:04', 35019, NULL, NULL),
(3, '2021-01-26 16:46:29', 85821, NULL, NULL),
(4, '2021-01-27 05:14:44', 78509, NULL, NULL),
(5, '2021-01-26 16:53:17', 32691, NULL, NULL),
(6, '2021-01-27 05:45:02', 29587, NULL, NULL),
(7, '2021-01-27 11:51:51', 76718, NULL, NULL),
(8, '2021-01-26 20:32:50', 92913, NULL, NULL),
(9, '2021-01-27 00:40:55', 74736, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL,
  `quantite` int(11) DEFAULT NULL,
  `date` datetime,
  `produit_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `stock`
--

INSERT INTO `stock` (`id`, `quantite`, `date`, `produit_id`) VALUES
(1, 40873, '2021-02-10 15:47:59', 1),
(7, 98154, '2021-01-26 20:39:00', 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `databasechangeloglock`
--
ALTER TABLE `databasechangeloglock`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `jhi_authority`
--
ALTER TABLE `jhi_authority`
  ADD PRIMARY KEY (`name`);

--
-- Index pour la table `jhi_persistent_audit_event`
--
ALTER TABLE `jhi_persistent_audit_event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `idx_persistent_audit_event` (`principal`,`event_date`);

--
-- Index pour la table `jhi_persistent_audit_evt_data`
--
ALTER TABLE `jhi_persistent_audit_evt_data`
  ADD PRIMARY KEY (`event_id`,`name`),
  ADD KEY `idx_persistent_audit_evt_data` (`event_id`);

--
-- Index pour la table `jhi_user`
--
ALTER TABLE `jhi_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_user_login` (`login`),
  ADD UNIQUE KEY `ux_user_email` (`email`);

--
-- Index pour la table `jhi_user_authority`
--
ALTER TABLE `jhi_user_authority`
  ADD PRIMARY KEY (`user_id`,`authority_name`),
  ADD KEY `fk_authority_name` (`authority_name`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_produit_categorie_id` (`categorie_id`);

--
-- Index pour la table `sortie`
--
ALTER TABLE `sortie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_sortie_produit_id` (`produit_id`),
  ADD KEY `fk_sortie_facture_id` (`facture_id`);

--
-- Index pour la table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_stock_produit_id` (`produit_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `jhi_persistent_audit_event`
--
ALTER TABLE `jhi_persistent_audit_event`
  MODIFY `event_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `jhi_user`
--
ALTER TABLE `jhi_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `sortie`
--
ALTER TABLE `sortie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `jhi_persistent_audit_evt_data`
--
ALTER TABLE `jhi_persistent_audit_evt_data`
  ADD CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`);

--
-- Contraintes pour la table `jhi_user_authority`
--
ALTER TABLE `jhi_user_authority`
  ADD CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fk_produit_categorie_id` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `sortie`
--
ALTER TABLE `sortie`
  ADD CONSTRAINT `fk_sortie_facture_id` FOREIGN KEY (`facture_id`) REFERENCES `facture` (`id`),
  ADD CONSTRAINT `fk_sortie_produit_id` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `fk_stock_produit_id` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
