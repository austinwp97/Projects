package com.revature.DAOs;

import com.revature.models.Flight;

import java.sql.Timestamp;

public interface FlightDAOInterface {
    Flight getFlightByNumber(int flight_number);

    Timestamp updateDepartureTime(int flight_number, Timestamp departure_time);

    Timestamp updateArrivalTime(int flight_number, Timestamp departure_time);

    boolean updateDelayed(int flight_number,boolean delayed);
}
