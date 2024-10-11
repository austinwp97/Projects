package com.revature.models;
import java.time.*;

public class Flight {

    private int flight_number;

    private String origin;

    private String destination;

    private boolean delayed;

    private LocalDateTime departure_time;

    private LocalDateTime arrival_time;

    public Flight(){

    }

    public Flight(int flight_number, String origin, String destination, boolean delayed, LocalDateTime departure_time, LocalDateTime arrival_time) {
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

    public LocalDateTime getDepartureTime() {
        return departure_time;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departure_time = departure_time;
    }

    public LocalDateTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalDateTime arrival_time) {
        this.arrival_time = arrival_time;
    }
}
