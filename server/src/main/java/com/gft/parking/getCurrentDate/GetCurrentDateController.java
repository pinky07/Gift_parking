package com.gft.parking.getCurrentDate;

import com.gft.parking.getCurrentDate.businessLogic.ResponseEntityCreation;
import com.gft.parking.getCurrentDate.businessLogic.inputs.AbstractInputs;
import com.gft.parking.getCurrentDate.dataAccess.GetCurrentDateRepository;
import com.gft.parking.getCurrentDate.dataAccess.PersistentInputs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/date")
public class GetCurrentDateController {
    private final GetCurrentDateRepository repository;

    public GetCurrentDateController(GetCurrentDateRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/current")
    public ResponseEntity<Object> getCurrentDate() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("getCurrentDate started");

        AbstractInputs inputs = new PersistentInputs(repository);
        ResponseEntity<Object> response = ResponseEntityCreation.getResponse(inputs);

        logger.info("getCurrentDate returned {}", response);

        return response;
    }
}