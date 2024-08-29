package com.shiyu.Pfinance.Config;

import com.shiyu.Pfinance.Entity.Customer;
import com.shiyu.Pfinance.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if(customer.isPresent()){
            Customer customerObj = customer.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(customerObj.getUsername())
                    .password(customerObj.getPassword())
                    .roles(getRoles(customerObj))
                    .build();
        }
        else{
            throw new UsernameNotFoundException("Username doesn't exist");
        }
    }

    private String[] getRoles(Customer customer){
        if(customer.getRole() == null) return new String[]{"USER"};
        return customer.getRole().split(",");
    }
}
