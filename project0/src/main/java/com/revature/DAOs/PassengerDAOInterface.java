package com.revature.DAOs;

import com.revature.models.Passenger;

import java.util.ArrayList;

public interface PassengerDAOInterface {

    Passenger insertPassenger(Passenger p);

    ArrayList<Passenger> getAllPassengers();


}
