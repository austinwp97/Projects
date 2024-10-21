package com.revature.DAOs;

import com.revature.models.Flight;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface FlightDAOInterface {
    Flight getFlightByNumber(int flight_number);
    ArrayList<Flight> getAllFlights();
    ArrayList<Timestamp> updateDelayed(int flight_number, double hours);





}
