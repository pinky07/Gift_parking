package com.gft.parking.listFutureReservations.businessLogic.inputs;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Reservation {
    private int id;
    private String plate;
    private String lotName;
    private LocalDate reservationDate;

    public Reservation(int id, String lotName, LocalDate reservationDate){
        this.id = id;
        this.lotName = lotName;
        this.reservationDate = reservationDate;
    }
}