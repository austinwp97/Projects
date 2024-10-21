package com.revature.DAOs;

import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.*;

public class AuthDAO {

    public Passenger login(int passenger_id, String first_name){

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM passengers WHERE passenger_id = ? AND first_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,passenger_id);
            ps.setString(2,first_name);

            ResultSet rs = ps.executeQuery();

            FlightDAO fDAO = new FlightDAO();

            if(rs.next()){

                Passenger pass = new Passenger(
                        fDAO.getFlightByNumber(rs.getInt("flight_id_fk")),
                        rs.getString("seatClass"),
                        rs.getInt("passenger_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"));
                return pass;
            }
        }catch(SQLException e){
        e.printStackTrace();
            System.out.println("Could not find that passenger.");
        }
        return null;
}
}
