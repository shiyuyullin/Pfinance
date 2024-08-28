package com.shiyu.Pfinance.Service;

import com.shiyu.Pfinance.Entity.User;
import com.shiyu.Pfinance.Repository.UserRepository;
import com.shiyu.Pfinance.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> registerUser(RegistrationRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }


}
