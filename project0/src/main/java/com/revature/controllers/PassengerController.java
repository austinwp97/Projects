package com.revature.controllers;

import com.revature.DAOs.PassengerDAO;
import com.revature.models.Passenger;
import io.javalin.http.Handler;

import java.util.ArrayList;

/* The Controller Layer is where HTTP requests get sent after Javalin directs them from main()
*  It's in this layer that JSON comes in and gets translated to Java and vice versa
*  We'll be taking in HTTP Requests from the client, and sending back HTTP responses */
public class PassengerController {


    PassengerDAO pDAO = new PassengerDAO();

    public Handler getPassengerHandler = ctx -> {

        ArrayList<Passenger> passengers = pDAO.getAllPassengers();

        ctx.json(passengers);

        ctx.status(200);

    };

    public Handler insertPassengerHandler = ctx -> {

        Passenger newPassenger = ctx.bodyAsClass(Passenger.class);
        if(newPassenger.getFirst_name() == null || newPassenger.getFirst_name().isBlank()){
            ctx.result("First name is required!");
            ctx.status(400);
        }
        else if(newPassenger.getLast_name() == null || newPassenger.getLast_name().isBlank()){
        ctx.result("Last name is required!");
        ctx.status(400);
        }
        else if(newPassenger.getSeatClass() == null || newPassenger.getSeatClass().isBlank()){
            ctx.result("Seat class is required!");
            ctx.status(400);
        }
        else {
            Passenger insertedPassenger = pDAO.insertPassenger(newPassenger);
            ctx.status(201);
            ctx.json(insertedPassenger);
        }
    };

    public Handler deletePassenger = ctx -> {

        int passenger_id = Integer.parseInt(ctx.pathParam("passengerId"));

        Passenger deletedPassenger = pDAO.deletePassenger(passenger_id);
        if(deletedPassenger == null){
            ctx.result("Passenger not found.");
            ctx.status(404);
        }
        else if(passenger_id < 0){
            ctx.result("Passenger ID must be greater than zero");
            ctx.status(400);
        }
        else if(passenger_id == 0){
            ctx.result("Passenger " + passenger_id + " not found.");
        }
        else{
            ctx.json(deletedPassenger.toString());
            ctx.status(200);
        }


    };

    public Handler getPassengersByFlightID = ctx -> {

        int flight_id_fk = Integer.parseInt(ctx.pathParam("flightIdFk"));

        ArrayList<Passenger> passengersByFlightId = pDAO.getPassengerByFK(flight_id_fk);

        if(passengersByFlightId.isEmpty()){
            ctx.result("No passengers found by that flight ID");
            ctx.status(404);
        }
        else{
            ctx.json(passengersByFlightId);
            ctx.status(200);
        }

    };
}
