package com.shiyu.Pfinance.Service;

import com.shiyu.Pfinance.Entity.Customer;
import com.shiyu.Pfinance.Repository.CustomerRepository;
import com.shiyu.Pfinance.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<Customer> registerCustomer(RegistrationRequest request){
        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setEmail(request.getEmail());
        String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
        return ResponseEntity.ok().body(customer);
    }


}
