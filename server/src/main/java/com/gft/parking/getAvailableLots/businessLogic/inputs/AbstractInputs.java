package com.gft.parking.getAvailableLots.businessLogic.inputs;

import lombok.*;
import java.util.*;

@Data
public abstract class AbstractInputs {
    public abstract List<Lot> listAvailableLots();
}
