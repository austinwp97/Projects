package com.revature.models;

public class Passenger {

    private String first_name;
    private String last_name;
    private int passenger_id;
    private String seatClass;

    private Flight flight;

    private int flight_id_fk;

    public Passenger(){

    }

    public Passenger(Flight flight, String seatClass, int passenger_id, String last_name, String first_name) {
        this.flight = flight;
        this.seatClass = seatClass;
        this.passenger_id = passenger_id;
        this.last_name = last_name;
        this.first_name = first_name;
    }

    public Passenger(Flight flight,String last_name, String first_name, String seatClass, int flight_id_fk, int passenger_id) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.seatClass = seatClass;
        this.flight_id_fk = flight_id_fk;
        this.flight = flight;
        this.passenger_id = passenger_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getFlight_id_fk() {
        return flight_id_fk;
    }

    public void setFlight_id_fk(int flight_id_fk) {
        this.flight_id_fk = flight_id_fk;
    }

    @Override
    public String toString() {
        return "Passenger{\n" +
                "first_name='" + first_name + "',\n" +
                "last_name='" + last_name + "',\n" +
                "passenger_id=" + passenger_id + "',\n" +
                "seatClass='" + seatClass + "',\n" +
                "flight=\n" + flight + "\n" +
                "flight_id_fk=" + flight_id_fk +
                '}';
    }
}
