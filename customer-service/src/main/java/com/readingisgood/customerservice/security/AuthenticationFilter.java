package com.readingisgood.customerservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readingisgood.customerservice.dto.CreateCustomerDto;
import com.readingisgood.customerservice.dto.LoginDto;
import com.readingisgood.customerservice.service.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final CustomerService customerService;
    private final Environment environment;
    private final AuthenticationManager authenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDto creds = new ObjectMapper().readValue(request.getInputStream(),LoginDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword()
            ,new ArrayList<>()));

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userName = ((User)authResult.getPrincipal()).getUsername();
        customerService.loadUserByUsername(userName);
        CreateCustomerDto createCustomerDto = customerService.getCustomerDetailsByEmail(userName);
        String token = Jwts.builder()
                .setSubject(createCustomerDto.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512,environment.getProperty("token.secret"))
                .compact();
        response.addHeader("token",token);
        response.addHeader("customerId",createCustomerDto.getId().toString());
    }
}
