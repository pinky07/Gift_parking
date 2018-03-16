package com.gft.parking.getAvailableLots.businessLogic;

import com.gft.parking.getAvailableLots.businessLogic.inputs.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class ResponseEntityCreation {
    public static ResponseEntity<Object> getResponse(AbstractInputs inputs){
        List<Lot> lots = inputs.listAvailableLots();

        return new ResponseEntity<>(lots, HttpStatus.OK);
    }
}