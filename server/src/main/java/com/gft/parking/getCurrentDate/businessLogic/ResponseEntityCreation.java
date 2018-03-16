package com.gft.parking.getCurrentDate.businessLogic;

import com.gft.parking.getCurrentDate.businessLogic.inputs.AbstractInputs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

public class ResponseEntityCreation {
    public static ResponseEntity<Object> getResponse(AbstractInputs inputs){
        LocalDateTime currentDate = inputs.getCurrentDate();

        return new ResponseEntity<>(currentDate, HttpStatus.OK);
    }
}