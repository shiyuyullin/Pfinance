package com.shiyu.Pfinance.Controller;

import com.shiyu.Pfinance.Service.AuthService;
import com.shiyu.Pfinance.dto.LoginRequest;
import com.shiyu.Pfinance.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest request){
        return authService.registerUser(request);
    }


    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request){
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword());
        Authentication authenticationResponse =
                authenticationManager.authenticate(authenticationRequest);
        return ResponseEntity.accepted().build();
    }


}
