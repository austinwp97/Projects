package com.revature;

import com.revature.DAOs.FlightDAO;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class LauncherOLD {

    public static void main(String[] args) {
        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("CONNECTION FAILED");
        }

        FlightDAO f = new FlightDAO();

        System.out.println(f.getFlightByNumber(1));
    }
}
