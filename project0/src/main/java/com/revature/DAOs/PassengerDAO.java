package com.revature.DAOs;

import com.revature.models.Passenger;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengerDAO implements PassengerDAOInterface{

    @Override
    public Passenger insertPassenger(Passenger p) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO passengers (last_name, first_name, seatClass, flight_id_fk) VALUES (?,?,?)";

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

    @Override
    public ArrayList<Passenger> getAllPassengers() {
        return null;
    }
}
