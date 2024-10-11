package com.revature.DAOs;

import com.revature.models.Flight;

import java.sql.Timestamp;

public class FlightDAO implements FlightDAOInterface{
    @Override
    public Flight getFlightByNumber(int flight_number) {
        return null;
    }

    @Override
    public Timestamp updateDepartureTime(int flight_number, Timestamp departure_time) {
        return null;
    }

    @Override
    public Timestamp updateArrivalTime(int flight_number, Timestamp departure_time) {
        return null;
    }

    @Override
    public boolean updateDelayed(int flight_number, boolean delayed) {
        return false;
    }
}
