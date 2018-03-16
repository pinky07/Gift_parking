package com.gft.parking.listFutureReservations;

import com.gft.parking.listFutureReservations.businessLogic.ResponseEntityCreation;
import com.gft.parking.listFutureReservations.businessLogic.inputs.AbstractInputs;
import com.gft.parking.listFutureReservations.dataAccess.PersistentInputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping(value = "/api/v1/reservations")
public class ListFutureReservationsController {
    @PersistenceContext
    private EntityManager entityManager;

    public ListFutureReservationsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/future")
    public ResponseEntity<Object> listFutureReservations(String plate) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("listFutureReservations started");

        AbstractInputs inputs = new PersistentInputs(entityManager);
        ResponseEntity<Object> response = ResponseEntityCreation.getResponse(inputs, plate);

        logger.info("listFutureReservations returned {}", response);

        return response;
    }
}