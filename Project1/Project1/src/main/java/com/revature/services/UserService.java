package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import com.revature.models.OutUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User register(User newUser){

        if(newUser.getUsername() == null || newUser.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty!");
        }

        return userDAO.save(newUser);
    }

    public User updateUserRole(int userId, String newRole){


        User u = userDAO.findById(userId).orElseThrow( () -> new IllegalArgumentException("No user found with id: " + userId));

        u.setRole(newRole);

        userDAO.save(u);

        return u;
    }

    public OutUserDTO deleteUserById(int userId){

        User userToDelete = userDAO.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + userId));

        OutUserDTO outU = new OutUserDTO(userToDelete.getUserId(),
                userToDelete.getUsername(),
                userToDelete.getRole(),
                userToDelete.getFirstName(),
                userToDelete.getLastName(),
                userToDelete.getReimbursements());

        userDAO.deleteById(userId);

        return outU;

    }

    public List<OutUserDTO> getAllUsers(){
        List<User> users = userDAO.findAll();

        List<OutUserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            OutUserDTO userDTO = new OutUserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getReimbursements());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

}
