package com.gft.parking.getAvailableLots.dataAccess;

import com.gft.parking.getAvailableLots.businessLogic.inputs.AbstractInputs;
import com.gft.parking.getAvailableLots.businessLogic.inputs.Lot;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.stream.Collectors;

public class PersistentInputs extends AbstractInputs {
    private EntityManager entityManager;

    public PersistentInputs(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Lot> listAvailableLots() {
        List<Object[]> storedProcedureResults = null;

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("listAvailableLots");
        // Call the stored procedure.
        storedProcedureResults = storedProcedure.getResultList();

        // Use Java 8's cool new functional programming paradigm to map the objects from the stored procedure results
        return storedProcedureResults.stream().map(result -> new Lot(
                (Integer) result[0],
                (String) result[1]
        )).collect(Collectors.toList());
    }
}