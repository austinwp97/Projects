package com.revature.controllers;


import com.revature.models.LoginDTO;
import com.revature.models.OutUserDTO;
import com.revature.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    public static HttpSession session;

    @PostMapping
    public ResponseEntity<OutUserDTO> login(@RequestBody LoginDTO lDTO, HttpSession session){

        OutUserDTO uDTO = authService.login(lDTO, session);

        return ResponseEntity.ok(uDTO);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){

        return ResponseEntity.status(400).body(e.getMessage());
    }
}
