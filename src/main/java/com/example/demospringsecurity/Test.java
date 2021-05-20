package com.example.demospringsecurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.example.demospringsecurity.entity.Customer;
import org.springframework.util.Base64Utils;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Test {

    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setUsername("T7Owlbu");
        customer.setPassword("root");
        customer.setValid(true);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("customerId",customer.getId());
        builder.withClaim("customerUsername",customer.getUsername());
        String token = builder.sign(Algorithm.HMAC256("secret"));

        System.out.println(token);

        DecodedJWT decode = JWT.decode(token);

        String header = decode.getHeader();
        byte[] headerBytes = Base64Utils.decodeFromUrlSafeString(header);
        String headerString = new String(headerBytes, UTF_8);
        System.out.println(headerString);

        String payload = decode.getPayload();
        byte[] payloadBytes = Base64Utils.decodeFromUrlSafeString(payload);
        String payloadString = new String(payloadBytes, UTF_8);
        System.out.println(payloadString);

        try{
            Verification verification = JWT.require(Algorithm.HMAC256("secret"));
            verification.build().verify(token);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
