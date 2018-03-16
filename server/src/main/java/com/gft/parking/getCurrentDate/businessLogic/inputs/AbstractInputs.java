package com.gft.parking.getCurrentDate.businessLogic.inputs;

import lombok.*;
import java.time.*;
import java.util.*;

@Data
public abstract class AbstractInputs {
    public abstract LocalDateTime getCurrentDate();
}
