package com.shiyu.Pfinance.Service;

import com.shiyu.Pfinance.Entity.User;
import com.shiyu.Pfinance.Repository.UserRepository;
import com.shiyu.Pfinance.dto.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public String registerUser(RegistrationRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        //TODO: password encryption
        userRepository.save(user);
        return "User registered";
    }

}
