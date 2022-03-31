-- Adminer 4.8.1 MySQL 5.5.5-10.5.12-MariaDB-0+deb11u1 dump

SET NAMES utf8;
SET
time_zone = '+00:00';
SET
foreign_key_checks = 0;
SET
sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `ishiraku_categorie`;
CREATE TABLE `ishiraku_categorie`
(
    `id`        int(11) NOT NULL,
    `categorie` varchar(20) COLLATE latin1_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

TRUNCATE `ishiraku_categorie`;
INSERT INTO `ishiraku_categorie` (`id`, `categorie`)
VALUES (1, 'moto'),
       (2, 'voiture'),
       (3, 'handicape'),
       (4, 'familiale');

DROP TABLE IF EXISTS `ishiraku_client`;
CREATE TABLE `ishiraku_client`
(
    `id`               int(11) NOT NULL AUTO_INCREMENT,
    `nom`              varchar(20) COLLATE latin1_general_ci NOT NULL,
    `prenom`           varchar(20) COLLATE latin1_general_ci NOT NULL,
    `numero_telephone` varchar(20) COLLATE latin1_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

TRUNCATE `ishiraku_client`;
INSERT INTO `ishiraku_client` (`id`, `nom`, `prenom`, `numero_telephone`)
VALUES (0, 'Pas de client ', '', ''),
       (1, 'Naji', 'Djibril', '0770413785'),
       (2, 'Djavoudine', 'Hameed', '0606060606'),
       (3, 'Bougui', 'Ryan', '0606060608');

DROP TABLE IF EXISTS `ishiraku_employes`;
CREATE TABLE `ishiraku_employes`
(
    `id`     int(11) NOT NULL AUTO_INCREMENT,
    `role`   varchar(50) COLLATE latin1_general_ci   NOT NULL,
    `nom`    varchar(50) COLLATE latin1_general_ci   NOT NULL,
    `prenom` varchar(50) COLLATE latin1_general_ci   NOT NULL,
    `login`  varchar(50) COLLATE latin1_general_ci   NOT NULL,
    `mdp`    varchar(1000) COLLATE latin1_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

TRUNCATE `ishiraku_employes`;
INSERT INTO `ishiraku_employes` (`id`, `role`, `nom`, `prenom`, `login`, `mdp`)
VALUES (1, 'laveur', 'Erambert ', 'Quentin', 'test', 'test'),
       (2, 'laveur', 'Gervais ', 'Antoine', 'laveurVoiture2', '*14EEE6654A481484E6995AD4E4D0D3C04EECCB38'),
       (3, 'laveur', 'Marie-Louise', 'Josue', 'laveurVoiture3', '*655D80521CDF77CFDE44BE3220B215CA18B744FC'),
       (4, 'receptionniste', 'Dauverne', 'Romain', 'reception', '*9CB94703FE630325E140DA92008B4813F829B408'),
       (5, 'receptionniste', 'test', 'test', 'test', 'test'),
       (6, 'receptionniste', 'test', 'test', 'test', '*94BDCEBE19083CE2A1F959FD02F964C7AF4CFC29');

DROP TABLE IF EXISTS `ishiraku_lavage`;
CREATE TABLE `ishiraku_lavage`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `laveur`      int(20) NOT NULL,
    `date`        date                                   NOT NULL,
    `heure`       varchar(10) COLLATE latin1_general_ci  NOT NULL,
    `voiture`     varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
    `commentaire` varchar(200) COLLATE latin1_general_ci NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `voiture` (`voiture`),
    KEY           `laveurvehicule` (`laveur`),
    CONSTRAINT `ishiraku_lavage_ibfk_2` FOREIGN KEY (`voiture`) REFERENCES `ishiraku_voiture` (`plaque`),
    CONSTRAINT `ishiraku_lavage_ibfk_3` FOREIGN KEY (`laveur`) REFERENCES `ishiraku_employes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

TRUNCATE `ishiraku_lavage`;
INSERT INTO `ishiraku_lavage` (`id`, `laveur`, `date`, `heure`, `voiture`, `commentaire`)
VALUES (1, 1, '2021-12-02', '15h', '10-152-12', 'Jaimerai que la voiture soit lavée pour 17h maximum svp');

DROP TABLE IF EXISTS `ishiraku_place`;
CREATE TABLE `ishiraku_place`
(
    `id`          int(11) NOT NULL,
    `etage`       int(11) NOT NULL,
    `numParking`  int(11) NOT NULL,
    `categorie`   int(11) NOT NULL,
    `typevoiture` int(11) NOT NULL,
    `id_client`   int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `ishiraku_reservation` (`id_client`),
    KEY           `ishiraku_categorie` (`categorie`),
    KEY           `ishiraku_typevoiture` (`typevoiture`),
    CONSTRAINT `place_ibfk_2` FOREIGN KEY (`categorie`) REFERENCES `ishiraku_categorie` (`id`),
    CONSTRAINT `place_ibfk_3` FOREIGN KEY (`typevoiture`) REFERENCES `ishiraku_typevoiture` (`id_type`),
    CONSTRAINT `place_ibfk_4` FOREIGN KEY (`id_client`) REFERENCES `ishiraku_client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

TRUNCATE `ishiraku_place`;
INSERT INTO `ishiraku_place` (`id`, `etage`, `numParking`, `categorie`, `typevoiture`, `id_client`)
VALUES (1, 1, 1, 1, 1, 1),
       (2, 1, 2, 1, 1, 0),
       (3, 1, 3, 1, 2, 0),
       (4, 1, 4, 2, 1, 0),
       (5, 1, 5, 2, 1, 0),
       (6, 1, 6, 2, 1, 0),
       (7, 1, 7, 2, 1, 0),
       (8, 1, 8, 2, 1, 0),
       (9, 1, 9, 2, 1, 0),
       (10, 1, 10, 2, 2, 0),
       (11, 1, 11, 2, 2, 0),
       (12, 1, 12, 2, 2, 0),
       (13, 1, 13, 2, 2, 0),
       (14, 1, 14, 3, 1, 0),
       (15, 1, 15, 3, 1, 0),
       (16, 1, 16, 3, 2, 0),
       (17, 1, 17, 4, 1, 0),
       (18, 1, 18, 4, 1, 0),
       (19, 1, 19, 4, 1, 0),
       (20, 1, 20, 4, 2, 0),
       (21, 2, 1, 1, 1, 0),
       (22, 2, 2, 1, 1, 0),
       (23, 2, 3, 1, 2, 0),
       (24, 2, 4, 2, 1, 0),
       (25, 2, 5, 2, 1, 0),
       (26, 2, 6, 2, 1, 0),
       (27, 2, 7, 2, 1, 0),
       (28, 2, 8, 2, 1, 0),
       (29, 2, 9, 2, 1, 0),
       (30, 2, 10, 2, 2, 0),
       (31, 2, 11, 2, 2, 0),
       (32, 2, 12, 2, 2, 0),
       (33, 2, 13, 2, 2, 0),
       (34, 2, 14, 3, 1, 0),
       (35, 2, 15, 3, 1, 0),
       (36, 2, 16, 3, 2, 0),
       (37, 2, 17, 4, 1, 0),
       (38, 2, 18, 4, 1, 0),
       (39, 2, 19, 4, 1, 0),
       (40, 2, 20, 4, 2, 0);

DROP TABLE IF EXISTS `ishiraku_reservation`;
CREATE TABLE `ishiraku_reservation`
(
    `client`     int(11) NOT NULL,
    `dateEntree` date NOT NULL,
    `dateSortie` date NOT NULL,
    `place`      int(11) NOT NULL,
    KEY          `client` (`client`),
    KEY          `place` (`place`),
    CONSTRAINT `ishiraku_reservation_ibfk_2` FOREIGN KEY (`place`) REFERENCES `ishiraku_place` (`id`),
    CONSTRAINT `ishiraku_reservation_ibfk_3` FOREIGN KEY (`client`) REFERENCES `ishiraku_client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

TRUNCATE `ishiraku_reservation`;
INSERT INTO `ishiraku_reservation` (`client`, `dateEntree`, `dateSortie`, `place`)
VALUES (3, '2002-05-12', '2022-03-09', 5);

DROP TABLE IF EXISTS `ishiraku_typevoiture`;
CREATE TABLE `ishiraku_typevoiture`
(
    `id_type`     int(11) NOT NULL,
    `typevoiture` varchar(20) COLLATE latin1_general_ci NOT NULL,
    PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

TRUNCATE `ishiraku_typevoiture`;
INSERT INTO `ishiraku_typevoiture` (`id_type`, `typevoiture`)
VALUES (1, 'carburant'),
       (2, 'electrique');

DROP TABLE IF EXISTS `ishiraku_voiture`;
CREATE TABLE `ishiraku_voiture`
(
    `plaque`      varchar(20) COLLATE latin1_general_ci NOT NULL,
    `client`      int(11) NOT NULL,
    `typevoiture` int(11) NOT NULL,
    `employes`    int(11) DEFAULT NULL,
    PRIMARY KEY (`plaque`),
    KEY           `client` (`client`),
    KEY           `typevoiture` (`typevoiture`),
    KEY           `employes` (`employes`),
    CONSTRAINT `ishiraku_voiture_ibfk_3` FOREIGN KEY (`typevoiture`) REFERENCES `ishiraku_typevoiture` (`id_type`),
    CONSTRAINT `ishiraku_voiture_ibfk_4` FOREIGN KEY (`employes`) REFERENCES `ishiraku_employes` (`id`),
    CONSTRAINT `ishiraku_voiture_ibfk_5` FOREIGN KEY (`client`) REFERENCES `ishiraku_client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

TRUNCATE `ishiraku_voiture`;
INSERT INTO `ishiraku_voiture` (`plaque`, `client`, `typevoiture`, `employes`)
VALUES ('10-152-12', 1, 1, 1),
       ('10-255-14', 2, 2, NULL),
       ('12-542-96', 3, 1, NULL);

-- 2022-03-17 21:42:51
