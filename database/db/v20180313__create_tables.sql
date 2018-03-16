CREATE TABLE `dateparameters` (
  `name` varchar(45) NOT NULL,
  `value` datetime DEFAULT NULL,
  `description` varchar(256) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lots` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `availableFrom` datetime NOT NULL,
  `availableUntil` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lot_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plate` varchar(45) NOT NULL,
  `lotId` int(11) NOT NULL,
  `reservationDate` date NOT NULL,
  `eventDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservations_id_lots` (`lotId`),
  CONSTRAINT `fk_reservations_id_lots` FOREIGN KEY (`lotId`) REFERENCES `lots` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `cancelations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservationId` int(11) NOT NULL,
  `eventDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cancelations_id_reservations_idx` (`reservationId`),
  CONSTRAINT `fk_cancelations_id_reservations` FOREIGN KEY (`reservationId`) REFERENCES `reservations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


