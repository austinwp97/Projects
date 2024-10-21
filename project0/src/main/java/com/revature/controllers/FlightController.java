package com.revature.controllers;

import com.revature.DAOs.FlightDAO;
import com.revature.models.Flight;
import io.javalin.http.Handler;
import io.javalin.validation.BodyValidator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class FlightController {

    FlightDAO fDAO = new FlightDAO();


    //This Handler will handle GET request to /flights/id
    public Handler getRoleByFlightNumberHandler = ctx -> {

        int flight_number = Integer.parseInt(ctx.pathParam("flightNumber"));

        Flight f = fDAO.getFlightByNumber(flight_number);


        if(flight_number < 0){
            ctx.result("Flight number must be greater than zero.");
            ctx.status(400);
        }
        else if(f == null){
            ctx.result("Flight " + flight_number + " not found.");
            ctx.status(404);
        }
         else {

            ctx.result(f.toString());
            ctx.status(200);
        }
    };

    public Handler getFlights = ctx -> {

        ArrayList<Flight> flights = fDAO.getAllFlights();

        ctx.json(flights.toString());

        ctx.status(200);
    };

    public Handler insertFlightHandler = ctx -> {


        Flight newFlight = ctx.bodyAsClass(Flight.class);



        if(newFlight.getOrigin() == null || newFlight.getOrigin().isBlank()){
            ctx.result("Origin city is required!");
            ctx.status(400);
        }
        else if(newFlight.getDestination() == null || newFlight.getDestination().isBlank()){
            ctx.result("Destination city is required!");
            ctx.status(400);
        }
        else if(newFlight.getArrival_time().equals(null)){
            ctx.result("Arrival time is required!");
            ctx.status(400);
        }
        else if(newFlight.getDepartureTime().equals(null)){
            ctx.result("Departure time is required!");
            ctx.status(400);
        }
        else{
            Flight insertedFlight = fDAO.insertFlight(newFlight);
            ctx.status(201);
            ctx.result(insertedFlight.toString());
        }

    };

    public Handler updateDepartureTime = ctx -> {

        int flight_number = Integer.parseInt(ctx.pathParam("id"));
        double hoursDelayed = Double.parseDouble(ctx.pathParam("hours"));

        Flight f = fDAO.getFlightByNumber(flight_number);

        if(f == null){
            ctx.result("Flight not found.");
            ctx.status(404);
        }
        else{
            ctx.result(fDAO.updateDelayed(flight_number,hoursDelayed).toString());
            ctx.status(200);
        }

    };



}
