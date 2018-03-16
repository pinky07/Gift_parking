package com.gft.parking.listFutureReservations.businessLogic.inputs;

import lombok.*;
import java.util.*;

@Data
public abstract class AbstractInputs {
    public abstract List<Reservation> listFutureReservations(String plate);
}