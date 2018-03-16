package com.gft.parking.getCurrentDate.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.*;

@Repository
@Transactional
public interface GetCurrentDateRepository extends JpaRepository<Reservation, Integer> {

    @Procedure(procedureName = "getCurrentDate", outputParameterName = "currentDate")
    LocalDateTime getCurrentDate();

}
