package com.revature.controllers;

import com.revature.DAOs.AuthDAO;
import com.revature.models.LoginDTO;
import com.revature.models.Passenger;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class AuthController {

    AuthDAO aDAO = new AuthDAO();

    public static HttpSession ses;

    public Handler loginHandler = ctx -> {

    LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);

    Passenger loggedInPassenger = aDAO.login(lDTO.getPassenger_id(),lDTO.getFirst_name());

    if(loggedInPassenger != null){

        ses = ctx.req().getSession();

        ses.setAttribute("first_name",loggedInPassenger.getFirst_name());
        ses.setAttribute("last_name",loggedInPassenger.getLast_name());


        ctx.result("Admin first name: " + ses.getAttribute("first_name") + " " + ses.getAttribute("last_name") + " logged in successfully.");

    }
    else{
        ctx.result("Login failed.");
        ctx.skipRemainingHandlers();

    }










    };
}
