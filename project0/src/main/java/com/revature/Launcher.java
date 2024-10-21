package com.revature;

import com.revature.DAOs.PassengerDAO;
import com.revature.DAOs.PassengerDAOInterface;
import com.revature.controllers.AuthController;
import com.revature.controllers.FlightController;
import com.revature.controllers.PassengerController;
import com.revature.models.Passenger;
import io.javalin.Javalin;

import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) {

        
        //Typical Javalin setup Syntax
        var app = Javalin.create().start(7000);

        /* We need create() to begin the instantiation of our Javalin object
        We need start() to actually start our Javalin app on a port of our choosing
        You can choose any port, but make sure it's a port that isn't being used (like 5432)
        A port is like a parking space, a place for our app to sit where other app can find it
         */

        AuthController ac = new AuthController();
        PassengerController pc = new PassengerController();
        FlightController fc = new FlightController();

        app.before("/passengers/*",ctx ->{

            if(AuthController.ses == null){
                System.out.println("Session is null!");
                throw new IllegalArgumentException("You must log in before doing this!");
            }


        });
        app.before("/flights/*",ctx ->{

            if(AuthController.ses == null){
                System.out.println("Session is null!");
                throw new IllegalArgumentException("You must log in before doing this!");
            }


        });

        app.exception(IllegalArgumentException.class,(e,ctx) -> {
            ctx.status(401);
            ctx.result(e.getMessage());
        });

        app.get("/passengers",pc.getPassengerHandler);

        app.post("/passengers",pc.insertPassengerHandler);

        app.get("/flights/{flightNumber}",fc.getRoleByFlightNumberHandler);

        app.get("/flights",fc.getFlights);

        app.post("/auth",ac.loginHandler);

        app.post("/flights",fc.insertFlightHandler);

        app.delete("/passengers/{passengerId}",pc.deletePassenger);

        app.get("/passengers/flight/{flightIdFk}",pc.getPassengersByFlightID);

        app.patch("/flights/delay/id={id}hours={hours}",fc.updateDepartureTime);



    }
}
