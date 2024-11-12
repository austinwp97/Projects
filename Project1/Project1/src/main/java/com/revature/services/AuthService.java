package com.revature.services;

import com.revature.DAOs.AuthDAO;
import com.revature.controllers.AuthController;
import com.revature.models.LoginDTO;
import com.revature.models.OutUserDTO;
import com.revature.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthDAO aDAO;

    @Autowired
    public AuthService(AuthDAO aDAO){
        this.aDAO = aDAO;
    }

    public OutUserDTO login(LoginDTO lDTO, HttpSession session){

        User u = aDAO.findByUsernameAndPassword(lDTO.getUsername(), lDTO.getPassword());

        if(u == null){
            throw new IllegalArgumentException("No user found with those credentials!");
        }

        AuthController.session = session;

        AuthController.session.setAttribute("userId",u.getUserId());
        AuthController.session.setAttribute("username", u.getUsername());
        AuthController.session.setAttribute("role", u.getRole());

        return new OutUserDTO(u.getUserId(),u.getUsername(),u.getRole(),u.getFirstName(),u.getLastName(),u.getReimbursements());


    }
}
