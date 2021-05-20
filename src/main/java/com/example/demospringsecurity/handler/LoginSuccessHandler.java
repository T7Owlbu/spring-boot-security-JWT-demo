package com.example.demospringsecurity.handler;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demospringsecurity.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        Customer customer = new Customer();
        BeanUtils.copyProperties(principal,customer);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("username", customer.getUsername())
                .withClaim("expire",System.currentTimeMillis()+1000*60*30);
        String jwt = builder.sign(Algorithm.HMAC256("secret"));
        httpServletResponse.setHeader("token",jwt);
        httpServletResponse.getWriter().write("login success!");
    }

}