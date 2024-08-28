package com.shiyu.Pfinance.Service;

import com.shiyu.Pfinance.Entity.User;
import com.shiyu.Pfinance.Repository.UserRepository;
import com.shiyu.Pfinance.dto.LoginRequest;
import com.shiyu.Pfinance.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    public ResponseEntity<User> registerUser(RegistrationRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

//    public ResponseEntity<String> loginUser(LoginRequest loginRequest){
//        Authentication authenticationRequest =
//                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
//        Authentication authenticationResponse =
//                authenticationManager.authenticate(authenticationRequest);
//        return ResponseEntity.ok().body("Successful login");
//    }

}
