package com.gft.parking.getCurrentDate.dataAccess;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table( name = "reservations" )
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;
}