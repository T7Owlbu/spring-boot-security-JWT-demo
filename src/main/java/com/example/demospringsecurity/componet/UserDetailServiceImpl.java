package com.example.demospringsecurity.componet;

import com.example.demospringsecurity.entity.Authority;
import com.example.demospringsecurity.entity.Customer;
import com.example.demospringsecurity.service.impl.AuthorityServiceImpl;
import com.example.demospringsecurity.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private AuthorityServiceImpl authorityService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customerService.getCustomerByUsername(s);
        List<Authority> authorityList = authorityService.getAuthorityListByUsername(s);
        List<GrantedAuthority> grantedAuthorityList = authorityList.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
        UserDetails userDetails = new User(customer.getUsername(),customer.getPassword(),grantedAuthorityList);
        return userDetails;
    }

}
