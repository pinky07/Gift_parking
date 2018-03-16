package com.gft.parking.getAvailableLots;

import com.gft.parking.getAvailableLots.businessLogic.ResponseEntityCreation;
import com.gft.parking.getAvailableLots.businessLogic.inputs.AbstractInputs;
import com.gft.parking.getAvailableLots.dataAccess.PersistentInputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping(value = "/api/v1/lots")
public class GetAvailableLotsController {
    @PersistenceContext
    private EntityManager entityManager;

    public GetAvailableLotsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/available")
    public ResponseEntity<Object> getAvailableLots() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("getAvailableLots started");

        AbstractInputs inputs = new PersistentInputs(entityManager);
        ResponseEntity<Object> response = ResponseEntityCreation.getResponse(inputs);

        logger.info("getAvailableLots returned {}", response);

        return response;
    }
}