package com.shiyu.Pfinance.Controller;

import com.shiyu.Pfinance.Entity.Customer;
import com.shiyu.Pfinance.Service.AuthService;
import com.shiyu.Pfinance.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody RegistrationRequest request){
        return authService.registerCustomer(request);
    }



}
