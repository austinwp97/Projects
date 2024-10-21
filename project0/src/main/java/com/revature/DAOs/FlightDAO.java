package com.revature.DAOs;

import com.revature.models.Flight;
import com.revature.utils.ConnectionUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightDAO implements FlightDAOInterface{
    @Override
    public Flight getFlightByNumber(int flight_number) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM flights WHERE flight_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,flight_number);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Flight flight = new Flight(rs.getInt("flight_number"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getBoolean("delayed"),
                        Timestamp.valueOf(rs.getString("departure_time")),
                        Timestamp.valueOf(rs.getString("arrival_time")));
                return flight;
            }



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't get flight by flight number");


        }

        return null;
    }

    public ArrayList<Flight> getAllFlights(){

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM flights";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Flight> flights = new ArrayList<>();

            while(rs.next()){

                Flight f = new Flight(
                        rs.getInt("flight_number"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getBoolean("delayed"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("arrival_time")

                );
                flights.add(f);
            }
            return flights;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("No flights found.");
        }
        return null;
    }

    public Flight insertFlight(Flight f){

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO flights (origin, destination,delayed,departure_time, arrival_time) VALUES (?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,f.getOrigin());
            ps.setString(2,f.getDestination());
            ps.setBoolean(3,f.isDelayed());
            ps.setTimestamp(4,f.getDepartureTime());
            ps.setTimestamp(5,f.getArrival_time());

            ps.executeUpdate();

            return f;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't insert flight");
        }
    return null;

    }


    @Override
    public ArrayList<Timestamp> updateDelayed(int flight_number,double hours) {


        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "UPDATE flights SET departure_time = ?, arrival_time = ?, delayed = ? WHERE flight_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ArrayList<Timestamp> newTimes = getTimestamps(flight_number, hours); //returns timestamps after specified number of hours for specific flight_number
                                                                                 //returns ArrayList with first element = departure time, second element = arrival time

            ps.setTimestamp(1,newTimes.get(0));
            ps.setTimestamp(2,newTimes.get(1));
            ps.setBoolean(3,true);
            ps.setInt(4,flight_number);
            ps.executeUpdate();

            return newTimes;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't insert flight");
        }

    return null;
    }

    @NotNull
    private static ArrayList<Timestamp> getTimestamps(int flight_number, double hours) {

        FlightDAO fDAO = new FlightDAO();
        Flight f = fDAO.getFlightByNumber(flight_number);

        Timestamp currentDepartureTime = f.getDepartureTime();

        Timestamp newDepartureTime = new Timestamp(currentDepartureTime.getTime() + (long)(hours * 3600000));

        Timestamp currentArrivalTime = f.getArrival_time();

        Timestamp newArrivalTime = new Timestamp(currentArrivalTime.getTime() + (long)(hours * 3600000));

        ArrayList<Timestamp> newTimes = new ArrayList<>();

        newTimes.add(currentDepartureTime);
        newTimes.add(currentArrivalTime);
        newTimes.add(newDepartureTime);
        newTimes.add(newArrivalTime);
        return newTimes;
    }


}
