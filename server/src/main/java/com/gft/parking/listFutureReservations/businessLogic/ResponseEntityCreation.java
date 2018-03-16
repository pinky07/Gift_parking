package com.gft.parking.listFutureReservations.businessLogic;

import com.gft.parking.listFutureReservations.businessLogic.inputs.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class ResponseEntityCreation {
    public static ResponseEntity<Object> getResponse(AbstractInputs inputs, String plate){
        List<Reservation> lots = inputs.listFutureReservations(plate);

        return new ResponseEntity<>(lots, HttpStatus.OK);
    }
}