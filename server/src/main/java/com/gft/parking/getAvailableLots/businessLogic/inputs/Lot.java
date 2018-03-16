package com.gft.parking.getAvailableLots.businessLogic.inputs;

import lombok.Data;

@Data
public class Lot {
    private Integer id;
    private String name;

    public Lot(Integer id, String name) {
        this.id=id;
        this.name= name;
    }
}