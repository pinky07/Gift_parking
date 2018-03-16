package com.gft.parking.listFutureReservations.dataAccess;

import com.gft.parking.listFutureReservations.businessLogic.inputs.AbstractInputs;
import com.gft.parking.listFutureReservations.businessLogic.inputs.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PersistentInputs extends AbstractInputs {
    private EntityManager entityManager;

    public PersistentInputs(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Reservation> listFutureReservations(String plate) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("listFutureReservations");
        // Set the parameters of the stored procedure.
        String plateParameter = "plate";
        storedProcedure.registerStoredProcedureParameter(plateParameter, String.class, ParameterMode.IN);
        storedProcedure.setParameter(plateParameter, plate);

        // Call the stored procedure.
        List<Object[]> storedProcedureResults = storedProcedure.getResultList();

        // Use Java 8's cool new functional programming paradigm to map the objects from the stored procedure results
        return storedProcedureResults.stream().map(result -> new Reservation(
                (Integer) result[0],
                (String) result[1],
                (LocalDate) result[2]
        )).collect(Collectors.toList());
    }
}