package com.revature.models;
import java.sql.Timestamp;


public class Flight {

    private int flight_number;

    private String origin;

    private String destination;

    private boolean delayed;

    private Timestamp departure_time;

    private Timestamp arrival_time;

    public Flight(){

    }

    public Flight(int flight_number, String origin, String destination, boolean delayed, Timestamp departure_time, Timestamp arrival_time) {
        this.flight_number = flight_number;
        this.origin = origin;
        this.destination = destination;
        this.delayed = delayed;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public Timestamp getDepartureTime() {
        return departure_time;
    }

    public void setDepartureTime(String departure_time) {
        try {
            this.departure_time = Timestamp.valueOf(departure_time);
        }catch(Exception e){
            e.printStackTrace();
            this.departure_time = null;
        }
    }

    public Timestamp getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        try {
            this.arrival_time = Timestamp.valueOf(arrival_time);
        }catch(Exception e){
                e.printStackTrace();
                this.arrival_time = null;
        }
    }

    @Override
    public String toString() {
        return "\nFlight{" +
                "\n flight_number=" + flight_number +
                "\n origin='" + origin + '\'' +
                "\n destination='" + destination + '\'' +
                "\n delayed=" + delayed +
                "\n departure_time=" + departure_time.toLocalDateTime() +
                "\n arrival_time=" + arrival_time.toLocalDateTime() +
                "}";
    }
}
