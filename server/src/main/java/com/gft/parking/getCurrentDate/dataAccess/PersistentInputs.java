package com.gft.parking.getCurrentDate.dataAccess;

import com.gft.parking.getCurrentDate.businessLogic.inputs.AbstractInputs;

import java.time.LocalDateTime;

public class PersistentInputs extends AbstractInputs {
    private final GetCurrentDateRepository repository;

    public PersistentInputs(GetCurrentDateRepository repository) {
        this.repository = repository;
    }

    @Override
    public LocalDateTime getCurrentDate() {
        return repository.getCurrentDate();
    }
}