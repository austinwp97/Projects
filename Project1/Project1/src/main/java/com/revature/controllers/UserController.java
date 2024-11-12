package com.revature.controllers;


import com.revature.aspects.AdminOnly;
import com.revature.models.User;
import com.revature.models.OutUserDTO;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User newUser){

        User u = userService.register(newUser);

        return ResponseEntity.status(201).body(u);

    }
//
//    @AdminOnly
    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserRole(@PathVariable int userId, @RequestBody Map<String, String> roleMap){
        String newRole = roleMap.get("role");
        return ResponseEntity.status(201).body(userService.updateUserRole(userId,newRole));

    }

    @AdminOnly
    @DeleteMapping("/{userId}")
    public ResponseEntity<OutUserDTO> deleteUser(@PathVariable int userId){
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @AdminOnly
    @GetMapping
    public ResponseEntity<List<OutUserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){

        return ResponseEntity.status(400).body(e.getMessage());
    }

}
