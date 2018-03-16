DELIMITER $$
CREATE PROCEDURE `getCurrentDate`(OUT currentDate datetime)
BEGIN
    declare parameterDate datetime;

	SELECT value into parameterDate FROM dateParameters
    WHERE name = 'SystemDateTime';

    IF parameterDate IS null THEN SET
		currentDate = NOW();
    ELSE
		SET currentDate = parameterDate;
    END IF;

END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE `listAvailableLots`()
BEGIN
	CALL getCurrentDate(@Time);

    SELECT id, name
    FROM lots
    WHERE availableFrom <= @Time AND availableUntil >= @Time;

END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `listFutureReservations`(plate varchar(10))
BEGIN
	CALL getCurrentDate(@Time);

    SELECT reservations.id, reservations.plate, lots.id, reservations.reservationDate
    FROM reservations INNER JOIN lots ON reservations.lotId = lots.id
    WHERE reservationDate >= @Time AND plate = plate;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addReservation`(lotId int, plate varchar(45), reservationDate DATE)
BEGIN

	CALL getCurrentDate(@Time);
	INSERT INTO `gftparking`.`reservations` (`plate`, `lotId`, `reservationDate`, `eventDate`) VALUES (plate, lotId, reservationDate, @Time);

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cancelReservation`(id INT)
BEGIN
	CALL getCurrentDate(@Time);
	INSERT INTO `gftparking`.`cancelations` (`reservationId`, `eventDate`) VALUES (id, @Time);
END$$
DELIMITER ;

