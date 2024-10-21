package com.revature.DAOs;

import com.revature.models.Flight;
import com.revature.models.Passenger;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class PassengerDAO implements PassengerDAOInterface{

    @Override
    public Passenger insertPassenger(Passenger p) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO passengers (last_name, first_name, seatclass, flight_id_fk) VALUES (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,p.getLast_name());
            ps.setString(2,p.getFirst_name());
            ps.setString(3,p.getSeatClass());
            ps.setInt(4,p.getFlight_id_fk());

            ps.executeUpdate();

            return p;


        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't insert Passenger");
        }


        return null;
    }
    public Passenger getPassengerById(int passenger_id) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM passengers WHERE passenger_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,passenger_id);

            ResultSet rs = ps.executeQuery();

            FlightDAO fDAO = new FlightDAO();
            if(rs.next()){
                Passenger passenger = new Passenger(fDAO.getFlightByNumber(rs.getInt("flight_id_fk")),
                        rs.getString("seatclass"),
                        rs.getInt("passenger_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"));
                return passenger;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't get flight by flight number");


        }

        return null;
    }


    @Override
    public ArrayList<Passenger> getAllPassengers() {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM passengers";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Passenger> passengers = new ArrayList<>();

            FlightDAO fDAO = new FlightDAO();

            while(rs.next()){

                Passenger p = new Passenger(
                        fDAO.getFlightByNumber(rs.getInt("flight_id_fk")),
                        rs.getString("seatclass"),
                        rs.getInt("passenger_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name")
                );
                passengers.add(p);
            }
            return passengers;


        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't get passengers");
        }
        return null;
    }

    public Passenger deletePassenger(int passenger_id){
        try(Connection conn = ConnectionUtil.getConnection()){
            PassengerDAO pDAO = new PassengerDAO();
            Passenger p = pDAO.getPassengerById(passenger_id);


            String sql = "DELETE FROM passengers WHERE passenger_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,passenger_id);



            ps.executeUpdate();


            return p;


        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Passenger not found.");
        }
        return null;
    }

    public ArrayList<Passenger> getPassengerByFK(int flight_id_fk) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            ArrayList<Passenger> passengers = new ArrayList<>();
            String sql = "SELECT * FROM passengers WHERE flight_id_fk = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,flight_id_fk);

            ResultSet rs = ps.executeQuery();

            FlightDAO fDAO = new FlightDAO();

            while(rs.next()){
                Passenger passenger = new Passenger(fDAO.getFlightByNumber(flight_id_fk),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("seatclass"),
                        rs.getInt("flight_id_fk"),
                        rs.getInt("passenger_id")
                        );


                passengers.add(passenger);
            }

            return passengers;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't get passengers by flight number");
        }

        return null;
    }
}
